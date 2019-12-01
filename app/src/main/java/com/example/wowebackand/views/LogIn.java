package com.example.wowebackand.views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wowebackand.R;
import com.example.wowebackand.activities.MainActivity;
import com.example.wowebackand.activities.SecActivity;
import com.example.wowebackand.models.Client;
import com.example.wowebackand.models.ClientForm;
import com.example.wowebackand.models.constant.Const;
import com.example.wowebackand.models.filters.ClientFilter;
import com.example.wowebackand.respostory.ClientRespostory;
import com.example.wowebackand.viewModel.MainViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LogIn extends Fragment {
    String uName, pwd;
    SharedPreferences preferences;
    Intent intent;


    EditText userName, password;
    Button signIn;
    TextView forget, create;

    ClientRespostory respostory;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        userName = view.findViewById(R.id.login_user_name);
        password = view.findViewById(R.id.login_password);
        signIn = view.findViewById(R.id.login);
        forget = view.findViewById(R.id.login_forget_password);
        create = view.findViewById(R.id.login_registration);

        preferences = getActivity().getSharedPreferences("userDetails", Context.MODE_PRIVATE);

        intent = new Intent(getActivity(), MainActivity.class);

//        if (preferences.contains("userName") && preferences.contains("password")) {
//            startActivity(intent);
//        }

        signIn.setOnClickListener((view1) -> {
            uName = userName.getText().toString();
            pwd = password.getText().toString();
            if (checkString(uName, pwd)) {
//
//                if (uName.equals("didox") && pwd.equals("wowe")) {
//                    SharedPreferences.Editor editor = preferences.edit();
//                    editor.putString("userName", uName);
//                    editor.putString("password", pwd);
//                    editor.commit();
//                    startActivity(intent);
//                }



                ClientForm client=clientExist(userName.getText().toString(),password.getText().toString());
                if (client!=null){
                    /**
                     * ndaza gukoresha iriya clientId nkoresheje MainViewModel.class
                     * maze nkurure completed,pending appoitements hamwe
                     */
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("token",client.getToken());
                    editor.putString("userName",uName);
                    editor.putString("password", pwd);
                    editor.putInt("clientId",client.getClientId());
                    editor.commit();
//                    MainViewModel.setClientId(client.getClientId());
//                    MainActivity.navController.navigate(R.id.defaultFragment);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getContext(), "the user does not exist", Toast.LENGTH_SHORT).show();
                }

            }
            else{
                Toast.makeText(getContext(), "fill the fields", Toast.LENGTH_SHORT).show();
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

    public ClientForm clientExist(String username, String password) {
        respostory=new ClientRespostory();
        ClientFilter filter = new ClientFilter();
        filter.setUsername(username);
        filter.setPassword(password);
        ClientForm client=respostory.login(filter);

        return client;

    }


    public void  login(){

    }

}
