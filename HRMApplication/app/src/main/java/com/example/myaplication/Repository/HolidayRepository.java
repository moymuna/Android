package com.example.myaplication.Repository;

import android.content.Context;
import com.example.myaplication.api.ApiClient;
import com.example.myaplication.api.ApiService;
import com.example.myaplication.model.response.HolidayResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;

public class HolidayRepository {
    private final ApiService apiService;

    public HolidayRepository(Context context) {
        apiService = ApiClient.getClient(context);
    }

    public void getAllHolidays(Callback<List<HolidayResponse>> callback) {
        apiService.getAllHolidays().enqueue(callback);
    }

    public void getUpcomingHolidays(int limit, Callback<List<HolidayResponse>> callback) {
        apiService.getUpcomingHolidays(limit).enqueue(callback);
    }
}
