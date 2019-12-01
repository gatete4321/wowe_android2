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
    @POST("/public/notification/all")
    public Call<List<Notification>> getNotifications(@Body AppNotFilter filter);

    @POST("/public/notification/create")
    public Call<Integer> createNotification(@Body NotificationForm notificationForm);

    @POST("/public/notification/delete")
    public Call<Integer> deleteNotification(@Body Notification filter);

}
