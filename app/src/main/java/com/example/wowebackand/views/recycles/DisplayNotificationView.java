package com.example.wowebackand.views.recycles;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.wowebackand.R;
import com.example.wowebackand.models.Notification;
import com.example.wowebackand.viewModel.NotificationViewModel;
import com.example.wowebackand.views.adapter.DisplayNotificationAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DisplayNotificationView extends Fragment {

    List<Notification> notifications;
    NotificationViewModel viewModel;

    RecyclerView recyclerView;

    DisplayNotificationAdapter adapter;
    View view;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.notification, container, false);
        recyclerView=view.findViewById(R.id.notification_recycle);
        viewModel= ViewModelProviders.of(this).get(NotificationViewModel.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        adapter=new DisplayNotificationAdapter();

        viewModel.getNotificationLiveData().observe(this,(notifications1)->{
            adapter.setNotifications(notifications1);
            adapter.notifyDataSetChanged();
            notifications=notifications1;
        });


        recyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.deleteNot(adapter.getNotByPos(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(),"deleted",Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

//        new Handler().postDelayed(()->{
//            if (notifications!=null){
//                adapter.setNotifications(notifications);
//                adapter.showShimer=false;
//                adapter.notifyDataSetChanged();
//            }
//            else
//                noData();
//        },3000);
        return view;
    }
    void noData(){
        recyclerView.setVisibility(View.GONE);

        recyclerView=null;
        TextView textview = new TextView(getActivity());
        textview.setText("Oops!!");
        textview.setTextSize(40);
        textview.setGravity(View.TEXT_ALIGNMENT_CENTER);

        RelativeLayout.LayoutParams textviewparam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        textviewparam.addRule(RelativeLayout.CENTER_HORIZONTAL);
        textviewparam.addRule(RelativeLayout.CENTER_VERTICAL);

        RelativeLayout relativeLayout=view.findViewById(R.id.notification_relative);
        relativeLayout.addView(textview, textviewparam);

    }
}
