package com.example.wowebackand.views.recycles;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.wowebackand.R;
import com.example.wowebackand.models.Appoitement;
import com.example.wowebackand.viewModel.CompletedViewModel;
import com.example.wowebackand.views.adapter.CompletedAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * iy imaze ku displayinga ama appoitements yarangiye
 */
public class DisplayFragComplete extends Fragment {
    List<Appoitement> appoitementList;
    View view;
    CompletedAdapter adapter;
    private CompletedViewModel viewModel;
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_frag_completed, container, false);
        recyclerView = view.findViewById(R.id.recycle_view_completed);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        viewModel = ViewModelProviders.of(this).get(CompletedViewModel.class);
        adapter = new CompletedAdapter();
        viewModel.getLiveData().observe(this, appoitements -> {
            adapter.setAppoitements(appoitements);
            adapter.notifyDataSetChanged();
        });

        recyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT){
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.deleteAppoitement(adapter.getAppByPos(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(),"deleted",Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        return view;
    }

    void noData() {
        recyclerView.setVisibility(View.GONE);

        recyclerView = null;
        TextView textview = new TextView(getActivity());
        textview.setText("Oops!!");
        textview.setTextSize(40);
        textview.setGravity(View.TEXT_ALIGNMENT_CENTER);

        RelativeLayout.LayoutParams textviewparam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        textviewparam.addRule(RelativeLayout.CENTER_HORIZONTAL);
        textviewparam.addRule(RelativeLayout.CENTER_VERTICAL);

        RelativeLayout relativeLayout = view.findViewById(R.id.layout_frag_completed_rel);
        relativeLayout.addView(textview, textviewparam);

    }
}
