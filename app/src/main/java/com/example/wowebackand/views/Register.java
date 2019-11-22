package com.example.wowebackand.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wowebackand.R;
import com.example.wowebackand.activities.SecActivity;
import com.example.wowebackand.models.Client;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class Register extends Fragment {


    EditText userName, password1, password2, phone, email;
    Button register,signIn;

    Client client;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
                Toast.makeText(getActivity(),"please fill the data in all fields",Toast.LENGTH_SHORT).show();
                return;
            }
            if (!(password1.getText().toString().equals(password2.getText().toString()))){
               Toast.makeText(getActivity(),"password not match",Toast.LENGTH_SHORT).show();
                return;
            }
            if (checkUsernameHastaken(userName.getText().toString())){
                Toast.makeText(getActivity(),"username has taken",Toast.LENGTH_SHORT).show();
            }
            if (createClient()==null){
                Toast.makeText(getActivity(),"denied",Toast.LENGTH_SHORT).show();
                return;
            }
//            MainActivity.navController.navigate(R.id.loginFragment);


        });

        signIn.setOnClickListener((view1)->{
            SecActivity.controller.navigate(R.id.loginFragment);
        });

        return view;
    }

    public Integer createClient() {
        client = new Client();
        client.setUsername(userName.getText().toString());
        client.setPassword(password1.getText().toString());
        client.setEmail(email.getText().toString());
        client.setPhoneNumber(phone.getText().toString());

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

    boolean checkUsernameHastaken(String userName){
        boolean value=true;
        /**
         * man nkoze icekinga ko username yafashwe
         * localhost:9000/public/client/checkUsername
         */
        return value;
    }
}
