package com.example.wowebackand.views.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wowebackand.R;
import com.example.wowebackand.activities.MainActivity;
import com.example.wowebackand.models.Client;
import com.example.wowebackand.models.constant.Const;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProvidersAdapter extends RecyclerView.Adapter<ProvidersAdapter.MyProvidersHolder> implements Filterable {

    List<Client> technicians;

    List<Client> techniciansFull;


    public boolean showShimer = true;
    public int SHIMMER_ITEM_NO = 5;

    Context context;

    public ProvidersAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public MyProvidersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_provider_item, parent, false);
        return new MyProvidersHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyProvidersHolder holder, int position) {
        if (showShimer) {
            holder.shimmerFrameLayout.startShimmer();
        } else {
            holder.shimmerFrameLayout.stopShimmer();
            holder.shimmerFrameLayout.setShimmer(null);

            Client client = technicians.get(position);

            Bundle bundle = new Bundle();
            bundle.putParcelable("client", client);

            holder.techDescription.setText(client.getClientAbout());
            holder.techName.setText(client.getUsername());
            initializeImages(client.getProfileImage(), holder.techImage);//loading image



            holder.calling.setOnClickListener((view) -> {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + client.getPhoneNumber()));
                context.startActivity(intent);
            });

            holder.view.setOnClickListener((view) -> {
                if (Const.userName!=null){
                MainActivity.navController.navigate(R.id.techDetail, bundle);}
            });
            holder.makeApp.setOnClickListener((view) -> {
                if (Const.userName!=null){
                MainActivity.navController.navigate(R.id.makeAppoitement, bundle);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return showShimer ? SHIMMER_ITEM_NO : technicians.size();//if its is true i will return 5 itemes
    }

    public static class MyProvidersHolder extends RecyclerView.ViewHolder {
        ImageView techImage;
        TextView techName, techDescription, calling, makeApp;
        View view;
        ShimmerFrameLayout shimmerFrameLayout;

        public MyProvidersHolder(@NonNull View itemView) {
            super(itemView);
            techImage = itemView.findViewById(R.id.image_view_provider_tech);
            techName = itemView.findViewById(R.id.item_tech_name);
            techDescription = itemView.findViewById(R.id.item_tech_description);
            calling = itemView.findViewById(R.id.item_tech_calling);
            makeApp = itemView.findViewById(R.id.item_tech_appoitement);
            view = itemView;
            shimmerFrameLayout = itemView.findViewById(R.id.shimmer_layout);
        }

    }


    public List<Client> getTechnicians() {
        return technicians;
    }

    public void setTechnicians(List<Client> technicians) {
        this.technicians = technicians;
        techniciansFull = new ArrayList<>(technicians);
    }


    void initializeImages(String imageName, ImageView imageView) {
        String full = Const.urlImage + imageName;
        Picasso.with(context)
                .load(full)
                .fit()
                .centerInside()
                .into(imageView);
    }

    @Override
    public Filter getFilter() {

        return techFilter;
    }

    private Filter techFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            List<Client> filteredTech = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredTech.addAll(techniciansFull);
            } else {
                String ijambo = charSequence.toString().toLowerCase().trim();
                for (Client client : techniciansFull) {
                    if (client.getUsername().toLowerCase().contains(ijambo)
                            || client.getClientLocation().toLowerCase().contains(ijambo)
                            ||client.getClientAbout().toLowerCase().contains(ijambo)) {
                        filteredTech.add(client);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredTech;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            if (technicians != null) {
                technicians.clear();
                technicians.addAll((List) filterResults.values);
                notifyDataSetChanged();
            }
        }
    };
}
