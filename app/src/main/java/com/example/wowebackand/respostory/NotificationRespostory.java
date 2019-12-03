package com.example.wowebackand.respostory;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.example.wowebackand.Retrofit.DoNet;
import com.example.wowebackand.Retrofit.NotificationNet;
import com.example.wowebackand.Retrofit.RetrofitService;
import com.example.wowebackand.dao.NotificationDao;
import com.example.wowebackand.dao.WoweDatabase;
import com.example.wowebackand.models.Notification;
import com.example.wowebackand.models.NotificationForm;
import com.example.wowebackand.models.filters.AppNotFilter;

import java.util.List;
import java.util.concurrent.ExecutionException;

import androidx.lifecycle.LiveData;

import retrofit2.Call;

public class NotificationRespostory
{
    public static int e = 0;
    public static int lastNOID;
    NotificationNet net;
    NotificationDao dao;
    Application application;

    public NotificationRespostory(Application application) {
        WoweDatabase woweDatabase= WoweDatabase.getInstance(application);
        dao=woweDatabase.notificationDao();
        net= RetrofitService.createService(NotificationNet.class);
        this.application = application;
    }

    public LiveData<List<Notification>> getNotifications(AppNotFilter filter){
      int l= getLastNotficationId();
//        new Handler().postDelayed(()->{
            filter.setId(l);

            insertNotificationDataInDatabase(filter,dao,net);
//        },2000);

        return dao.getAll();
    }

    public Integer createNotification(NotificationForm notification){
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


    public static void insertNotificationDataInDatabase(AppNotFilter filter, NotificationDao dao, NotificationNet notificationNet) {
        e++;
        if (e <= 1) {
            Call<List<Notification>> listCall = notificationNet.getNotifications(filter);
            DoNet<List<Notification>> net = new DoNet(null,dao);
            listCall.enqueue(net);
        }
    }


    int getLastNotficationId(){
        Integer l = 0;
        try {
          l=new GetLastNotificationItem(dao).execute().get();
        } catch (ExecutionException ex) {
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return l;
    }



    private static class GetLastNotificationItem extends AsyncTask<Void,Void,Integer> {

        private NotificationDao dao;

        public GetLastNotificationItem(NotificationDao notificationDao) {
            this.dao= notificationDao;
        }


        @Override
        protected Integer doInBackground(Void... voids) {
            Integer last=dao.getLastNotificationId();
            if (last==null){
                last=0;
            }
            return last;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            lastNOID=integer;
        }
    }

}
