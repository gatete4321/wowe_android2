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
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
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

/**
 * ndacyeka kuri layout nyeneye scrolview kugirango bi fitinge tu
 */
public class pending_full_appoitement extends Fragment
{
    AppoitementRespostory respostory;
    Context context;
    Integer phoneNumber;
    String loct,descr;

    TextView serviceName,techName;
    ImageView techPic;
    CalendarView calendarView;
    EditText phone,description,location;
    Button submit,delete;

    Appoitement appoitement;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_edit_appoitement,container,false);
        context=getContext();
        initializeViews(view);

        Bundle bundle=getArguments();
        if (bundle!=null){

            appoitement= bundle.getParcelable("data");

        }
        initializeFakeData();

        delete.setOnClickListener((view1)->{
            respostory=new AppoitementRespostory(null);
            respostory.cancelAppoitement(appoitement.getAppoitementId());

            DefaultFragment.navController.navigate(R.id.pendingFragment);
            Toast.makeText(getContext(),"appoitement canceld ",Toast.LENGTH_SHORT).show();
        });


        return view;
    }


    private void initializeViews(View view) {
        serviceName=view.findViewById(R.id.edit_app_service_name);
        techName=view.findViewById(R.id.edit_app_tech_name);
        techPic=view.findViewById(R.id.edit_app_tech_pic);
        calendarView=view.findViewById(R.id.edit_app_get_calendar);
        phone=view.findViewById(R.id.edit_app_get_phone);
        description=view.findViewById(R.id.edit_app_get_description);
        location=view.findViewById(R.id.edit_app_get_location);
        delete=view.findViewById(R.id.edit_app_button_delete);
        submit=view.findViewById(R.id.edit_app_button_submit);
    }


    private void initializeFakeData() {
        if (appoitement!=null){
            serviceName.setText("gukanika"+appoitement.getClientId());
            techName.setText("gahire"+appoitement.getServiceId());
            techPic.setImageResource(Const.serviceIdImag(appoitement.getServiceId()));
            calendarView.setDate(appoitement.getDoneTime().getTime());
            if (isNetworkAvaible()){
                initializeImages(appoitement.getClientId(),techPic);
            }
            else
                techPic.setImageResource(Const.serviceIdImag(appoitement.getServiceId()));
            Log.e("passing","appoitement is not null");
            return;
        }

    }


    void initializeImages(Integer imageId,ImageView imageView){
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
