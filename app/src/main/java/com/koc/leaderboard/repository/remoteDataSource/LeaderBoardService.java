package com.koc.leaderboard.repository.remoteDataSource;

import com.koc.leaderboard.api.LeaderBoardApi;

import java.util.List;
import com.koc.leaderboard.repository.model.*;



import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kelvin_clark on 8/30/2020
 */
public class LeaderBoardService {
    private static final String BASE_URL = "https://gadsapi.herokuapp.com/";
    private static LeaderBoardApi leaderBoardApi;

    HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(logging)
            .build();


    public LeaderBoardService() {
        leaderBoardApi = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LeaderBoardApi.class);
    }

    public Call<List<IQModel>> getSkillIq(){
        return leaderBoardApi.getSkillIq();
    }

    public Call<List<HoursModel>> getHours() {
        return leaderBoardApi.getHours();
    }

    public Call<Void> submit(String emailAddress, String firstName, String lastName, String linkToProject){
        LeaderBoardApi api  = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/e/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(LeaderBoardApi.class);
        return api.submit(emailAddress, firstName, lastName, linkToProject);
    }
}
