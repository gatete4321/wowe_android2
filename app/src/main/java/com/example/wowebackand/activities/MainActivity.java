package com.example.wowebackand.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.ui.NavigationUI;

import com.example.wowebackand.R;

import com.example.wowebackand.models.constant.Const;
import com.example.wowebackand.respostory.AppoitementRespostory;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences sharedPreferences;
    Intent intent;


    Dialog dialog;

    public Toolbar toolbar;

    ActionBarDrawerToggle toggle;

    public DrawerLayout drawerLayout;

    public static NavController navController;

    public NavigationView navigationView;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = findViewById(R.id.my_linear_layout);
        setupNavigation();
        initializeUser();

    }

    private void setupNavigation() {

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        navigationView = findViewById(R.id.navigationView);


        drawerLayout = findViewById(R.id.drawer_layout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);

        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(this);

    }


    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(drawerLayout, Navigation.findNavController(this, R.id.nav_host_fragment));
    }


    @Override
    public void onBackPressed() {
//        if (drawerLayout != null) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
//            }
        }
            else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        menuItem.setChecked(true);

        drawerLayout.closeDrawers();

        int id = menuItem.getItemId();

        switch (id) {

            case R.id.first:
                navController.navigate(R.id.defaultFragment);
                //             initializeViewPager();
                break;

            case R.id.second:
                navController.navigate(R.id.notification);

                break;

            case R.id.third:
                navController.navigate(R.id.MyProfileFrag);
                break;
            case R.id.about_us_menu:
                navController.navigate(R.id.aboutUsFragment);
                break;

            case R.id.logout_menu:
                logoutP();
//                TestNavFragment.controller.navigate(R.id.loginFragment);
//                navController.navigate(R.id.testNavigation);
                break;
        }
        return true;

    }

    void logoutP() {
        TextView logout, cancel;

        sharedPreferences = getSharedPreferences("userDetails", MODE_PRIVATE);

        intent = new Intent(MainActivity.this, SecActivity.class);

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialbox_logout);
        logout = dialog.findViewById(R.id.real_logout);
        cancel = dialog.findViewById(R.id.cancel_logout);
        cancel.setOnClickListener((view) -> {
            dialog.dismiss();
        });
        logout.setOnClickListener((view) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();

            Const.userName=null;
            Const.phone=null;
            Const.userId=null;
            Const.token=null;
            Const.email=null;

            startActivity(intent);
            /**
             * tuzahasyira code zo ku loginga out
             */

            Toast.makeText(this, "log out", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });
        dialog.show();
    }

    /**
     * i am gona to get all shared preference and assign to they values
     */
    public void initializeUser() {
//        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        sharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
//        if (sharedPreferences.contains("token") && sharedPreferences.contains("username") && sharedPreferences.contains("clientId")) {
            Const.token = sharedPreferences.getString("token", "token");
            Const.email = sharedPreferences.getString("email", "email");
            Const.userId = sharedPreferences.getInt("clientId", 0);
            Const.userName = sharedPreferences.getString("userName", "user");
            Const.phone = sharedPreferences.getString("phone", "078800000000");
            Log.e("mainActivity", "initializing shared preference");
//            return true;
//        } else
//            return false;
    }
}
