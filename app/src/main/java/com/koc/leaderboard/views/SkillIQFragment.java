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
import com.koc.leaderboard.adapters.SkillIqAdapter;
import com.koc.leaderboard.viewModel.BaseViewModel;

import java.util.ArrayList;


public class SkillIQFragment extends Fragment {

    private BaseViewModel viewModel;
    private SkillIqAdapter adapter;
    private ProgressBar progressBar;
    private RecyclerView rvSkillIq;

    public SkillIQFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_skill_i_q, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this).get(BaseViewModel.class);
        viewModel.fetchIQ();

        progressBar = view.findViewById(R.id.progressBar);

        rvSkillIq = view.findViewById(R.id.rv_skill_iq);
        adapter = new SkillIqAdapter(new ArrayList<>());
        rvSkillIq.setAdapter(adapter);
        rvSkillIq.setLayoutManager(new LinearLayoutManager(getContext()));

        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.getIQ().observe(getViewLifecycleOwner(), iqModels -> {
            if (!iqModels.isEmpty()) {
                progressBar.animate()
                        .alpha(0.0f)
                        .setDuration(200)
                        .start();
                progressBar.setVisibility(View.INVISIBLE);
                rvSkillIq.setVisibility(View.VISIBLE);
                adapter.insertList(iqModels);
            }
        });
    }
}