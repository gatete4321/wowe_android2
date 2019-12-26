package com.example.wowebackand.views;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wowebackand.R;
import com.example.wowebackand.models.Appoitement;
import com.example.wowebackand.models.constant.Const;
import com.example.wowebackand.respostory.AppoitementRespostory;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static androidx.core.content.ContextCompat.getSystemService;

public class Completed_full_appoitement extends Fragment
{
    AppoitementRespostory respostory;
    Context context;
    Appoitement appoitement;

    Bundle bundle;

    Button submit,delete;
    TextView createDate,serviceName, doneTime,techName,description;
    EditText feedback;
    ImageView techPic;
    RatingBar ratingBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.display_completed_appoitement_view,container,false);
        context=getContext();
        initializeViews(view);
        initializeData();

        submit.setOnClickListener((view1)->{
            /**
             * niba ratings yari yarizihashyize naggo yemerewe
             */
//            if (!new Appoitement().getRate().equals(null)){
                /**
                 * dont update the rates
                 */
//            }
//            if (!new Appoitement().getFeedBack().equals(null)){
                /**
                 * dont update the feedback
                 */
//            }
//            if (feedback.getText().toString().trim().equals(null)&&
//                    ratingBar.getRating()==0){
//                Toast.makeText(getActivity(),"please fill data",Toast.LENGTH_SHORT).show();
//                return;
//            }
        });
        delete.setOnClickListener((view1)->{
            if (isNetworkAvaible()) {
                respostory = new AppoitementRespostory(null);
                respostory.cancelAppoitement(appoitement.getAppoitementId());
                Toast.makeText(getActivity(), "appoitement deleted on server", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(getActivity(),"enable to connect",Toast.LENGTH_SHORT).show();
        });




        return view;
    }

    private void initializeData() {
        bundle=getArguments();
        appoitement=bundle.getParcelable("completed");
        if (!appoitement.equals(null)){
            createDate.append(appoitement.getDoneTime().getDay()+"/"+appoitement.getDoneTime().getMonth()+"/"+(1900+appoitement.getDoneTime().getYear()));
            serviceName.append(Const.getServicesIdName(appoitement.getServiceId()));
            doneTime.append(appoitement.getDoneTime().getDay()+"/"+appoitement.getDoneTime().getMonth()+"/"+(1900+appoitement.getDoneTime().getYear()));
            description.append(appoitement.getDescription());
            techName.append(appoitement.getTechName());
            if (isNetworkAvaible()){
                String techImage=getTechImage(appoitement.getClientId());
                initializeImages(techImage,techPic);
            }
            else {
                techPic.setImageResource(Const.serviceIdImag(appoitement.getServiceId()));
            }



            if (appoitement.getRate()!=null){
                ratingBar.setRating(appoitement.getRate());
            }
        }
    }

    private String getTechImage(Integer clientId) {
        return null;
    }

    /**
     * for initializing data
     * @param view
     */
    private void initializeViews(View view) {
        submit=view.findViewById(R.id.app_view_button_submit);
        delete=view.findViewById(R.id.app_view_button_delete);
        createDate =view.findViewById(R.id.app_view_create_date);
        serviceName=view.findViewById(R.id.app_view_service_name);
        doneTime =view.findViewById(R.id.app_view_done_time);
        techName=view.findViewById(R.id.app_view_tech_name);
        description=view.findViewById(R.id.app_view_description);
        feedback=view.findViewById(R.id.app_view_feedback);
        techPic=view.findViewById(R.id.app_image_view);
        ratingBar=view.findViewById(R.id.app_view_ratingBar);
    }

    /**
     * this was because i used my own server
     * @param imageId
     * @param imageView
     */
//    void initializeImages(Integer imageId,ImageView imageView){
//        String full= Const.urlImageId+imageId;
//        Picasso.with(context)
//                .load(full)
//                .fit()
//                .centerInside()
//                .into(imageView);
//    }
void initializeImages(String imageId,ImageView imageView){
    String full= Const.urlImageId+imageId;
    Picasso.with(context)
            .load(full)
            .fit()
            .centerInside()
            .into(imageView);
}
    private boolean isNetworkAvaible() {

        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;

        return connected;

    }
}

//    public boolean checkConnection(){
//        boolean connected = false;
//        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
//        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
//                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
//            //we are connected to a network
//            connected = true;
//        }
//        else
//            connected = false;
//
//        return connected;
//        //##########################33333later
//        ConnectivityManager connectivityManager=
//                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
//        return networkInfo!=null && networkInfo.isConnected();
//    }