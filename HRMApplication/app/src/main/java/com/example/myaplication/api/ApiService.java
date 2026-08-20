package com.example.myaplication.api;

import com.example.myaplication.model.request.LogInRequest;
import com.example.myaplication.model.response.EmployeeResponse;
import com.example.myaplication.model.response.LogInResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {

    @POST("api/auth/login")
    Call<LogInResponse> login(@Body LogInRequest request);

    @GET("api/employees/user/{userId}")
    Call<EmployeeResponse> getEmployeeByUserId(@Path("userId") Long userId);

}
