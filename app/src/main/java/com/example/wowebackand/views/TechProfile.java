package com.example.wowebackand.views;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.wowebackand.R;
import com.example.wowebackand.activities.MainActivity;
import com.example.wowebackand.models.Client;
import com.example.wowebackand.models.constant.Const;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * iyi fragment niyo ngiye gusimbuza iya DisplayTechDetail.java kuko sinyishaka ko ziza mu buryo bwa recycleView
 * @20.10.2019
 */
public class TechProfile extends Fragment {
    Context context;
    Client client;

    Bundle bundle;


    ImageView imageView;
    Button button;
    RatingBar ratingBar;
    TextView techName, techCarrier, techAbout, techLocation;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view1 = inflater.inflate(R.layout.layout_tech_profile_item, container, false);
        imageView = view1.findViewById(R.id.tech_image);
        button = view1.findViewById(R.id.button_make_appoitement);
        ratingBar = view1.findViewById(R.id.tech_detail_ratingBar);
        techAbout = view1.findViewById(R.id.profile_item_tech_about);
        techName = view1.findViewById(R.id.profile_item_tech_name);
        techCarrier = view1.findViewById(R.id.profile_item_tech_carier_name);
        techLocation = view1.findViewById(R.id.profile_item_tech_location);
        context=getContext();

        ratingBar.setMax(5);
        ratingBar.setNumStars(5);


        bundle=getArguments();
        client=(Client) bundle.getParcelable("client");
        if (!client.equals(null)) {
//            imageView.setImageResource(R.drawable.ic_email_account);
            techName.setText(client.getUsername());
            techAbout.setText(client.getClientAbout());
            techLocation.setText(client.getClientLocation());
            techCarrier.setText(Const.getServicesIdName(client.getServiceId()));
            ratingBar.setRating(client.getRates());

            initializeImages(client.getProfileImage(),imageView);

            Log.e("techProfile1","client is noo null");
        }
        else
        Log.e("techProfile","client is null");
        button.setOnClickListener((view)->{
            bundle=new Bundle();
            bundle.putParcelable("client",client);
            MainActivity.navController.navigate(R.id.makeAppoitement,bundle);
        });






        return view1;
    }



    void initializeImages(String imageName,ImageView imageView){
        String full= Const.urlImage+imageName;
        Picasso.with(context)
                .load(full)
                .fit()
                .centerInside()
                .into(imageView);
    }
}
