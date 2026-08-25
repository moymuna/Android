package com.example.myaplication;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myaplication.Repository.NoticeRepository;
import com.example.myaplication.adapter.NoticeAdapter;
import com.example.myaplication.model.response.NoticeResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoticeBoardActivity extends AppCompatActivity {

    private NoticeRepository noticeRepository;
    private RecyclerView rvNotices;
    private NoticeAdapter adapter;
    private List<NoticeResponse> noticeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_board);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        noticeRepository = new NoticeRepository(this);

        rvNotices = findViewById(R.id.rv_notices);
        rvNotices.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NoticeAdapter(noticeList);
        rvNotices.setAdapter(adapter);

        loadNotices();
    }

    private void loadNotices() {
        noticeRepository.getAllNotices(new Callback<List<NoticeResponse>>() {
            @Override
            public void onResponse(Call<List<NoticeResponse>> call, Response<List<NoticeResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    noticeList.clear();
                    noticeList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(NoticeBoardActivity.this, "Failed to load notices", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<NoticeResponse>> call, Throwable t) {
                Toast.makeText(NoticeBoardActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
