package com.example.wowebackand.views;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Completed_full_appoitement extends Fragment
{

    Appoitement appoitement;

    Bundle bundle;

    Button submit,delete;
    TextView date,serviceName,contact,techName,description;
    EditText feedback;
    ImageView techPic;
    RatingBar ratingBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.display_completed_appoitement_view,container,false);
        initializeViews(view);
        initializeData();

        submit.setOnClickListener((view1)->{
            /**
             * niba ratings yari yarizihashyize naggo yemerewe
             */
            if (!new Appoitement().getRate().equals(null)){
                /**
                 * dont update the rates
                 */
            }
            if (!new Appoitement().getFeedBack().equals(null)){
                /**
                 * dont update the feedback
                 */
            }
            if (feedback.getText().toString().trim().equals(null)&&
                    ratingBar.getRating()==0){
                Toast.makeText(getActivity(),"please fill data",Toast.LENGTH_SHORT).show();
                return;
            }
        });
        delete.setOnClickListener((view1)->{

        });




        return view;
    }

    private void initializeData() {
        bundle=getArguments();
        appoitement=bundle.getParcelable("completed");
        if (!appoitement.equals(null)){
            Log.e("commpleted","appoitement nago ari null");
            date.setText(appoitement.getDoneTime().toString());
            serviceName.setText(appoitement.getClientId()+"none");
        }
    }

    /**
     * for initializing data
     * @param view
     */
    private void initializeViews(View view) {
        submit=view.findViewById(R.id.app_view_button_submit);
        delete=view.findViewById(R.id.app_view_button_delete);
        date=view.findViewById(R.id.app_view_date);
        serviceName=view.findViewById(R.id.app_view_service_name);
        contact=view.findViewById(R.id.app_view_contact);
        techName=view.findViewById(R.id.app_view_tech_name);
        description=view.findViewById(R.id.app_view_description);
        feedback=view.findViewById(R.id.app_view_feedback);
        techPic=view.findViewById(R.id.app_image_view);
        ratingBar=view.findViewById(R.id.app_view_ratingBar);
    }

    /**
     * this will get all data to be displayed on the appointement form
     */
    public void setData(){

    }

    /**
     * this will handle all onclick events
     */
    public void onClickListeners(){
        delete.setOnClickListener((view)->{
            //delete the appoitement
        });

        submit.setOnClickListener((view)->{
            //submit ratings
            //submit feedback
        });
    }
}
