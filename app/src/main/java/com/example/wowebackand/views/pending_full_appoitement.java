package com.example.wowebackand.views;

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

import com.example.wowebackand.R;
import com.example.wowebackand.models.Appoitement;
import com.example.wowebackand.models.constant.Const;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * ndacyeka kuri layout nyeneye scrolview kugirango bi fitinge tu
 */
public class pending_full_appoitement extends Fragment
{
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
        initializeViews(view);

        Bundle bundle=getArguments();
        if (bundle!=null){
            Log.e("bundle","bundle is not null");
            appoitement= bundle.getParcelable("data");
            Log.e("datas", "not null"+appoitement.getClientId());
        }
        initializeFakeData();


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
            Log.e("passing","appoitement is not null");
            return;
        }
        serviceName.setText("gukanika");
        techName.setText("gahire");
        techPic.setImageResource(R.drawable.header);
//        phoneNumber=phone.getInputExtras(true).getInt("phone");
        calendarView.setOnDateChangeListener((view,i,i1,i2)->{
            serviceName.setText((i1+1)+"/"+i2+"/"+i);
        });
    }
}
