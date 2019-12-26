package com.example.wowebackand.viewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wowebackand.models.Notification;
import com.example.wowebackand.models.constant.Const;
import com.example.wowebackand.models.filters.AppNotFilter;
import com.example.wowebackand.respostory.NotificationRespostory;

import java.util.List;

/**
 * the view model for notifications
 */
public class NotificationViewModel extends AndroidViewModel{

    NotificationRespostory respostory;
    LiveData<List<Notification>> notificationLiveData;
    List<Notification> notifications;
    Context context;
    public NotificationViewModel(@NonNull Application application) {
        super(application);
        respostory=new NotificationRespostory(application);
        this.context=application;

    }

    public LiveData<List<Notification>> getNotificationLiveData(){
        AppNotFilter filter=new AppNotFilter();
        filter.setClientId(Const.userId);//tuzakurura izo yakorewe gusa apana izo yakoze
        notificationLiveData= respostory.getNotifications(filter,context);
        return notificationLiveData;

    }


    public void deleteNot(Notification notification){
        respostory.deleteNotification(notification);
    }


}