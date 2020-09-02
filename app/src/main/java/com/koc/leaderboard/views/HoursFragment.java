package com.koc.leaderboard.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.koc.leaderboard.R;
import com.koc.leaderboard.adapters.HoursAdapter;
import com.koc.leaderboard.viewModel.BaseViewModel;

import java.util.ArrayList;

public class HoursFragment extends Fragment {

    private BaseViewModel viewModel;
    private HoursAdapter adapter;
    private ProgressBar progressBar;
    private RecyclerView rvHours;

    public HoursFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_hours, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(BaseViewModel.class);
        viewModel.fetchHours();

        progressBar = view.findViewById(R.id.progressBar);

        rvHours = view.findViewById(R.id.rv_learners);
        adapter = new HoursAdapter(new ArrayList<>());
        rvHours.setAdapter(adapter);
        rvHours.setLayoutManager(new LinearLayoutManager(getContext()));

        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.getHours().observe(getViewLifecycleOwner(), hoursModels -> {
            if (!hoursModels.isEmpty()) {
                progressBar.animate()
                        .alpha(0.0f)
                        .setDuration(200)
                        .start();
                progressBar.setVisibility(View.INVISIBLE);

                rvHours.setVisibility(View.VISIBLE);
                adapter.insertList(hoursModels);
            }
        });
    }
}