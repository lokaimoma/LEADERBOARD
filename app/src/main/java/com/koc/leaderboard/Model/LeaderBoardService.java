package com.koc.leaderboard.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kelvin_clark on 8/30/2020
 */
public class LeaderBoardService {
    private static final String BASE_URL = "https://gadsapi.herokuapp.com/";
    private final LeaderBoardApi leaderBoardApi;

    public LeaderBoardService() {
        leaderBoardApi = new Retrofit.Builder()
                .baseUrl(BASE_URL)
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
}
