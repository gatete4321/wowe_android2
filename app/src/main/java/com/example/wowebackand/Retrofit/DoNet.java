package com.example.wowebackand.Retrofit;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.wowebackand.dao.AppoitementDao;
import com.example.wowebackand.dao.NotificationDao;
import com.example.wowebackand.models.Appoitement;
import com.example.wowebackand.models.Notification;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * iyi class niyo gukora network ops kuyind thread
 *
 * @param <T>
 */
public class DoNet<T> implements Callback<T> {

    MutableLiveData<T> liveData = new MutableLiveData<>();

    public T t;

    public AppoitementDao dao;

    public NotificationDao notificationDao;

    public Context context;

    public DoNet(AppoitementDao dao,NotificationDao notificationDao,Context context) {
        this.dao = dao;
        this.notificationDao=notificationDao;
        this.context=context;
    }

    public DoNet() {
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        if (!response.isSuccessful()) {
//            Log.e("unsucesfull", "code error:" + response.code());

            switch (response.code()){
                case 404:
                    makeToast("not found");
                    return;
                case 401:
                    makeToast("please login again");
                    return;
            }

            return;
        }
        Log.e("Donet", "nahageze tu");
        t = response.body() != null ? response.body() : response.body();

        if (dao != null) {
            Log.e("DONET", "nazibitse appoitements");
            new InsertNAppoitementAsyncTask(dao).execute((List<Appoitement>) t);
        }

        if (notificationDao!=null){
            Log.e("NOT", "nazibitse Notificatiions");
            new InsertNNotificationAsyncTask(notificationDao).execute((List<Notification>) t);
        }

        liveData.postValue(response.body());
        return;
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.e("onFailure", t.getMessage());
        return;
    }


    public MutableLiveData<T> getLiveData() {
        return liveData;
    }

    public void setLiveData(MutableLiveData<T> liveData) {
        this.liveData = liveData;
    }

    public void makeToast(String message){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }


    private static class InsertNAppoitementAsyncTask extends AsyncTask<List<Appoitement>, Void, Void> {

        private AppoitementDao appoitementDao;

        public InsertNAppoitementAsyncTask(AppoitementDao appoitementDao) {
            this.appoitementDao = appoitementDao;
        }


        @Override
        protected Void doInBackground(List<Appoitement>... lists) {
            appoitementDao.insertAppoitement(lists[0]);
            return null;
        }
    }

    private static class InsertNNotificationAsyncTask extends AsyncTask<List<Notification>, Void, Void> {

        private NotificationDao notificationDao;

        public InsertNNotificationAsyncTask(NotificationDao notificationDao) {
            this.notificationDao=notificationDao;
        }


        @Override
        protected Void doInBackground(List<Notification>... lists) {
            notificationDao.InsertNotifications(lists[0]);
            return null;
        }
    }


}
