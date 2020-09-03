package com.koc.leaderboard.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koc.leaderboard.repository.model.*;
import com.koc.leaderboard.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by kelvin_clark on 8/30/2020
 */
public class SkillIqAdapter extends RecyclerView.Adapter<SkillIqAdapter.ViewHolder>{

    private List<IQModel> iqModels;

    public SkillIqAdapter(List<IQModel> iqModels) {
        this.iqModels = iqModels;
    }

    public void insertList(List<IQModel> iqs) {
        if (!iqModels.isEmpty())
            iqModels.clear();

        iqModels.addAll(iqs);
        Collections.sort(iqModels, IQModel.BY_IQ.reversed());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_skilliq_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        IQModel iqModel = iqModels.get(position);
        holder.learnerName.setText(iqModel.getName());
        String skillIq = iqModel.getScore() + " skill IQ score, " + iqModel.getCountry();
        holder.learnerIq.setText(skillIq);
    }

    @Override
    public int getItemCount() {
        return iqModels.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView learnerName;
        TextView learnerIq;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            learnerName = itemView.findViewById(R.id.tv_learner_name);
            learnerIq = itemView.findViewById(R.id.tv_learner_iq);
        }
    }
}
