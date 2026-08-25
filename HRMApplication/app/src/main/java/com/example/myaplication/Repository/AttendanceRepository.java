package com.example.myaplication.Repository;

import android.content.Context;
import com.example.myaplication.api.ApiClient;
import com.example.myaplication.api.ApiService;
import com.example.myaplication.model.response.AttendanceResponse;
import retrofit2.Call;
import retrofit2.Callback;

public class AttendanceRepository {
    private final ApiService apiService;

    public AttendanceRepository(Context context) {
        apiService = ApiClient.getClient(context);
    }

    public void clockIn(Long employeeId, Callback<AttendanceResponse> callback) {
        apiService.clockIn(employeeId).enqueue(callback);
    }

    public void clockOut(Long employeeId, Callback<AttendanceResponse> callback) {
        apiService.clockOut(employeeId).enqueue(callback);
    }

    public void getTodayAttendance(Long employeeId, Callback<AttendanceResponse> callback) {
        apiService.getTodayAttendance(employeeId).enqueue(callback);
    }
}
