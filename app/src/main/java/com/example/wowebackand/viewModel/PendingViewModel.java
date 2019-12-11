package com.example.wowebackand.viewModel;

import android.app.Application;
import android.os.AsyncTask;

import com.example.wowebackand.models.Appoitement;
import com.example.wowebackand.models.filters.AppNotFilter;
import com.example.wowebackand.respostory.AppoitementRespostory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


public class PendingViewModel extends AndroidViewModel
{

    AppoitementRespostory respostory;
    LiveData<List<Appoitement>> liveData;
    List<Appoitement> appoitements;

    public PendingViewModel(@NonNull Application application) {
        super(application);
        respostory=new AppoitementRespostory(application);
    }

    public void deleteAppoitement(Appoitement appoitement){
        respostory.deleteAppoitement(appoitement);
    }

    public LiveData<List<Appoitement>> getLiveData(){
        AppNotFilter filter=new AppNotFilter();
        filter.setClientId(1);
        filter.setStatus(0);
//        appoitements=respostory.getAppoitements(filter);
        liveData=  respostory.getAppoitements(filter);
        return liveData;
    }
    public void  PendToComp(List<Appoitement> appoitements){
        /**
         * turaza guchekinga ko ibikora rimwe gusa tu
         */
        new PenToCom(respostory).execute(appoitements);
    }
}

class PenToCom extends AsyncTask<List<Appoitement>,Void,Void> {

    AppoitementRespostory respostory;
    List<Appoitement> appoitements;
    List<Integer> appoitementIds;

    public PenToCom(AppoitementRespostory respostory) {
        this.respostory = respostory;
    }

    @Override
    protected Void doInBackground(List<Appoitement>... lists) {
        appoitements=lists[0];
        appoitementIds=new ArrayList<>();
//       Integer d= appoitements.stream().filter(()->{
//
//        }).count();
        /**
         * niba itariki iri nyuma yuno munsi irahita ijya muri completed appoitements
         */
        for (Appoitement appoitement:appoitements){
            if (appoitement.getDoneTime().before(new Date())){
                appoitementIds.add(appoitement.getAppoitementId());
            }
            respostory.updatePendToComp(appoitementIds);
        }
        return null;
    }
}