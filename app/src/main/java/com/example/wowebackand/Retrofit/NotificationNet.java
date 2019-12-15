package com.example.wowebackand.Retrofit;



import com.example.wowebackand.models.Notification;
import com.example.wowebackand.models.NotificationForm;
import com.example.wowebackand.models.filters.AppNotFilter;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NotificationNet
{
    @POST("/notification/all")
    public Call<List<Notification>> getNotifications(@Body AppNotFilter filter);

    @POST("/notification/create")
    public Call<Integer> createNotification(@Body NotificationForm notificationForm);

    @POST("/notification/delete")
    public Call<Integer> deleteNotification(@Body Notification filter);

}
