package com.example.wowebackand.views.recycles;


import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.wowebackand.R;
import com.example.wowebackand.activities.MainActivity;
import com.example.wowebackand.views.adapter.AdapterService;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * iyi icyo imaze nu ku displayinga ama service atangwa
 * mur grid view
 */
public class DisplayFragService extends Fragment {

    AdapterService adapterService;
    RecyclerView recyclerView;


    DisplayServiceProvider serviceProvider; //for real apps

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.layout_frag_service,container,false);
        recyclerView=view.findViewById(R.id.frag_service_recycleview);







        serviceProvider=new DisplayServiceProvider();  //for real apps

        adapterService=new AdapterService(((position, id) -> {
            /**
             * for navigaing to other fragment,and passing other data uusing bundle
             * am gona to run about actions in fragments
             */ MainActivity.navController.navigate(R.id.serviceProvider,new Bundle());
        }),getActivity());
        recyclerView.setAdapter(adapterService);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),calculateNumberOfColumns(2));
        recyclerView.setLayoutManager(gridLayoutManager);//new GridLayoutManager(getActivity(),2));
    return view;
    }

    // Custom method to calculate number of columns for grid type recycler view
    protected int calculateNumberOfColumns(int base){
        int columns = base;
        String screenSize = getScreenSizeCategory();

        if(screenSize.equals("small")){
            if(base!=1){
                columns = columns-1;
            }
        }else if (screenSize.equals("normal")){
            // Do nothing
        }else if(screenSize.equals("large")){
            columns += 2;
        }else if (screenSize.equals("xlarge")){
            columns += 3;
        }

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            columns = (int) (columns * 1.5);
        }

        return columns;
    }


    protected String getScreenOrientation(){
        String orientation = "undefined";

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            orientation = "landscape";
        }else if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            orientation = "portrait";
        }

        return orientation;
    }

    protected String getScreenSizeCategory(){
        int screenLayout = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;

        switch(screenLayout){
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                // small screens are at least 426dp x 320dp
                return "small";
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                // normal screens are at least 470dp x 320dp
                return "normal";
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                // large screens are at least 640dp x 480dp
                return "large";
            case Configuration.SCREENLAYOUT_SIZE_XLARGE:
                // xlarge screens are at least 960dp x 720dp
                return "xlarge";
            default:
                return "undefined";
        }
    }
}
//((ViewGroup)getView().getParent()).getId()
/**
 * R.id.nav_host_fragment
 * man ndabona iyi tekinike yanze ngiye gukoresha navigation
 * kuko ni pasingamo layout ya activity irimo ihita ifungura izindi fragment icyarimwe
 * ********method 2************************************************************
 * nagerageje no gukora indi layout isumba DrawerLayout muri activity_main.xml*
 * arko birimo birongera memory 512kb kandi nibigire nicyo bitanga            *
 * <RelativeLayout                                                            *
 *     xmlns:android="http://schemas.android.com/apk/res/android"             *
 *     xmlns:app="http://schemas.android.com/apk/res-auto"                    *
 *     xmlns:tools="http://schemas.android.com/tools"                         *
 *     android:layout_height="match_parent"                                   *
 *     android:layout_width="match_parent"                                    *
 *     android:id="@+id/relative_main">                                       *
 ******************************************************************************
 * &&&&&&&&&&&&&igisubizo&&&&&&&&&&&&&&&&&&&&&&&&&&&&
 * nakoresheje host fragment muri navigation        &
 * component nkuko first fragment na second zimeze  &
 * &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
 */