package com.example.wowebackand.views.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wowebackand.R;
import com.example.wowebackand.models.Appoitement;
import com.example.wowebackand.activities.MainActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * ngiye gukurura kuri network
 */
public class CompletedAdapter extends RecyclerView.Adapter<CompletedAdapter.MyCompletedViewHoleder> {

    private List<Appoitement> appoitements;

    private MyOnRecyclerListener listener;

    public CompletedAdapter(MyOnRecyclerListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CompletedAdapter.MyCompletedViewHoleder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_completed_item,parent,false);
        return new CompletedAdapter.MyCompletedViewHoleder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompletedAdapter.MyCompletedViewHoleder holder, int position) {
        Appoitement appoitement=appoitements.get(position);
        holder.serviceName.setText("wowe"+appoitement.getServiceId());
        holder.techName.setText("rukara"+appoitement.getClientId());
        holder.date.setText(appoitement.getDoneTime().toString());
        holder.imageView.setImageResource(R.drawable.abasuderezi);
        holder.view.setOnClickListener((view)->{

            Bundle bundle=new Bundle();
            bundle.putParcelable("completed",appoitement);
            MainActivity.navController.navigate(R.id.completedForm,bundle);
        });
        holder.serviceName.setOnClickListener((view)->{
//            listener.onRecyclerViewItemCliked(position,view.getId());
        });
    }

    @Override
    public int getItemCount() {
        if (appoitements==null)
            initializeAppoitements();
        return appoitements.size();
    }



    public  class MyCompletedViewHoleder extends RecyclerView.ViewHolder{

        ImageView imageView;

        TextView serviceName,date,techName;
        View view;

        public MyCompletedViewHoleder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.completed_item_image);
            serviceName=itemView.findViewById(R.id.completed_item_text_view_service_name);
            date=itemView.findViewById(R.id.completed_item_date_display);
            techName=itemView.findViewById(R.id.completed_item_display_tech_name);

            view=itemView;

//            itemView.setOnClickListener((view)->{
//                listener.onRecyclerViewItemCliked(getAdapterPosition(),appoitements.get(getAdapterPosition()));
//            });
        }
    }
    public void setAppoitements(List<Appoitement> appoitements){
        this.appoitements=appoitements;
        notifyDataSetChanged();
    }

    public void initializeAppoitements(){
        appoitements=new ArrayList<>();
        Appoitement appoitement ;
        for (int i=0;i<=12;i++){
            appoitement=new Appoitement();
            appoitement.setTechId(i);
            appoitement.setClientId(i);
            appoitement.setServiceId(i);
            appoitement.setDoneTime(new Date());
            appoitements.add(appoitement);
        }
    }
}
