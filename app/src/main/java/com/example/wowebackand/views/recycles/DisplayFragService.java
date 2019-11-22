package com.example.wowebackand.views.recycles;


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
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
    return view;
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