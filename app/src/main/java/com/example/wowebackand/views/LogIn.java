package com.example.wowebackand.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wowebackand.R;
import com.example.wowebackand.Retrofit.ClientNet;
import com.example.wowebackand.Retrofit.RetrofitService;
import com.example.wowebackand.activities.MainActivity;
import com.example.wowebackand.activities.SecActivity;
import com.example.wowebackand.models.Client;
import com.example.wowebackand.models.ClientForm;
import com.example.wowebackand.models.constant.Const;
import com.example.wowebackand.models.filters.ClientFilter;
import com.example.wowebackand.respostory.ClientRespostory;
import com.example.wowebackand.viewModel.LoginViewModel;
import com.example.wowebackand.viewModel.MainViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogIn extends Fragment {
    ClientNet net;
    String uName, pwd;
    SharedPreferences preferences;
    Intent intent;

    LoginViewModel loginViewModel;



    EditText userName, password;
    Button signIn;
    TextView forget, create;
    ClientForm clientForm;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        userName = view.findViewById(R.id.login_user_name);
        password = view.findViewById(R.id.login_password);
        signIn = view.findViewById(R.id.login);
        forget = view.findViewById(R.id.login_forget_password);
        create = view.findViewById(R.id.login_registration);
        progressBar=view.findViewById(R.id.probar);

        loginViewModel= ViewModelProviders.of(this).get(LoginViewModel.class);

        preferences = getActivity().getSharedPreferences("userDetails", Context.MODE_PRIVATE);

        intent = new Intent(getActivity(), MainActivity.class);

//        if (preferences.contains("userName") && preferences.contains("password")) {
//            startActivity(intent);
//        }

        signIn.setOnClickListener((view1) -> {
            uName = userName.getText().toString();
            pwd = password.getText().toString();
            if (!isNetworkAvaible()){
//                Toast.makeText(getContext(),"notConneted",Toast.LENGTH_SHORT).show();
                makeToast("notConneted");
                return;
            }
            if (!checkString(uName, pwd)){
//                Toast.makeText(getContext(), "fill the fields", Toast.LENGTH_SHORT).show();
                makeToast("fill the fields");
            }
             else {
                progressBar.setVisibility(View.VISIBLE);
//
//                if (uName.equals("didox") && pwd.equals("wowe")) {
//                    SharedPreferences.Editor editor = preferences.edit();
//                    editor.putString("userName", uName);
//                    editor.putString("password", pwd);
//                    editor.commit();
//                    startActivity(intent);
//                }

                ClientFilter filter = new ClientFilter();
                filter.setUsername(uName);
                filter.setPassword(pwd);
                loginViewModel.login(filter).observe(this,(client)->{
                    if (client!=null){
                        /**
                         * ndaza gukoresha iriya clientId nkoresheje MainViewModel.class
                         * maze nkurure completed,pending appoitements hamwe
                         */
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("token",client.getToken());
                        editor.putString("userName",uName);
                        editor.putString("phone", client.getPhoneNumber());
                        editor.putString("email",client.getEmail());
                        editor.putInt("clientId",client.getClientId());
                        editor.commit();
                        progressBar.setVisibility(View.GONE);
//                    MainViewModel.setClientId(client.getClientId());
//                    MainActivity.navController.navigate(R.id.defaultFragment);
                        startActivity(intent);
                    }
//                    else {
////                    Toast.makeText(getContext(), "the user does not exist", Toast.LENGTH_SHORT).show();
//                        progressBar.setVisibility(View.GONE);
//                        makeToast("the user does not exist");
//                    }
                });
                new Handler().postDelayed(()->{
                    progressBar.setVisibility(View.GONE);
                    makeToast("the user does not exist");
//                        return;
                },5000);
            }

        });

        create.setOnClickListener((view1) -> {
            SecActivity.controller.navigate(R.id.registerFragment);
        });

        forget.setOnClickListener((view1) -> {
            /**
             * hano turaza gufungura indi fragment yo ku coveringa password
             * arko turaza kuyohereza kuri email password
             */
        });

        return view;
    }


    public boolean checkString(String username, String password) {

        boolean check = false;

        if (username != null && password != null) {

            if (!username.trim().isEmpty()) {
                check = true;
            }
        }

        return check;
    }

//    public ClientForm clientExist(String username, String password) {
////        respostory=new ClientRespostory();
//        ClientFilter filter = new ClientFilter();
//        filter.setUsername(username);
//        filter.setPassword(password);
//        ClientForm client=login(filter);
//
//        return client;
//
//    }


//    public ClientForm login(ClientFilter filter){
//        net= RetrofitService.createService(ClientNet.class);
//        Call<ClientForm> call;
//
//        call=net.getClient(filter);
//
//        call.enqueue(new Callback<ClientForm>() {
//
//            @Override
//            public void onResponse(Call<ClientForm> call, Response<ClientForm> response) {
//                clientForm=response.body();
////                progressBar.setVisibility(View.GONE);
//                Log.e("onResponse","muri response");
//                return;
//            }
//
//            @Override
//            public void onFailure(Call<ClientForm> call, Throwable t) {
////                progressBar.setVisibility(View.GONE);
//                Log.e("login","kubera"+t.getCause().getMessage());
////                Toast.makeText()`
//                return;
//            }
//        });
//        Log.e("main","muri main Thread");
//        return clientForm;
//    }

    private boolean isNetworkAvaible() {

        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;

        return connected;

    }
    public void makeToast(String msg){
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }
}
