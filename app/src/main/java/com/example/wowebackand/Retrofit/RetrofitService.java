package com.example.wowebackand.Retrofit;


import com.example.wowebackand.models.constant.Const;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService
{
    HttpLoggingInterceptor loggingInterceptor;
    public static Retrofit retrofit;
    OkHttpClient okHttpClient;

    public RetrofitService() {

        Gson gson=new GsonBuilder().serializeNulls().create();//if i want to send nulls on the server
        loggingInterceptor=new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request orginalRequest=chain.request();
                        Request newRequest=orginalRequest.newBuilder()
                                .header("Authorization", "Bearer "+Const.token)
                                .build();
                        return chain.proceed(newRequest);
                    }
                })
                          .addInterceptor(loggingInterceptor)
                          .build();


        retrofit = new Retrofit.Builder()
                .baseUrl("https://wowe2.cfapps.io")
//                .baseUrl("http://10.0.2.2:9000")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }


    public static <S> S createService(Class<S> serviceClass){
        new RetrofitService();
        return retrofit.create(serviceClass);
    }
}
