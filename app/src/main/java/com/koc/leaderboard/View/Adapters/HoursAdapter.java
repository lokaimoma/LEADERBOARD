package com.koc.leaderboard.View.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koc.leaderboard.Model.HoursModel;
import com.koc.leaderboard.R;

import java.util.List;

/**
 * Created by kelvin_clark on 8/30/2020
 */
public class HoursAdapter extends RecyclerView.Adapter<HoursAdapter.ViewHolder> {

    private List<HoursModel> learnersHours;

    public HoursAdapter(List<HoursModel> learnersHours) {
        this.learnersHours = learnersHours;
    }

    public void insertList(List<HoursModel> hours) {
        if (!learnersHours.isEmpty())
            learnersHours.clear();

        learnersHours.addAll(hours);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_learner_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HoursModel learner = learnersHours.get(position);
        holder.learnerName.setText(learner.getName());
        String learningHours = learner.getHours() + " learning hours, " + learner.getCountry();
        holder.learnerHours.setText(learningHours);

    }

    @Override
    public int getItemCount() {
        return learnersHours.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView learnerName;
        private TextView learnerHours;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            learnerName = itemView.findViewById(R.id.tv_learner_name);
            learnerHours = itemView.findViewById(R.id.tv_leaner_hours);
        }
    }
}
