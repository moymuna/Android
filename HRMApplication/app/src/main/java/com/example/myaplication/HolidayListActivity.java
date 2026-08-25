package com.example.myaplication;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myaplication.Repository.HolidayRepository;
import com.example.myaplication.adapter.HolidayAdapter;
import com.example.myaplication.model.response.HolidayResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HolidayListActivity extends AppCompatActivity {

    private HolidayRepository holidayRepository;
    private RecyclerView rvHolidays;
    private HolidayAdapter adapter;
    private List<HolidayResponse> holidayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_holiday_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        holidayRepository = new HolidayRepository(this);

        rvHolidays = findViewById(R.id.rv_holidays);
        rvHolidays.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HolidayAdapter(holidayList);
        rvHolidays.setAdapter(adapter);

        loadHolidays();
    }

    private void loadHolidays() {
        holidayRepository.getAllHolidays(new Callback<List<HolidayResponse>>() {
            @Override
            public void onResponse(Call<List<HolidayResponse>> call, Response<List<HolidayResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    holidayList.clear();
                    holidayList.addAll(response.body());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(HolidayListActivity.this, "Failed to load holidays", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<HolidayResponse>> call, Throwable t) {
                Toast.makeText(HolidayListActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
