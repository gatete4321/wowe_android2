package com.example.wowebackand.views.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wowebackand.R;
import com.example.wowebackand.models.Notification;
import com.example.wowebackand.models.constant.Const;

import java.util.ArrayList;
import java.util.List;

public class DisplayNotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    List<Notification> notifications;
    private static int TYPE_ACT = 1;
    private static int TYPE_OTHERS = 2;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view;
        if (viewType == TYPE_ACT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_action1, parent, false);
            return new NotAction1(view);
        } else
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_actions, parent, false);
        return new NotActionOthers(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == TYPE_ACT) {
            ((NotAction1) holder).setAction1(notifications.get(position));
            ((NotAction1) holder).accept.setOnClickListener((view) -> {
                /**
                 * on accepted
                 */
                Toast.makeText(context, "accepted", Toast.LENGTH_SHORT).show();
            });


            ((NotAction1) holder).cancel.setOnClickListener(view -> {
                /**
                 * on canceled
                 */
                Toast.makeText(context, "canceled", Toast.LENGTH_SHORT).show();
            });
        } else {
            ((NotActionOthers)holder).setOthers(notifications.get(position));
        }


    }

    @Override
    public int getItemCount() {
        testData();
        return notifications.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (notifications.get(position).getActionId() == 1)
            return TYPE_ACT;
        else
            return TYPE_OTHERS;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }
    public void testData(){
      Notification notification;
        notifications=new ArrayList<>();
        for (int a=0;a<=10;a++){
            notification=new Notification();
            notification.setActionId(a>5?a-5:a);
            notification.setAppoitementId(a);
            notification.setUwayikozeId(a);
            notification.setUyikoreweId(a+10);
            notifications.add(notification);
        }
    }
}


/**
 * ********************************************************
 */

class NotAction1 extends RecyclerView.ViewHolder {
    TextView cancel, accept, clientName, appDescr;
    ImageView serviceImage;
    View view;

    public NotAction1(@NonNull View itemView) {
        super(itemView);
        cancel = itemView.findViewById(R.id.notification_tech_cancel);
        accept = itemView.findViewById(R.id.notification_tech_accept);
        clientName = itemView.findViewById(R.id.item_client_name_notification_action1);
        appDescr = itemView.findViewById(R.id.notification_app_description);
        serviceImage = itemView.findViewById(R.id.image_view_notification_action1);
        view = itemView;
    }

    public void setAction1(Notification notification) {
        clientName.setText(notification.getUwayikozeId() + "mwenyewe");
        appDescr.setText(notification.getAppoitementId() + "kogosha inyamirambo");
        serviceImage.setImageResource(Const.serviceIdImag(notification.getActionId()));
    }
}

class NotActionOthers extends RecyclerView.ViewHolder {
    TextView techName, serviceName, umwanzuro;
    ImageView techImage;
    View view;

    public NotActionOthers(@NonNull View itemView) {
        super(itemView);
        techName = itemView.findViewById(R.id.notification_actions_tech_name);
        serviceName = itemView.findViewById(R.id.notification_actions_service_name);
        umwanzuro = itemView.findViewById(R.id.notification_actions_message);
        techImage = itemView.findViewById(R.id.notification_tech_image);
        view = itemView;
    }

    public void setOthers(Notification notification) {
        techName.setText("gatete" + notification.getUwayikozeId());
        serviceName.setText("gufura");
        umwanzuro.setText(Const.namesAction(notification.getActionId()));
        techImage.setImageResource(Const.serviceIdImag(notification.getActionId()));

    }

}