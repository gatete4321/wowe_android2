package com.example.wowebackand.respostory;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.example.wowebackand.Retrofit.ClientNet;
import com.example.wowebackand.Retrofit.DoNet;
import com.example.wowebackand.Retrofit.RetrofitService;
import com.example.wowebackand.models.Client;
import com.example.wowebackand.models.ClientForm;
import com.example.wowebackand.models.filters.ClientFilter;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ClientRespostory
{
    public static ClientForm clientForm;
    public static boolean check=false;
    ClientNet net;


    public ClientRespostory() {
        net= RetrofitService.createService(ClientNet.class);
    }

    public ClientForm getClientInfo(ClientFilter filter){
        Call<ClientForm> call=net.getClient(filter);
        DoNet<ClientForm> doNet=new DoNet<>();
        call.enqueue(doNet);
        return doNet.getLiveData().getValue();
    }

    /**
     * it will return the list of technicians
     * @return
     */
    public MutableLiveData<List<Client>> techList(Integer serviceId)
    {
        ClientFilter filter=new ClientFilter();
        filter.setServiceId(serviceId);
        Call<List<Client>> call=net.getListTech(filter);
        DoNet<List<Client>> doNet=new DoNet<>();
        call.enqueue(doNet);
        return doNet.getLiveData();

    }

    public Integer createClient(Client client){
       Call<Integer> value=net.createClient(client);
        DoNet<Integer> doNet=new DoNet<>();
        value.enqueue(doNet);
        return doNet.getLiveData().getValue();
    }

    public void deleteClient(){

    }

    public void uploadPic(){

    }
//
//    public ClientForm login(ClientFilter filter){
//        Call<ClientForm> call=net.getClient(filter);
//        DoNet<ClientForm> doNet=new DoNet<>();
//        call.enqueue(doNet);
//        ClientForm client=doNet.getLiveData().getValue();
//        return client;
//    }

    /**
     * this will update the client password
     * it checks the result if its 1 and say it is true(completed)
     * @param filter
     * @return
     */
    public ClientForm login(ClientFilter filter){
        Call<ClientForm> call;

        call=net.getClient(filter);

        call.enqueue(new Callback<ClientForm>() {

            @Override
            public void onResponse(Call<ClientForm> call, Response<ClientForm> response) {
                clientForm=response.body();
                Log.e("onResponse","muri response");
                return;
            }

            @Override
            public void onFailure(Call<ClientForm> call, Throwable t) {
                Log.e("login","kubera"+t.getMessage());
//                Toast.makeText()`
                return;
            }
        });
        Log.e("main","muri main Thread");
        return clientForm;
    }

    /**
     * ndatekereza iyingiy izajya ya updatinga profile yose niba nagihindutse
     * ikibazo ni riya static check itarimo kuba updated vuba ariko nacyo tuzagikemura muri future
     * @param filter
     * @param ops
     * @return
     */

    public boolean updateDatas(ClientFilter filter,Integer ops){
        filter.setClientId(1);//for fake user

        Call<Integer> call;


        switch (ops){
            case 1:
                call=net.updatePassword(filter);
                break;
            case 2:
                call=net.updateClient(filter);
                break;
            default:
                return false;
        }
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()){
                    if (response.body()==1){
                        ClientRespostory.check=true;//the problem nuko bitinda
                    }
                    if (response.body()==20){
                        /**
                         * the password is incorect does not match
                         */
                    }
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                /**
                 * i will toast if the the value is not sucessfully
                 * i want to understand the retrofit if the onFailure runs on the main `thread or background Thread
                 */
            }
        });

        return check;
    }
}


//    public boolean updatePassword(ClientFilter filter){
//        filter.setClientId(1);//for fake user
//
//        Call<ClientForm> call;
//
//        call=net.updatePassword(filter);
//
//        call.enqueue(new Callback<Integer>() {
//            @Override
//            public void onResponse(Call<Integer> call, Response<Integer> response) {
//                if (response.isSuccessful()){
//                    if (response.body()==1){
//                        ClientRespostory.check=true;//the problem nuko bitinda
//                    }
//                    if (response.body()==20){
//                        /**
//                         * the password is incorect does not match
//                         */
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Integer> call, Throwable t) {
//                /**
//                 * i will toast if the the value is not sucessfully
//                 * i want to understand the retrofit if the onFailure runs on the main `thread or background Thread
//                 */
//            }
//        });
//
//        return check;
//    }