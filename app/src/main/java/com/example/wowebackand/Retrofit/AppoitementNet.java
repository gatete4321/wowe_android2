package com.example.wowebackand.Retrofit;

import android.content.Intent;

import com.example.wowebackand.models.Appoitement;
import com.example.wowebackand.models.AppoitementForm;
import com.example.wowebackand.models.constant.Const;
import com.example.wowebackand.models.filters.AppNotFilter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AppoitementNet
{

    @POST("/app/appoitement")
    Call<Appoitement> getAppoitement(@Body AppNotFilter filter);

//    @Headers("Authorization"+ Const.)
    @POST("/app/all")
    Call<List<Appoitement>> getAllAppoitements(@Body AppNotFilter filter);

//    Call<List<Appoitement>> getCompletedAppoitements(Integer clientId);
//
//    Call<List<Appoitement>> getPendingAppoitements(Integer clientId);

    @POST("/app/create")
    Call<Integer> createAppoitement(@Body AppoitementForm appoitementForm);

    @POST("/app/delete")
    Call<String> deleteAppoitement(@Body AppNotFilter filter);


    Call<String> rateAppoitement(@Body AppNotFilter filter);

    Call<String> feedBack(@Body String feedBack);

    @POST()
    Call<Appoitement> updateAppoitent(@Body Appoitement appoitement);

    @POST("/app/techImage")
    Call<String> getTechImage(@Body Integer clientId);
}
//    @Header("Authorization") String header,