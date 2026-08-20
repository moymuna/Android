package com.example.myaplication.Repository;

import android.content.Context;

import com.example.myaplication.api.ApiClient;
import com.example.myaplication.api.ApiService;
import com.example.myaplication.model.response.EmployeeResponse;

import retrofit2.Call;
import retrofit2.Callback;

public class EmployeeRepository {
    private final ApiService apiService;

    public EmployeeRepository(Context context) {
        apiService = ApiClient.getClient(context);
    }

    public void getEmployeeByUserId(Long userId,
                                    Callback<EmployeeResponse> callback) {

        Call<EmployeeResponse> call =
                apiService.getEmployeeByUserId(userId);

        call.enqueue(callback);

    }
}
