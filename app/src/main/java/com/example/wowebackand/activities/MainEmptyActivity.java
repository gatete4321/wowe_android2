package com.example.wowebackand.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainEmptyActivity extends AppCompatActivity
{
    SharedPreferences preferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);

        Intent activityIntent;
        if (preferences.contains("clientId") && preferences.contains("token")){//hano tura chekinga token man
            activityIntent=new Intent(this, MainActivity.class);
        }
        else {
            activityIntent=new Intent(this, SecActivity.class);
        }
        startActivity(activityIntent);
        finish();
    }
}
