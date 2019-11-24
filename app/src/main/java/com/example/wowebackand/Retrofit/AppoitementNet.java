package com.example.wowebackand.Retrofit;

import com.example.wowebackand.models.Appoitement;
import com.example.wowebackand.models.filters.AppNotFilter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AppoitementNet
{
    @POST("public/app/appoitement")
    Call<Appoitement> getAppoitement(@Body AppNotFilter filter);

    @POST("/public/app/all")
    Call<List<Appoitement>> getAllAppoitements(@Body AppNotFilter filter);

//    Call<List<Appoitement>> getCompletedAppoitements(Integer clientId);
//
//    Call<List<Appoitement>> getPendingAppoitements(Integer clientId);

    @POST("public/app/create")
    Call<String> createAppoitement(@Body Appoitement appoitement);

    @POST("public/app/delete")
    Call<String> deleteAppoitement(@Body AppNotFilter filter);


    Call<String> rateAppoitement(@Body AppNotFilter filter);

    Call<String> feedBack(@Body String feedBack);

    @POST()
    Call<Appoitement> updateAppoitent(@Body Appoitement appoitement);
}
