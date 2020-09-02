package com.koc.leaderboard.api;

import com.koc.leaderboard.repository.model.*;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by kelvin_clark on 8/30/2020
 */
public interface LeaderBoardApi {
    @GET("/api/hours")
    Call<List<HoursModel>> getHours();

    @GET("/api/skilliq")
    Call<List<IQModel>> getSkillIq();

    @POST("/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    Call<Void> submit(
            @Field("entry.1824927963")String emailAddress,
            @Field(" entry.1877115667")String firstName,
            @Field("entry.2006916086")String lastName,
            @Field("entry.284483984")String linkToProject
    );
}
