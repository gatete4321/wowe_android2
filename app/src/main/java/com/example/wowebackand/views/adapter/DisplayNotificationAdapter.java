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
import com.example.wowebackand.models.NotificationForm;
import com.example.wowebackand.models.constant.Const;
import com.example.wowebackand.respostory.NotificationRespostory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DisplayNotificationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    NotificationRespostory respostory;

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

        Notification notification = notifications.get(position);
        if (getItemViewType(position) == TYPE_ACT) {
            ((NotAction1) holder).setAction1(notification);
            /**
             * 55 not allowed to make another notification
             */
//            if (notification.getStatus() !=55) {
            ((NotAction1) holder).accept.setOnClickListener((view) -> {
                /**
                 * on accepted
                 */
                createNotificaion(2, (NotificationForm) notification);
                Toast.makeText(context, "accepted", Toast.LENGTH_SHORT).show();
            });


            ((NotAction1) holder).cancel.setOnClickListener(view -> {
                /**
                 * on canceled
                 */
                createNotificaion(3, (NotificationForm) notification);
                Toast.makeText(context, "canceled", Toast.LENGTH_SHORT).show();
            });
//            }
        } else {
            ((NotActionOthers) holder).setOthers(notification);
        }


    }

    /**
     * this will hold the actionId that we want
     *
     * @param actionId      the action
     * @param notification2 the appoitement id
     * @ the recent notification in order to be updated to 55
     */
    private void createNotificaion(int actionId, NotificationForm notification2) {
        respostory = new NotificationRespostory(null);
        NotificationForm notification = new NotificationForm();
        notification.setActionId(actionId);
        notification.setRecentNotificationId(notification2.getRecentNotificationId());
        notification.setAppoitementId(notification2.getAppoitementId());
        notification.setUwayikozeId(notification2.getUyikoreweId());
        notification.setUyikoreweId(notification2.getUwayikozeId());
        respostory.insertNotification(notification);


    }

    @Override
    public int getItemCount() {
        if (notifications == null)
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
        notifyDataSetChanged();
    }

    public void testData() {
        Notification notification;
        notifications = new ArrayList<>();
        for (int a = 0; a <= 1; a++) {
            notification = new Notification();
            notification.setActionId(a);
            notification.setAppoitementId(a);
            notification.setUwayikozeId(a);
            notification.setUyikoreweId(a + 10);
            notification.setDoneTime(new Date());
            notification.setUwayikozeName("mimi");
            notifications.add(notification);
        }
    }

}


/**
 * ********************************************************
 */

class NotAction1 extends RecyclerView.ViewHolder {
    TextView cancel, accept, clientName, appDescr, date;
    ImageView serviceImage;
    View view;

    public NotAction1(@NonNull View itemView) {
        super(itemView);
        cancel = itemView.findViewById(R.id.notification_tech_cancel);
        accept = itemView.findViewById(R.id.notification_tech_accept);
        clientName = itemView.findViewById(R.id.item_client_name_notification_action1);
        appDescr = itemView.findViewById(R.id.notification_app_description);
        serviceImage = itemView.findViewById(R.id.image_view_notification_action1);
        date = itemView.findViewById(R.id.item_date_notification_action1);
        view = itemView;
    }

    public void setAction1(Notification notification) {
        clientName.append(notification.getUwayikozeName());
        appDescr.append(notification.getAppoitementId() + "kogosha inyamirambo");
        serviceImage.setImageResource(Const.serviceIdImag(notification.getActionId()));
        date.append(notification.getDoneTime().getDate() + "/" + notification.getDoneTime().getMonth() + "/" + (1900 + notification.getDoneTime().getYear()));
    }
}

class NotActionOthers extends RecyclerView.ViewHolder {
    TextView techName, serviceName, umwanzuro, date;
    ImageView techImage;
    View view;

    public NotActionOthers(@NonNull View itemView) {
        super(itemView);
        techName = itemView.findViewById(R.id.notification_actions_tech_name);
        serviceName = itemView.findViewById(R.id.notification_actions_service_name);
        umwanzuro = itemView.findViewById(R.id.notification_actions_message);
        techImage = itemView.findViewById(R.id.notification_tech_image);
        date = itemView.findViewById(R.id.notification_actions_date);
        view = itemView;
    }

    public void setOthers(Notification notification) {
        techName.append(notification.getUwayikozeName());
        serviceName.append("gufura");
        umwanzuro.setText(Const.namesAction(notification.getActionId()));
        techImage.setImageResource(Const.serviceIdImag(notification.getActionId()));
        date.append(notification.getDoneTime().getDate() + "/" + notification.getDoneTime().getMonth() + "/" + (1900 + notification.getDoneTime().getYear()));
    }

}