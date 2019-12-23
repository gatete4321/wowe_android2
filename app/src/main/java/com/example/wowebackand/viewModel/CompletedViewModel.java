package com.example.wowebackand.viewModel;

import android.app.Application;
import android.content.Context;

import com.example.wowebackand.models.Appoitement;
import com.example.wowebackand.models.filters.AppNotFilter;
import com.example.wowebackand.respostory.AppoitementRespostory;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


public class CompletedViewModel extends AndroidViewModel {

    AppoitementRespostory respostory;
    LiveData<List<Appoitement>> liveData;
    List<Appoitement> appoitements;
    Context context;

    public CompletedViewModel(@NonNull Application application) {
        super(application);
        respostory = new AppoitementRespostory(application);
        this.context=application;
    }

    public void deleteAppoitement(Appoitement appoitement) {
        respostory.deleteAppoitement(appoitement);
    }

    public LiveData<List<Appoitement>> getLiveData() {
        AppNotFilter filter = new AppNotFilter();
        filter.setClientId(1);
        filter.setStatus(1);
//        appoitements=respostory.getAppoitements(filter);
        liveData = respostory.getAppoitements(filter,context);
        return liveData;
    }
}
