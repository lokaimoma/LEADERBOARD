package com.koc.leaderboard.View.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.koc.leaderboard.View.HoursFragment;
import com.koc.leaderboard.View.SkillIQFragment;

/**
 * Created by kelvin_clark on 8/29/2020
 */
public class HomePagerAdapter extends FragmentStateAdapter {

    public HomePagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1) {
            return new SkillIQFragment();
        }
        return new HoursFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
