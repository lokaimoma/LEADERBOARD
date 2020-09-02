package com.koc.leaderboard.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.koc.leaderboard.repository.model.HoursModel;
import com.koc.leaderboard.repository.model.IQModel;
import com.koc.leaderboard.repository.remoteDataSource.LeaderBoardService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

/**
 * Created by kelvin_clark on 8/30/2020
 */
public class BaseViewModel extends ViewModel {
    private static final String TAG = "BaseViewModel";
    private MutableLiveData<List<IQModel>> iqList = new MutableLiveData<>();
    private MutableLiveData<List<HoursModel>> hoursList = new MutableLiveData<>();
    private LeaderBoardService apiService = new LeaderBoardService();

    public void fetchHours() {
        Call<List<HoursModel>> hoursData = apiService.getHours();

        hoursData.enqueue(new Callback<List<HoursModel>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<HoursModel>> call, Response<List<HoursModel>> response) {
                if (response.isSuccessful()){
                    List<HoursModel> data = response.body();
                    hoursList.setValue(data);
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<List<HoursModel>> call, Throwable t) {
                Log.e(TAG, "onFailure: Error fetching learner's hours", t);
                hoursList.setValue(null);
            }
        });
    }

    public MutableLiveData<List<HoursModel>> getHours() {
        return hoursList;
    }

    public void fetchIQ() {
        final Call<List<IQModel>> iqModel = apiService.getSkillIq();

        iqModel.enqueue(new Callback<List<IQModel>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<IQModel>> call, Response<List<IQModel>> response) {
                if (response.isSuccessful()) {
                    List<IQModel> data = response.body();
                    iqList.setValue(data);
                }
            }

            @EverythingIsNonNull
            @Override
            public void onFailure(Call<List<IQModel>> call, Throwable t) {
                Log.e(TAG, "onFailure: error fetching learner's IQs", t);
                iqList.setValue(null);
            }
        });
    }

    public MutableLiveData<List<IQModel>> getIQ() {
        return iqList;
    }

}
