package com.example.wowebackand.views.recycles;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.wowebackand.R;
import com.example.wowebackand.viewModel.PendingViewModel;
import com.example.wowebackand.views.adapter.PendingAdapter;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/**
 * iyi fragment icyo imaze nu ku displayinga list yama appoitements atarakorwa
 */
public class DisplayFragPending extends Fragment {

    private RecyclerView recyclerView;
    private PendingViewModel viewModel;

    String date;

    private DatePickerDialog.OnDateSetListener onDateSetListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_frag_pending,container,false);
        recyclerView=view.findViewById(R.id.recycle_view_pending);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        viewModel= ViewModelProviders.of(this).get(PendingViewModel.class);


        onDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                date=dayOfMonth+"/"+month+"/"+year;
            }
        };

        PendingAdapter adapter=new PendingAdapter(((dateDisplay, id) -> {


            /**
             * iyi ndaza kuyishyira muri adapter
             * nkore na network operation
             */

            Calendar calendar=Calendar.getInstance();

            int year=calendar.get(Calendar.YEAR);
            int month=calendar.get(Calendar.MONTH);
            int day=calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog=new DatePickerDialog(getActivity(),
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,onDateSetListener,year,month,day);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
            TextView textView=(TextView)dateDisplay;
            textView.setText(date);
            /**
             * nago nkakoze fresh
             */
        }));
        recyclerView.setAdapter(adapter);
        viewModel.getLiveData().observe(this,appoitements -> {
            adapter.setAppoitements(appoitements);
        });
        return view;
    }
}
