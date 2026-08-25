package com.example.myaplication.Repository;

import android.content.Context;
import com.example.myaplication.api.ApiClient;
import com.example.myaplication.api.ApiService;
import com.example.myaplication.model.response.TrainingResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;

public class TrainingRepository {
    private final ApiService apiService;

    public TrainingRepository(Context context) {
        apiService = ApiClient.getClient(context);
    }

    public void getAllTrainings(Callback<List<TrainingResponse>> callback) {
        apiService.getAllTrainings().enqueue(callback);
    }

    public void applyForTraining(Long trainingId, Long employeeId, Callback<TrainingResponse> callback) {
        apiService.applyForTraining(trainingId, employeeId).enqueue(callback);
    }
}
