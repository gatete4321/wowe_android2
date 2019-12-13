package com.example.wowebackand.views.recycles;


import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wowebackand.R;
import com.example.wowebackand.models.Appoitement;
import com.example.wowebackand.respostory.AppoitementRespostory;
import com.example.wowebackand.viewModel.PendingViewModel;
import com.example.wowebackand.views.adapter.PendingAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


/**
 * iyi fragment icyo imaze nu ku displayinga list yama appoitements atarakorwa
 */
public class DisplayFragPending extends Fragment {

    List<Appoitement> appoitementList;
    View view;
    private RecyclerView recyclerView;
    private PendingViewModel viewModel;

    String date;

    private DatePickerDialog.OnDateSetListener onDateSetListener;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_frag_pending, container, false);
        recyclerView = view.findViewById(R.id.recycle_view_pending);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        viewModel = ViewModelProviders.of(this).get(PendingViewModel.class);

        PendingAdapter adapter = new PendingAdapter();
        viewModel.getLiveData().observe(this, appoitements -> {
            appoitementList = appoitements;
            if (appoitements != null) {
                adapter.setAppoitements(appoitements);
            } else
                noData();
        });

        recyclerView.setAdapter(adapter);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.deleteAppoitement(adapter.getAppoitementByPos(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(), "deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
        new Handler().postDelayed(()->{
            if (appoitementList != null){
                viewModel.PendToComp(appoitementList);
                Log.e("PendToComp","nabikoze");
            }
        },3000);

        return view;
    }

    void noData() {
        recyclerView.setVisibility(View.GONE);

        recyclerView = null;
        TextView textview = new TextView(getActivity());
        textview.setText("Oops!!");
        textview.setTextSize(40);
        textview.setGravity(View.TEXT_ALIGNMENT_CENTER);

        RelativeLayout.LayoutParams textviewparam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        textviewparam.addRule(RelativeLayout.CENTER_HORIZONTAL);
        textviewparam.addRule(RelativeLayout.CENTER_VERTICAL);

        RelativeLayout relativeLayout = view.findViewById(R.id.layout_frag_pending_rel);
        relativeLayout.addView(textview, textviewparam);

    }
}


/**
 * iyi ndaza kuyishyira muri adapter
 * //             * nkore na network operation
 * //
 */
//
//            Calendar calendar=Calendar.getInstance();
//
//            int year=calendar.get(Calendar.YEAR);
//            int month=calendar.get(Calendar.MONTH);
//            int day=calendar.get(Calendar.DAY_OF_MONTH);
//            DatePickerDialog dialog=new DatePickerDialog(getActivity(),
//                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,onDateSetListener,year,month,day);
//            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            dialog.show();
//            TextView textView=(TextView)dateDisplay;
//            textView.setText(date);
//            /**
//             * nago nkakoze fresh
//             */
//        }));