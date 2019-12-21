package com.example.wowebackand.views;

import android.content.Context;
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
import com.example.wowebackand.activities.SecActivity;
import com.example.wowebackand.models.Client;
import com.example.wowebackand.models.ClientForm;
import com.example.wowebackand.viewModel.LoginViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends Fragment {

    Context context;

    Integer value;
    LoginViewModel viewModel;


    ClientNet net;
    EditText userName, password1, password2, phone, email;
    Button register,signIn;
    ProgressBar proBarReg;

    Client client;
    String username,password,emailS,phoneNumber;

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
        proBarReg=view.findViewById(R.id.probar_register);

        viewModel= ViewModelProviders.of(this).get(LoginViewModel.class);

        register.setOnClickListener((view1)->{
            if (checkSpaces()){
                makeToast("please fill the data in all fields");
                return;
            }
            if (!(password1.getText().toString().equals(password2.getText().toString()))){
               makeToast("password not match");
                return;
            }
            password=password1.getText().toString();

            username=userName.getText().toString();
            emailS=email.getText().toString();
            phoneNumber=phone.getText().toString();

            if (!isNetworkAvaible()) {
                makeToast("not connected");
                return;
            }
            else
            {
                proBarReg.setVisibility(View.VISIBLE);
                ClientForm form=new ClientForm();
                form.setEmail(emailS);
                form.setPhoneNumber(phoneNumber);
                form.setUsername(username);
                form.setPassword(password);
                viewModel.register(form).observe(this,value->{
                    if (value!=null){
                        switch (value) {
                            case 100:
                                makeToast("the username has taken");
                                proBarReg.setVisibility(View.GONE);
                                return;
                            case 0:
                                makeToast("try later");
                                return;
                            case 1:
                                makeToast("done");
                                proBarReg.setVisibility(View.GONE);
                                SecActivity.controller.navigate(R.id.loginFragment);
                                return;
                        }
                    }
                });
                new Handler().postDelayed(()->{
                    if (proBarReg.getVisibility()==View.VISIBLE){
                    proBarReg.setVisibility(View.GONE);
                    makeToast("network fails");
                    }
                },10000);


            }


        });

        signIn.setOnClickListener((view1)->{
            SecActivity.controller.navigate(R.id.loginFragment);
        });

        return view;
    }


    boolean checkSpaces() {
        boolean value = false;
        if (password1.getText().toString().trim().isEmpty()
                || password2.getText().toString().trim().isEmpty()
                || userName.getText().toString().trim().isEmpty()
                || email.getText().toString().trim().isEmpty()
                || phone.getText().toString().trim().isEmpty()) {

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
