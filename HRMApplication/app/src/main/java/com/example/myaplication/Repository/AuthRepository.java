package com.example.myaplication.Repository;

import android.content.Context;

import com.example.myaplication.api.ApiClient;
import com.example.myaplication.api.ApiService;
import com.example.myaplication.model.request.LogInRequest;
import com.example.myaplication.model.response.LogInResponse;

import retrofit2.Call;
import retrofit2.Callback;

public class AuthRepository {
    private final ApiService apiService;

    public AuthRepository(Context context) {
        apiService = ApiClient.getClient(context);
    }

    public void login(LogInRequest request,
                      Callback<LogInResponse> callback) {

        Call<LogInResponse> call = apiService.login(request);

        call.enqueue(callback);
    }



}
