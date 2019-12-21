package com.example.wowebackand.viewModel;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wowebackand.Retrofit.ClientNet;
import com.example.wowebackand.Retrofit.RetrofitService;
import com.example.wowebackand.models.Client;
import com.example.wowebackand.models.ClientForm;
import com.example.wowebackand.models.filters.ClientFilter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel
{
    ClientNet net;
    MutableLiveData<ClientForm> mutableLiveData;
    MutableLiveData<Integer> integerMutableLiveData;

    public LoginViewModel() {
        net= RetrofitService.createService(ClientNet.class);
    }

    public MutableLiveData<ClientForm> login(ClientFilter filter){
        mutableLiveData=new MutableLiveData<>();

        Call<ClientForm> call;

        call=net.getClient(filter);

        call.enqueue(new Callback<ClientForm>() {

            @Override
            public void onResponse(Call<ClientForm> call, Response<ClientForm> response) {
                if (!response.isSuccessful()){
                    return;
                }
//                clientForm=response.body();
//                progressBar.setVisibility(View.GONE);
                mutableLiveData.postValue(response.body());
                Log.e("message",response.message());
                Log.e("onResponse","muri response");
                return;
            }

            @Override
            public void onFailure(Call<ClientForm> call, Throwable t) {
//                progressBar.setVisibility(View.GONE);
                Log.e("login","kubera"+t.getMessage());
//                Toast.makeText()`
                return;
            }
        });
        Log.e("main","muri main Thread");
        return mutableLiveData;
    }

    public MutableLiveData<Integer> register(ClientForm form){
        integerMutableLiveData=new MutableLiveData<>();
       Client client = new Client();
        client.setUsername(form.getUsername());
        client.setPassword(form.getPassword());
        client.setEmail(form.getEmail());
        client.setPhoneNumber(form.getPhoneNumber());

        Call<Integer> call=net.createClient(client);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (!response.isSuccessful()) {
                    Log.e("unsucesfull", "code error:" + response.code());
                    return;
                }
                integerMutableLiveData.postValue(response.body());
                return;
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                integerMutableLiveData.postValue(90);
            }
        });
        return integerMutableLiveData;
    }

}
