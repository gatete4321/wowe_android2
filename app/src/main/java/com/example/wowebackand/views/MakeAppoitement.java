package com.example.wowebackand.views;

import android.content.Context;
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
import com.example.wowebackand.models.AppoitementForm;
import com.example.wowebackand.models.Client;
import com.example.wowebackand.models.constant.Const;
import com.example.wowebackand.respostory.AppoitementRespostory;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * ndacyeka kuri layout nyeneye scrolview kugirango bi fitinge tu
 */
public class MakeAppoitement extends Fragment {
    Context context;
    private AppoitementRespostory respostory;
    Integer phoneNumber;
    String loct, descr;
    Bundle bundle;
    Client client;

    Date calDate;

    Button submit;
    TextView serviceName, techName;
    ImageView techPic;
    CalendarView calendarView;
    EditText phone, description, location;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_make_appoitement, container, false);
        bundle = getArguments();
        client = bundle.getParcelable("client");

        initializeViews(view);
        initializeFakeData();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {

                String  date=i2+"/"+(i1+1)+"/"+i;

                try {
                    calDate=new SimpleDateFormat("dd/MM/yyyy").parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Log.e("dateCalendar",calDate.toString());
            }
        });


        submit.setOnClickListener((view1) -> {
            if (phone.getText().toString().trim().isEmpty() || description.getText().toString().trim().isEmpty()) {
                Toast.makeText(getContext(), "please enter phone,description", Toast.LENGTH_SHORT).show();
                return;
            }

            Calendar c=Calendar.getInstance();
            c.add(Calendar.DATE,30);
            if(calDate.before(new Date())||calDate.after(c.getTime())){
                Log.e("date picked",calDate.toString()+calDate.getYear());
                Toast.makeText(getContext(),"invalid date not above one month ",Toast.LENGTH_SHORT).show();
                return;
            }
            makeAppoitement();
            MainActivity.navController.navigate(R.id.defaultFragment);
        });

        return view;
    }


    private void initializeViews(View view) {
        serviceName = view.findViewById(R.id.make_app_service_name);
        techName = view.findViewById(R.id.make_app_tech_name);
        techPic = view.findViewById(R.id.make_app_tech_pic);
        calendarView = view.findViewById(R.id.make_app_get_calendar);
        phone = view.findViewById(R.id.make_app_get_phone);
        description = view.findViewById(R.id.make_app_get_description);
        location = view.findViewById(R.id.make_app_get_location);
        submit = view.findViewById(R.id.make_app_button_submit);
        context=getContext();
    }


    private void initializeFakeData() {
        if (!client.equals(null)) {
            serviceName.setText(Const.getServicesIdName(client.getServiceId()));
            techName.setText(client.getUsername());
            phone.setHint(Const.phone);
//            techPic.setImageResource(R.drawable.ic_email_account);
            initializeImages(client.getProfileImage(),techPic);

            Log.e("appoitement", "client not null");
            return;
        }
    }

    private void makeAppoitement() {

        respostory = new AppoitementRespostory(getActivity().getApplication());
        AppoitementForm appoitement = new AppoitementForm();

        appoitement.setServiceId(client.getServiceId());
        appoitement.setClientId(Const.userId);//"uyu muyuza tu uri kuyikoresha"
        appoitement.setTechId(client.getClientId());
        appoitement.setToday(calDate.getTime());
        appoitement.setDescription(description.getText().toString() + "@location" + location.getText().toString() + "@phone" + phone.getText().toString());
        appoitement.setTechName(client.getUsername());
        Integer result = respostory.insertAppoitement(appoitement);
        if (result != null) {
            if (result != 1)
                Toast.makeText(getActivity(), "Failed..", Toast.LENGTH_SHORT).show();
        }
    }

    void initializeImages(String imageName,ImageView imageView){
        String full= Const.urlImage+imageName;
        Picasso.with(context)
                .load(full)
                .fit()
                .centerInside()
                .into(imageView);
    }
}
