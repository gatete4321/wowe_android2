package com.example.wowebackand.respostory;

import android.app.Application;

import com.example.wowebackand.Retrofit.DoNet;
import com.example.wowebackand.Retrofit.NotificationNet;
import com.example.wowebackand.Retrofit.RetrofitService;
import com.example.wowebackand.models.Notification;
import com.example.wowebackand.models.NotificationForm;
import com.example.wowebackand.models.filters.AppNotFilter;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;

public class NotificationRespostory
{
    NotificationNet net;

    Application application;

    public NotificationRespostory(Application application) {
        net= RetrofitService.createService(NotificationNet.class);
        this.application = application;
    }

    public LiveData<List<Notification>> getNotifications(AppNotFilter filter){
        Call<List<Notification>> call=net.getNotifications(filter);
        DoNet<List<Notification>> doNet=new DoNet();
        call.enqueue(doNet);
        return doNet.getLiveData();
    }

    public Integer insertNotification(NotificationForm notification){
        Call<Integer> call=net.createNotification(notification);
        DoNet<Integer> doNet=new DoNet<Integer>();
        call.enqueue(doNet);
        return  doNet.getLiveData().getValue();
    }

    public Integer deleteNotification(Notification notification){

        Call<Integer> call=net.deleteNotification(notification);
        DoNet<Integer> doNet=new DoNet<>();
        call.enqueue(doNet);
        return doNet.getLiveData().getValue();
    }

    public Integer updateView(Notification notification){

        Call<Integer> call=net.updateView(notification);
        DoNet<Integer> doNet=new DoNet<>();
        call.enqueue(doNet);
        return doNet.getLiveData().getValue();
    }

}
