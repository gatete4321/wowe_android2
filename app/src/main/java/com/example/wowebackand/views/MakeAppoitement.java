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
import android.widget.Toast;

import com.example.wowebackand.R;
import com.example.wowebackand.activities.MainActivity;
import com.example.wowebackand.models.Appoitement;
import com.example.wowebackand.models.Client;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * ndacyeka kuri layout nyeneye scrolview kugirango bi fitinge tu
 */
public class MakeAppoitement extends Fragment
{
    Integer phoneNumber;
    String loct,descr;
    Bundle bundle;
    Client client;

    Button submit;
    TextView serviceName,techName;
    ImageView techPic;
    CalendarView calendarView;
    EditText phone,description,location;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_make_appoitement,container,false);
        bundle=getArguments();
        client=bundle.getParcelable("client");

        initializeViews(view);
        initializeFakeData();

        submit.setOnClickListener((view1)->{
            if (phone.getText().toString().trim().isEmpty()||description.getText().toString().trim().isEmpty()){
                Toast.makeText(getContext(),"please enter phone,description",Toast.LENGTH_SHORT).show();
                return;
            }
            /**
             * hano turahakora staff zo ku submiting appoitement kuri server
             * niba byagenze fresh Toast.makeText("done").show;
             * else
             * ("fail")
             */
            MainActivity.navController.navigate(R.id.defaultFragment);
        });

        return view;
    }


    private void initializeViews(View view) {
        serviceName=view.findViewById(R.id.make_app_service_name);
        techName=view.findViewById(R.id.make_app_tech_name);
        techPic=view.findViewById(R.id.make_app_tech_pic);
        calendarView=view.findViewById(R.id.make_app_get_calendar);
        phone=view.findViewById(R.id.make_app_get_phone);
        description=view.findViewById(R.id.make_app_get_description);
        location=view.findViewById(R.id.make_app_get_location);
        submit=view.findViewById(R.id.make_app_button_submit);
        submit.setOnClickListener((view1)->{
            /**
             * tuza submiinga data kuri server
             */
            if (location.getText().toString().trim().isEmpty()||
                description.getText().toString().trim().isEmpty()||
                phone.getText().toString().trim().isEmpty()
            ) {
               Toast.makeText(getContext(),"please fill data",Toast.LENGTH_SHORT).show();
               return;
            }
            makeAppoitement();
            MainActivity.navController.navigate(R.id.defaultFragment);
        });
    }




    private void initializeFakeData() {
        if (!client.equals(null)){
            serviceName.setText("amazi");
            techName.setText(client.getUsername());
            techPic.setImageResource(R.drawable.ic_email_account);
            Log.e("appoitement","client not null");
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

    private void makeAppoitement() {
        Appoitement appoitement = new Appoitement();

        appoitement.setServiceId(client.getServiceId());
        appoitement.setClientId(1);//"uyu muyuza tu uri kuyikoresha"
        appoitement.setTechId(client.getClientId());
        appoitement.setDoneTime(new Date(calendarView.getDate()));
        appoitement.setDescription(description.getText().toString()+"@location"+location.getText().toString()+"@phone"+phone.getText().toString());
    if (true){
        /**
         * the appoitement was suceesfully Toast the message that was sucesfully
         */
    }
    }
}
