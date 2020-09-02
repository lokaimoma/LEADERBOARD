package com.koc.leaderboard.views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.koc.leaderboard.R;
import com.koc.leaderboard.adapters.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getWindow().setStatusBarColor(Color.BLACK);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleMarginTop(4);
        setSupportActionBar(toolbar);


        ViewPager2 viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(new HomePagerAdapter(this));

        TabLayout tabLayout = findViewById(R.id.tabLayout);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Learning Leaders");
                            break;
                        case 1:
                            tab.setText("Skill IQ Leaders");
                            break;
                    }
                });


        tabLayoutMediator.attach();

        findViewById(R.id.submit).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SubmitActivity.class);
            startActivity(intent);
        });
    }

}