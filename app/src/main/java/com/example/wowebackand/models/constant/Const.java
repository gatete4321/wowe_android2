package com.example.wowebackand.models.constant;

import com.example.wowebackand.R;

import java.util.HashMap;
import java.util.Map;

import static androidx.core.content.ContextCompat.getSystemService;

public  class Const
{
    public static  Integer userId;

    public static String email;

    public static String token;

    public static String userName;

    public static String phone;


    public static final String url="https://wowe3.cfapps.io";//"http://10.0.2.2:9000";

    public static final String urlImage="https://drive.google.com/uc?id=";
//    public static final String urlImage="http://10.0.2.2:9000/public/pic/download?filename=";
    public static final String urlImageId="https://wowe3.cfapps.io/public/pic/downloadId?id=";





    public static String getnotificationAction(int key){
        switch (key){
            case 1:
                return "yakoze appoitement";
            case 2:
                return "techinicie yayemeye";
            case 3:
                return "techinicie yayanze";
            case 4:
                return "umukiliya yayanze";
            case 5:
                return "itariki yageze";
        }
        return "";
    }



    public static int serviceIdImag(int key){
        switch (key){
            case 1:
                return R.drawable.abakanishi;
            case 2:
                return R.drawable.abasuderezi;
            case 3:
                return R.drawable.abubatsi;
            case 4:
                return R.drawable.amazi;
            case 5:
                return R.drawable.electricie;
            case 6:
                return R.drawable.electronic;
            case 7:
                return R.drawable.umubaji;
            case 8:
                return R.drawable.shoferi;
        }
        return R.drawable.abubatsi;
    }

    public static String getServicesIdName(int key){
        switch (key){
            case 1:
                return "gukanika";
            case 2:
                return "gusudira";
            case 3:
                return "kubaka";
            case 4:
                return "amazi";
            case 5:
                return "amashanyarazi";
            case 6:
                return "electronicie";
            case 7:
                return "kubaza";
            case 8:
                return "gutwara";
        }
        return "umwuga";
    }
    public static String namesAction(int key){
        switch (key){
            case 1:
                return "";
            case 2:
                return "accepted";
            case 3:
                return "canceled";
            case 4:
                return "client canceled";
            case 5:
                return "Today";
        }
        return "";
    }
    /**
     * 0:completed
     * 1:pending
     * 99:deleted
     */


}
