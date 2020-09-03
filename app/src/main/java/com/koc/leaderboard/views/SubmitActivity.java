package com.koc.leaderboard.views;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.koc.leaderboard.repository.remoteDataSource.LeaderBoardService;
import com.koc.leaderboard.R;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;


public class SubmitActivity extends AppCompatActivity {
    private static final String TAG = "SubmitActivity";

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

            if (email.equals("") && firstN.equals("") && lastN.equals("") && github.equals("")) {
                Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
            } else {
                Log.d(TAG, "onCreate: sending request");

                Call<Void> response = new LeaderBoardService().submit(
                        email,
                        firstN,
                        lastN,
                        github
                );

                response.enqueue(new Callback<Void>() {
                    @EverythingIsNonNull
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()) {
                            Log.d(TAG, "onResponse: request successful");
                            IsSuccessfulDialog isSuccessfulDialog = new IsSuccessfulDialog();
                            isSuccessfulDialog.show(getSupportFragmentManager(), "Is successful dialog");
                        }
                    }
                    @EverythingIsNonNull
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.e(TAG, "onFailure: request failed", t);
                        ErrorDialog errorDialog = new ErrorDialog();
                        errorDialog.show(getSupportFragmentManager(), "Error dialog");
                    }
                });
            }
        });
    }
}