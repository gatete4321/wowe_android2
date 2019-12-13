package com.example.wowebackand.views;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wowebackand.R;
import com.example.wowebackand.Retrofit.ClientNet;
import com.example.wowebackand.Retrofit.RetrofitService;
import com.example.wowebackand.activities.SecActivity;
import com.example.wowebackand.models.Client;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends Fragment {

    Context context;

    Integer value;

    ClientNet net;
    EditText userName, password1, password2, phone, email;
    Button register,signIn;

    Client client;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      context=getContext();
       View view = inflater.inflate(R.layout.fragment_register, container, false);
        userName = view.findViewById(R.id.register_user_name);
        password1 = view.findViewById(R.id.register_password1);
        password2 = view.findViewById(R.id.register_password2);
        phone = view.findViewById(R.id.register_phone);
        email = view.findViewById(R.id.register_email);
        register = view.findViewById(R.id.register);
        signIn=view.findViewById(R.id.register_signin);

        register.setOnClickListener((view1)->{
            if (checkSpaces()){
                makeToast("please fill the data in all fields");
                return;
            }
            if (!(password1.getText().toString().equals(password2.getText().toString()))){
               makeToast("password not match");
                return;
            }
//            if (checkUsernameHastaken(userName.getText().toString())){
//                Toast.makeText(getActivity(),"username has taken",Toast.LENGTH_SHORT).show();
//            }
            if (isNetworkAvaible()) {
                if (createClient() != null) {

                    switch (value) {
                        case 20:
                            makeToast("the username has taken");
                            return;
                        case 10:
                            makeToast("try later");
                            return;
                        case 1:
                            makeToast("done");
                            SecActivity.controller.navigate(R.id.loginFragment);
                            return;
                    }

                }
            }
            else
                makeToast("not connected");
//            MainActivity.navController.navigate(R.id.loginFragment);


        });

        signIn.setOnClickListener((view1)->{
            SecActivity.controller.navigate(R.id.loginFragment);
        });

        return view;
    }

    public Integer createClient() {
        net= RetrofitService.createService(ClientNet.class);
        client = new Client();
        client.setUsername(userName.getText().toString());
        client.setPassword(password1.getText().toString());
        client.setEmail(email.getText().toString());
        client.setPhoneNumber(phone.getText().toString());

        Call<Integer> call=net.createClient(client);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (!response.isSuccessful()) {
                    Log.e("unsucesfull", "code error:" + response.code());
                    return;
                }
                value=response.body();
                return;
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
               makeToast("failure"+t.getMessage());
            }
        });


        /**
         * hano code za retrofit zo gusubmitinga
         */

        return null;
    }

    boolean checkSpaces() {
        boolean value = false;
        if (!(password1.getText().toString().trim().isEmpty()
                && password2.getText().toString().trim().isEmpty()
                && userName.getText().toString().trim().isEmpty())
                && email.getText().toString().trim().isEmpty()
                && phone.getText().toString().trim().isEmpty()) {
            value = true;
            /**
             * for future i will put even email for erecovery password
             */
        }
        return value;
    }

    public void makeToast(String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

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
}
