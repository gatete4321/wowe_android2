package com.example.wowebackand.views.recycles;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.wowebackand.R;
import com.example.wowebackand.models.Notification;
import com.example.wowebackand.viewModel.NotificationViewModel;
import com.example.wowebackand.views.adapter.DisplayNotificationAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DisplayNotificationView extends Fragment {

    NotificationViewModel viewModel;

    RecyclerView recyclerView;

    DisplayNotificationAdapter adapter;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.notification, container, false);
        recyclerView=view.findViewById(R.id.notification_recycle);
        viewModel= ViewModelProviders.of(this).get(NotificationViewModel.class);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        adapter=new DisplayNotificationAdapter();

        viewModel.getNotificationLiveData().observe(this,(notifications1)->{
            adapter.setNotifications(notifications1);
//            Log.e("notifications",notifications1.toString());
            adapter.notifyDataSetChanged();
        });

        recyclerView.setAdapter(adapter);
        return view;
    }
}
