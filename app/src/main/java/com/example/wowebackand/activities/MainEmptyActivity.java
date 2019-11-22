package com.example.wowebackand.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainEmptyActivity extends AppCompatActivity
{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent activityIntent;
        if (true){//hano tura chekinga token man
            activityIntent=new Intent(this, SecActivity.class);
        }
        else {
            activityIntent=new Intent(this, MainActivity.class);
        }
        startActivity(activityIntent);
        finish();
    }
}
