package com.example.wowebackand.activities;


import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.wowebackand.R;



public class SecActivity extends AppCompatActivity {

    Toolbar toolbar;

    public static NavController controller;
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);
        layout=findViewById(R.id.my_linear_layout);
        controller= Navigation.findNavController(this,R.id.nav_host);
        toolbar = findViewById(R.id.sec_toolbar);
        setSupportActionBar(toolbar);
//        controller.navigate(R.id.FirstFragment);
    }


    /**
     * i am gona to force no back presed
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
