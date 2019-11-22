package com.example.wowebackand.viewModel;

import android.app.Application;

import com.example.wowebackand.models.Client;
import com.example.wowebackand.respostory.ClientRespostory;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

/**
 * nago irarangira
 */
public class ProvideViewModel extends AndroidViewModel {

    private ClientRespostory respostory;

    public ProvideViewModel(@NonNull Application application) {
        super(application);
        respostory=new ClientRespostory(application);
    }
    public MutableLiveData<List<Client>> getListClientLivedata(Integer serviceId){
        return respostory.techList(serviceId);
    }
}
