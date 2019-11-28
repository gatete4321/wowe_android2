package com.example.wowebackand.views.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wowebackand.R;
import com.example.wowebackand.models.Appoitement;
import com.example.wowebackand.models.constant.Const;
import com.example.wowebackand.activities.MainActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PendingAdapter extends RecyclerView.Adapter<PendingAdapter.MyviewHolderPending> {
    private List<Appoitement> appoitements;

    private MyOnRecyclerListener listener;

    public PendingAdapter() {

    }

    @NonNull
    @Override
    public MyviewHolderPending onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view=LayoutInflater
             .from(parent.getContext()).inflate(R.layout.layout_pending_item,parent,false);
        return new MyviewHolderPending(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolderPending holder, int position) {

        Appoitement appoitement=appoitements.get(position);
        holder.imageView.setImageResource(Const.serviceIdImag(appoitement.getServiceId()));
        holder.serviceName.setText("ngewe"+appoitement.getServiceId());
        holder.techName.setText("rugamba"+appoitement.getClientId());
        holder.dateDisplay.setText(appoitement.getDoneTime().getDay()+"/"+appoitement.getDoneTime().getMonth()+"/"+(1900+appoitement.getDoneTime().getYear()));
        //        holder.imageView.draw(R.drawable.ic_edi);


        holder.view2.setOnClickListener((view)->{
            /**
             * hano turapasingamo data zirimo appoitement ya ka kanya
             * ahubwo ndakeka nza pasingamo appoitementId yonyine noneho izindi nkazikurura nkoresheje network
             * arko mdumva azaba ari 4
             */
            Bundle args=new Bundle();
//            args.putSerializable("data",appoitement);
//            args.putInt("data",appoitement.getTechId());
            args.putParcelable("data",appoitement);

            MainActivity.navController.navigate(R.id.updateAppoitement,args);
        });

    }

    @Override
    public int getItemCount() {
        if (appoitements==null)
            initializeAppoitements();
        return appoitements.size();
    }

    public class MyviewHolderPending extends RecyclerView.ViewHolder{

        View view2;

        ImageView imageView;
        TextView serviceName;
        TextView techName;
        TextView dateDisplay;
        public MyviewHolderPending(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.pending_item_image);
            serviceName=itemView.findViewById(R.id.pending_item_text_view_service_name);
            techName=itemView.findViewById(R.id.pending_item_display_tech_name);
            dateDisplay=itemView.findViewById(R.id.pending_item_date_display);
            this.view2=itemView;
        }
    }




    public void setAppoitements(List<Appoitement> appoitements){
        this.appoitements=appoitements;
        notifyDataSetChanged();
    }

    public void initializeAppoitements(){
        appoitements=new ArrayList<>();
        Appoitement appoitement ;
        for (int i=0;i<=1;i++){
            appoitement=new Appoitement();
            appoitement.setTechId(R.drawable.abasuderezi);
            appoitement.setClientId(i);
            appoitement.setServiceId(i);
            appoitement.setDoneTime(new Date());
            appoitements.add(appoitement);
        }
    }
}
