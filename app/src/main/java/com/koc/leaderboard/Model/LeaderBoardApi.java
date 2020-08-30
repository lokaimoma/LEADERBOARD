package com.koc.leaderboard.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by kelvin_clark on 8/30/2020
 */
public interface LeaderBoardApi {
    @GET("/api/hours")
    Call<List<HoursModel>> getHours();

    @GET("/api/skilliq")
    Call<List<IQModel>> getSkillIq();
}
