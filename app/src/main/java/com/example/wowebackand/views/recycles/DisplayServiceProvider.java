package com.example.wowebackand.views.recycles;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.wowebackand.R;
import com.example.wowebackand.models.Client;
import com.example.wowebackand.viewModel.ProvideViewModel;
import com.example.wowebackand.views.adapter.ProvidersAdapter;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * iyi fragment icyo imaze nu ku displayinga list ya ba techinicie
 * bakora ako kazi
 */
public class DisplayServiceProvider extends Fragment {
    private RecyclerView recyclerView;

    private ProvideViewModel viewModel;

    private ProvidersAdapter adapter;

    List<Client> clientList;

    private Integer serviceId;

    ShimmerFrameLayout shimmerFrameLayout;

    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_frag_service_provider, container, false);


        recyclerView=view.findViewById(R.id.service_provider);

        Bundle bundle = getArguments();
        if (bundle != null) {
            serviceId = bundle.getInt("serviceId");
        }






        if (!isNetworkAvaible()) {
            noData("turn on Connection");
        } else {
            setHasOptionsMenu(true);
            shimmerFrameLayout = view.findViewById(R.id.shimmer_layout);

            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            viewModel = ViewModelProviders.of(getActivity()).get(ProvideViewModel.class);
            viewModel.getListClientLivedata(serviceId).observe(this, client -> {
                clientList = client;
            });
            adapter = new ProvidersAdapter(getActivity());//.getApplicationContext());

            recyclerView.setAdapter(adapter);

            new Handler().postDelayed(() -> {
                if (clientList != null) {
                    adapter.setTechnicians(clientList);
                    adapter.showShimer = false;
                    adapter.notifyDataSetChanged();
                } else {
                    noData("Oops");
                }
            }, 3000);

        }


        return view;
    }


    void noData(String message) {
        recyclerView.setVisibility(View.GONE);

        recyclerView = null;
        TextView textview = new TextView(getActivity());
        textview.setText(message);
        textview.setTextSize(40);
        textview.setGravity(View.TEXT_ALIGNMENT_CENTER);

        RelativeLayout.LayoutParams textviewparam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        textviewparam.addRule(RelativeLayout.CENTER_HORIZONTAL);
        textviewparam.addRule(RelativeLayout.CENTER_VERTICAL);

        RelativeLayout relativeLayout = view.findViewById(R.id.layout_frag_service_provider);
        relativeLayout.addView(textview, textviewparam);

    }

    private boolean isNetworkAvaible() {

        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        } else
            connected = false;

        return connected;

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.provider_menu,menu);

        MenuItem item=menu.findItem(R.id.action_search);

        SearchView searchView= (SearchView) item.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setQueryHint("search name,location,service");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }
}
