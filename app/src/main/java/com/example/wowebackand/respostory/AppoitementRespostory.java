package com.example.wowebackand.respostory;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.wowebackand.Retrofit.AppoitementNet;
import com.example.wowebackand.Retrofit.DoNet;
import com.example.wowebackand.Retrofit.RetrofitService;
import com.example.wowebackand.dao.AppoitementDao;
import com.example.wowebackand.dao.WoweDatabase;
import com.example.wowebackand.models.Appoitement;
import com.example.wowebackand.models.AppoitementForm;
import com.example.wowebackand.models.constant.Const;
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
//    public Appoitement getAppoitement(AppNotFilter filter) {
//        Call<Appoitement> appoitementCall = appoitementNet.getAppoitement(filter);
//        DoNet net = new DoNet<Appoitement>();
//        appoitementCall.enqueue(net);
//        return (Appoitement) net.getLiveData().getValue();
//    }

    /**
     * list of completed appoitements and pending appoitements
     * ndakoresha DoNet<Object.class>().class mu gukora operation zose za network
     *
     * @return
     */
    public LiveData<List<Appoitement>> getAppoitements(AppNotFilter filter, Context context) {

        if (filter.getStatus() == 1) {
            filter.setStatus(null);
            getLastAppoitementId();
            filter.setId(lastId);
            insertDataInDatabase(filter, dao, appoitementNet,context);
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
     * @param context
     */
    public static void insertDataInDatabase(AppNotFilter filter, AppoitementDao dao, AppoitementNet appoitementNet, Context context) {
        i++;
        if (i <= 1) {
//            String token= "Bearer "+Const.token;
            Call<List<Appoitement>> listCall = appoitementNet.getAllAppoitements(filter);
            DoNet<List<Appoitement>> net = new DoNet(dao,null,context);
            listCall.enqueue(net);
        }
    }

    /**
     *this is gona be used when appoitement canceled it is deleted online
     */
    public void cancelAppoitement(Integer appoitementId) {
        AppNotFilter filter=new AppNotFilter();
        filter.setAppoitementId(appoitementId);
        Call<String> stringCall=appoitementNet.deleteAppoitement(filter);
        DoNet<String> net=new DoNet<>();
        stringCall.enqueue(net);
    }

    /**
     * this is used to delete appoitement in database
     * @param appoitement
     */
    public void deleteAppoitement(Appoitement appoitement){
        new DeleteAppoitement(dao).execute(appoitement);
    }

    public void updatePendToComp(List<Integer> integers){
        new UpdatePendiToCompl(dao).execute(integers);
    }

    public void feedBack() {

    }

    public void getTechImage(){

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


    private static class DeleteAppoitement extends AsyncTask<Appoitement,Void,Void>{
        private AppoitementDao appoitementDao;

        public DeleteAppoitement(AppoitementDao appoitementDao) {
            this.appoitementDao = appoitementDao;
        }

        @Override
        protected Void doInBackground(Appoitement... appoitements) {
            appoitementDao.deleteAppoitement(appoitements[0]);
            return null;
        }
    }

    private static class UpdatePendiToCompl extends AsyncTask<List<Integer>,Void,Void>{
        private AppoitementDao dao;

        public UpdatePendiToCompl(AppoitementDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(List<Integer>... lists) {
            dao.updatePenToCom(lists[0]);
            return null;
        }
    }


}
