package com.koc.leaderboard.View;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.koc.leaderboard.Model.LeaderBoardService;
import com.koc.leaderboard.R;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;


public class SubmitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getWindow().setStatusBarColor(Color.BLACK);

        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        TextView firstName = findViewById(R.id.first_name);
        TextView lastName = findViewById(R.id.last_name);
        TextView emailAddress = findViewById(R.id.email_address);
        TextView githubLink = findViewById(R.id.github_link);

        findViewById(R.id.submitBtn).setOnClickListener(view -> {
            String email = emailAddress.getText().toString();
            String firstN = firstName.getText().toString();
            String lastN = lastName.getText().toString();
            String github = githubLink.getText().toString();

            if (email.isEmpty() && firstN.isEmpty() && lastN.isEmpty() && github.isEmpty()) {
                Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
            }else {
                LeaderBoardService.submit(
                        email,
                        firstN,
                        lastN,
                        github
                ).enqueue(new Callback<Void>() {
                    @EverythingIsNonNull
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()){
                            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getApplicationContext());
                            builder.setView(R.layout.successfull_dialog);
                            builder.create();
                        }
                    }

                    @EverythingIsNonNull
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getApplicationContext());
                        builder.setView(R.layout.error_dialog);
                        builder.create();
                    }
                });
            }
        });
    }
}