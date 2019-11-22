package com.example.wowebackand.views.adapter;



import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.wowebackand.R;
import com.example.wowebackand.models.Service;
import com.example.wowebackand.activities.MainActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterService extends RecyclerView.Adapter<AdapterService.MyViewHolderService> {

    List<Service> services;

    MyOnRecyclerListener listener;
    Activity activity;

    public AdapterService(MyOnRecyclerListener listener, Activity activity) {
        this.listener = listener;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolderService onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                                 .inflate(R.layout.layout_frag_service_item,parent,false);

        return new MyViewHolderService(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderService holder, int position) {
        Service service=services.get(position);
        holder.textView.setText(service.getServiceName());
        holder.imageView.setImageResource(service.getServiceImageId());
        holder.view.setOnClickListener((view)->{
            /**
             * for navigating to other fragment,and passing other data using bundle
             * am gona to run about actions in fragments
             */
            Bundle bundle=new Bundle();
            bundle.putInt("serviceId",service.getServiceId());
            MainActivity.navController.navigate(R.id.serviceProvider,bundle);
        });
    }

    @Override
    public int getItemCount() {
        if (services==null)
            makeService();
        return services.size();
    }

    public class MyViewHolderService extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;
        View view;

        public MyViewHolderService(@NonNull View itemView) {
            super(itemView);
            view=itemView;
            imageView=itemView.findViewById(R.id.frag_service_item_image_view);
            textView=itemView.findViewById(R.id.frag_service_item_text_view);
            itemView.setOnClickListener((view)->{
//                listener.onRecyclerViewItemCliked(null,services.get(getAdapterPosition()));


            });
        }
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }







    public void makeService(){
        services=new ArrayList<Service>();
        Service service;
        service=new Service(1,"gukanika",R.drawable.abakanishi,null);
        services.add(service);
        service=new Service(2,"gusudira",R.drawable.abasuderezi,null);
        services.add(service);
        service=new Service(3,"kubaka",R.drawable.abubatsi,null);
        services.add(service);
        service=new Service(4,"amazi",R.drawable.amazi,null);
        services.add(service);
        service=new Service(5,"amashanyarazi",R.drawable.electricie,null);
        services.add(service);
        service=new Service(6,"electronicie",R.drawable.electronic,null);
        services.add(service);
        service=new Service(7,"kubaza",R.drawable.umubaji,null);
        services.add(service);
        service=new Service(8,"gutwara",R.drawable.shoferi,null);
        services.add(service);



    }
}
