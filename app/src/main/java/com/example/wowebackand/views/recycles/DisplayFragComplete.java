package com.example.wowebackand.views.recycles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.wowebackand.R;
import com.example.wowebackand.viewModel.CompletedViewModel;
import com.example.wowebackand.views.adapter.CompletedAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * iy imaze ku displayinga ama appoitements yarangiye
 */
public class DisplayFragComplete extends Fragment
{
    private CompletedViewModel viewModel;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.layout_frag_completed,container,false);
        recyclerView=view.findViewById(R.id.recycle_view_completed);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        viewModel= ViewModelProviders.of(this).get(CompletedViewModel.class);
        CompletedAdapter adapter=new CompletedAdapter((e,t)->{
            /**
             * hano turahashyira izindi code:zifata appoitement zikayishyira kuyindi fragment
             * i displayinga appoitements zabay completed
             * sindayubaka
             * niriya interface MyOnRecyclerLiistener izahinduka tu:ark niy mbay nkoresheje
             * @29/9/2019
             */



            Toast.makeText(getActivity().getApplicationContext(),"dfj",Toast.LENGTH_LONG)
                    .show();
        });
        recyclerView.setAdapter(adapter);
        viewModel.getLiveData().observe(this,appoitements -> {
            adapter.setAppoitements(appoitements);
        });
        return view;
    }
}
