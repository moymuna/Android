package com.example.myaplication.Repository;

import android.content.Context;
import com.example.myaplication.api.ApiClient;
import com.example.myaplication.api.ApiService;
import com.example.myaplication.model.response.NoticeResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;

public class NoticeRepository {
    private final ApiService apiService;

    public NoticeRepository(Context context) {
        apiService = ApiClient.getClient(context);
    }

    public void getAllNotices(Callback<List<NoticeResponse>> callback) {
        apiService.getAllNotices().enqueue(callback);
    }
}
