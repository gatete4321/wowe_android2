package com.example.wowebackand.respostory;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.wowebackand.Retrofit.AppoitementNet;
import com.example.wowebackand.Retrofit.DoNet;
import com.example.wowebackand.Retrofit.RetrofitService;
import com.example.wowebackand.dao.AppoitementDao;
import com.example.wowebackand.dao.WoweDatabase;
import com.example.wowebackand.models.Appoitement;
import com.example.wowebackand.models.AppoitementForm;
import com.example.wowebackand.models.filters.AppNotFilter;

import java.util.List;

import retrofit2.Call;

public class AppoitementRespostory {

    public static int i = 0;

    public static int lastId;

    private AppoitementNet appoitementNet;
    private AppoitementDao dao;
    private LiveData<List<Appoitement>> allAppoitements;

    public AppoitementRespostory(Application application) {
        appoitementNet = RetrofitService.createService(AppoitementNet.class);

        WoweDatabase woweDatabase = WoweDatabase.getInstance(application);
        dao = woweDatabase.appoitementDao();
    }

    /**
     * nago nanjiza muri database kuko nyeneye primary key yo kuri server
     * create an appoitement
     *
     * @param appoitement
     * @return
     */
    public Integer insertAppoitement(AppoitementForm appoitement) {
        Integer result;
        Call<Integer> call = appoitementNet.createAppoitement(appoitement);
        DoNet<Integer> net = new DoNet<>();
        call.enqueue(net);
        result = net.getLiveData().getValue();
        return result;
    }

    /**
     * update an appoitement
     *
     * @return
     */
    public Appoitement updateAppoitement() {
        return null;
    }

    /**
     * get appoitement by id,clientId,techId,
     *
     * @param filter
     * @return
     */
    public Appoitement getAppoitement(AppNotFilter filter) {
        Call<Appoitement> appoitementCall = appoitementNet.getAppoitement(filter);
        DoNet net = new DoNet<Appoitement>();
        appoitementCall.enqueue(net);
        return (Appoitement) net.getLiveData().getValue();
    }

    /**
     * list of completed appoitements and pending appoitements
     * ndakoresha DoNet<Object.class>().class mu gukora operation zose za network
     *
     * @return
     */
    public LiveData<List<Appoitement>> getAppoitements(AppNotFilter filter) {

        if (filter.getStatus() == 1) {
            filter.setStatus(null);
            getLastAppoitementId();
            filter.setId(lastId);
            insertDataInDatabase(filter, dao, appoitementNet);
            filter.setStatus(1);
            /**
             * ibi nabikoze kubera ko nshaka ko i fetchinga data kuri online 1 gusa kandi kuri completed fragment yonyine
             */
        }

        allAppoitements = dao.getAll(filter.getStatus(), filter.getClientId());

        return allAppoitements;
    }

    /**
     * iyi icyo idufasha nu ku insertinga all appoitement muri data base
     *
     * @param filter
     */
    public static void insertDataInDatabase(AppNotFilter filter, AppoitementDao dao, AppoitementNet appoitementNet) {
        i++;
        if (i <= 1) {
            Call<List<Appoitement>> listCall = appoitementNet.getAllAppoitements(filter);
            DoNet<List<Appoitement>> net = new DoNet(dao,null);
            listCall.enqueue(net);
        }
    }

    /**
     *
     */
    public void deleteAppoitement(Integer appoitementId) {
        AppNotFilter filter=new AppNotFilter();
        filter.setAppoitementId(appoitementId);
        Call<String> stringCall=appoitementNet.deleteAppoitement(filter);
        DoNet<String> net=new DoNet<>(null,null);
        stringCall.enqueue(net);
    }

    public void feedBack() {

    }

    public void rateAppoitement() {

    }

    public void getLastAppoitementId() {

        new GetLastItem(dao).execute();
    }

    private static class GetLastItem extends AsyncTask<Void, Void, Integer> {

        private AppoitementDao appoitementDao;

        public GetLastItem(AppoitementDao appoitementDao) {
            this.appoitementDao = appoitementDao;
        }


        @Override
        protected Integer doInBackground(Void... voids) {
            Integer last = appoitementDao.getLastAppoitementId();
            if (last == null) {
                last = 0;
            }
            return last;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            lastId = integer;
        }
    }


}
