package com.example.wowebackand.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

/**
 * edited 19/10/2019
 * clientId ukugirango fragment zose ziyisangire
 * token kugirango nze kuoherez kuri service zose
 */
public class MainViewModel extends AndroidViewModel {



    private static Integer clientId;

    private static String token;//token nza gukorsha hose
    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public static Integer getClientId() {
        return clientId;
    }

    public static void setClientId(Integer clientId) {
        MainViewModel.clientId = clientId;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        MainViewModel.token = token;
    }
}
