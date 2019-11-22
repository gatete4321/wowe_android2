package com.example.wowebackand.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wowebackand.R;
import com.example.wowebackand.models.filters.ClientFilter;
import com.example.wowebackand.respostory.ClientRespostory;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyProfile extends Fragment {

    Dialog dialog;

    ImageView accountImage, addImage;

    TextView email, userName, phone, password;

    ClientRespostory respostory;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_profile, container, false);
//        imageView=view.findViewById(R.id.account_image);
//        Picasso.with(getContext()).load(R.drawable.header).into(imageView);
        initializeViews(view);
        initializeData();

        email.setOnClickListener((view1) -> {
            dialogEmail();
        });
        userName.setOnClickListener((view1) -> {
            dialogUserName();
        });
        phone.setOnClickListener((view1) -> {
            dialogPhone();
        });
        password.setOnClickListener((view1) -> {
            dialogPassword();
        });

        return view;
    }

    /**
     * for initializing data in the fields
     * but i yhink i will use the viewmodel to save data for long time
     */
    private void initializeData() {
    }

    /**
     * for initializing views
     */
    private void initializeViews(View view) {
        accountImage = view.findViewById(R.id.account_image);
        addImage = view.findViewById(R.id.account_add_image);
        email = view.findViewById(R.id.account_email);
        userName = view.findViewById(R.id.account_username);
        phone = view.findViewById(R.id.account_phone);
        password = view.findViewById(R.id.account_password);
    }


    public void dialogPassword() {
        EditText recentPassword, password1, password2;
        TextView cancel;
        Button submit;
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialbox_myprofile_password);
        recentPassword = dialog.findViewById(R.id.dialbox_recent_password);
        password1 = dialog.findViewById(R.id.dialbox_new_password);
        password2 = dialog.findViewById(R.id.dialbox_retype_password);
        cancel = dialog.findViewById(R.id.dialog_password_cancel);
        submit = dialog.findViewById(R.id.dialbox_password_submit);

        cancel.setOnClickListener((view) -> {
            dialog.dismiss();
        });

        submit.setOnClickListener((view) -> {
            if (recentPassword.getText().toString().trim().isEmpty() ||
                    password1.getText().toString().trim().isEmpty() ||
                    password2.getText().toString().trim().isEmpty()) {
                Toast.makeText(getContext(), "please fill the fields", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!(password1.getText().toString().equals(password2.getText().toString()))) {
                Toast.makeText(getContext(), "input password does not match", Toast.LENGTH_SHORT).show();
                return;
            }

            if (updatePassword(password1.getText().toString(), recentPassword.getText().toString())) {
                ClientRespostory.check=false;
                dialog.dismiss();
            }
                Toast.makeText(getContext(), "recent password does not exist", Toast.LENGTH_SHORT).show();
        });
        dialog.show();
    }

    /**
     * for changing the phone
     */
    public void dialogPhone() {
        TextView cancel, recentPhone;
        EditText newPhone;
        Button submit;

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialbox_myprofile_phone);
        cancel = dialog.findViewById(R.id.dialog_phone_cancel);
        recentPhone = dialog.findViewById(R.id.dialbox_recent_phone);
        newPhone = dialog.findViewById(R.id.dialbox_new_phone);
        submit = dialog.findViewById(R.id.dialbox_phone_submit);


        recentPhone.setText("07889867");//iyituraza gufata telephone yarihari maze tuyishyire hariya
        cancel.setOnClickListener((view) -> {
            dialog.dismiss();
        });
        submit.setOnClickListener((view) -> {
            if (newPhone.getText().toString().trim().isEmpty()) {
                Toast.makeText(getContext(), "please fill real data", Toast.LENGTH_SHORT).show();
                return;
            }
            if (updateClientInfo(newPhone.getText().toString(),2)){
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    void dialogEmail() {
        TextView cancel, recentThing;
        EditText newThing;
        Button submit;
        ImageView recentImage, newImage;
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialbox_myprofile_others);
        cancel = dialog.findViewById(R.id.dialog_others_cancel);
        recentThing = dialog.findViewById(R.id.dialbox_recent_thing);
        newThing = dialog.findViewById(R.id.dialbox_new_thing);
        submit = dialog.findViewById(R.id.dialbox_others_submit);
        recentImage = dialog.findViewById(R.id.dialbox_recent_thing_image);
        newImage = dialog.findViewById(R.id.dialbox_new_thing_image);
        recentImage.setImageResource(R.drawable.ic_email_account);
        newImage.setImageResource(R.drawable.ic_email_account);

        recentThing.setText("dgatete4321@gmail.com");//nayo turaya pudatinga tuyivanye muri data

        cancel.setOnClickListener((view) -> {
            dialog.dismiss();
        });
        submit.setOnClickListener((view) -> {
            if (newThing.getText().toString().trim().isEmpty()) {
                Toast.makeText(getContext(), "please fill real data", Toast.LENGTH_SHORT).show();
                return;
            }
            if (updateClientInfo(newThing.getText().toString(),3)){
             dialog.dismiss();
            }
        });

        dialog.show();
    }


    void dialogUserName() {
        TextView cancel, recentThing;
        EditText newThing;
        Button submit;
        ImageView recentImage, newImage;
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialbox_myprofile_others);
        cancel = dialog.findViewById(R.id.dialog_others_cancel);
        recentThing = dialog.findViewById(R.id.dialbox_recent_thing);
        newThing = dialog.findViewById(R.id.dialbox_new_thing);
        submit = dialog.findViewById(R.id.dialbox_others_submit);
        recentImage = dialog.findViewById(R.id.dialbox_recent_thing_image);
        newImage = dialog.findViewById(R.id.dialbox_new_thing_image);
        recentImage.setImageResource(R.drawable.ic_username_account);
        newImage.setImageResource(R.drawable.ic_username_account);

        recentThing.setText("dgatete432");//nayo turaya pudatinga tuyivanye muri data

        cancel.setOnClickListener((view) -> {
            dialog.dismiss();
        });
        submit.setOnClickListener((view) -> {
            if (newThing.getText().toString().trim().isEmpty()) {
                Toast.makeText(getContext(), "please fill real data", Toast.LENGTH_SHORT).show();
                return;
            }
            if (updateClientInfo(newThing.getText().toString(),1)){
                dialog.dismiss();
            }
        });
        dialog.show();
    }



    /**
     * turohereza recentPassword hamwe na new password back ira chekinga ko recentpassword ariyo  maze ya updatinge
     * hano tuzahita twa pudatinga password
     *
     * @param password
     * @param recent
     */
    private boolean updatePassword(String password, String recent) {
        boolean check = false;

        ClientFilter filter = new ClientFilter();
        filter.setRecentPassword(recent);
        filter.setPassword(password);

        respostory = new ClientRespostory(null);
        check=respostory.updateDatas(filter,1);
        return check;
    }

    private boolean updateClientInfo(String data, int ops) {
        boolean check = false;

        ClientFilter filter = new ClientFilter();
        switch (ops){
            case 1:
                filter.setUsername(data);
                break;
            case 2:
                filter.setPhoneNumber(data);
                break;
            case 3:
                filter.setEmail(data);
        }

        respostory = new ClientRespostory(null);
        check=respostory.updateDatas(filter,2);
        ClientRespostory.check=false;
        return check;
    }


}

