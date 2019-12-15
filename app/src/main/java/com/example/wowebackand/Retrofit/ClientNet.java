package com.example.wowebackand.Retrofit;


import com.example.wowebackand.models.Client;
import com.example.wowebackand.models.ClientForm;
import com.example.wowebackand.models.filters.ClientFilter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ClientNet
{
    @POST("public/client/create")
    Call<Integer> createClient(@Body Client client);

    @POST("/delete")
    Call<String> deleteClient(@Body ClientFilter filter);


    @POST("/client/updateinfo")
    Call<Integer> updateClient(@Body ClientFilter filter);


    @POST("/client/login")
    Call<ClientForm> getClient(@Body ClientFilter filter);

    /**
     * get list of techinicians
     * @param filter
     * @return
     */
    @POST("/client/all")
    Call<List<Client>> getListTech(@Body ClientFilter filter);

    @POST("/client/password")
    Call<Integer> updatePassword(@Body ClientFilter filter);


}
