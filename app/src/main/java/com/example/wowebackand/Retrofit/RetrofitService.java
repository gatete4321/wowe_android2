package com.example.wowebackand.Retrofit;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService
{
    HttpLoggingInterceptor loggingInterceptor;
    public static Retrofit retrofit;
    OkHttpClient okHttpClient;

    public RetrofitService() {

        Gson gson=new GsonBuilder().serializeNulls().create();
        loggingInterceptor=new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient=new OkHttpClient.Builder()
                          .addInterceptor(loggingInterceptor)
                          .build();


        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:9000")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }


    public static <S> S createService(Class<S> serviceClass){
        new RetrofitService();
        return retrofit.create(serviceClass);
    }
}
