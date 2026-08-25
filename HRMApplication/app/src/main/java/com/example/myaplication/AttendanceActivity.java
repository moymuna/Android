package com.example.myaplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myaplication.Repository.AttendanceRepository;
import com.example.myaplication.model.response.AttendanceResponse;
import com.example.myaplication.session.SesssoinManager;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttendanceActivity extends AppCompatActivity {

    private TextView tvCheckInTime, tvWorkedHours, tvStatus;
    private MaterialButton btnClockIn, btnClockOut;
    private AttendanceRepository attendanceRepository;
    private SesssoinManager sessionManager;
    private Long employeeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        initViews();
        attendanceRepository = new AttendanceRepository(this);
        sessionManager = new SesssoinManager(this);

        if (sessionManager.getEmployee() != null) {
            employeeId = sessionManager.getEmployee().getId();
            loadTodayAttendance();
        } else {
            Toast.makeText(this, "Employee session not found", Toast.LENGTH_SHORT).show();
            finish();
        }

        btnClockIn.setOnClickListener(v -> clockIn());
        btnClockOut.setOnClickListener(v -> clockOut());
    }

    private void initViews() {
        tvCheckInTime = findViewById(R.id.tvCheckInTime);
        tvWorkedHours = findViewById(R.id.tvWorkedHours);
        tvStatus = findViewById(R.id.tvStatus);
        btnClockIn = findViewById(R.id.btnClockIn);
        btnClockOut = findViewById(R.id.btnClockOut);
    }

    private void loadTodayAttendance() {
        attendanceRepository.getTodayAttendance(employeeId, new Callback<AttendanceResponse>() {
            @Override
            public void onResponse(Call<AttendanceResponse> call, Response<AttendanceResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    updateUI(response.body());
                }
            }

            @Override
            public void onFailure(Call<AttendanceResponse> call, Throwable t) {
                Toast.makeText(AttendanceActivity.this, "Failed to load attendance", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clockIn() {
        attendanceRepository.clockIn(employeeId, new Callback<AttendanceResponse>() {
            @Override
            public void onResponse(Call<AttendanceResponse> call, Response<AttendanceResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(AttendanceActivity.this, "Clocked in successfully", Toast.LENGTH_SHORT).show();
                    updateUI(response.body());
                } else {
                    Toast.makeText(AttendanceActivity.this, "Clock-in failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AttendanceResponse> call, Throwable t) {
                Toast.makeText(AttendanceActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clockOut() {
        attendanceRepository.clockOut(employeeId, new Callback<AttendanceResponse>() {
            @Override
            public void onResponse(Call<AttendanceResponse> call, Response<AttendanceResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(AttendanceActivity.this, "Clocked out successfully", Toast.LENGTH_SHORT).show();
                    updateUI(response.body());
                } else {
                    Toast.makeText(AttendanceActivity.this, "Clock-out failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AttendanceResponse> call, Throwable t) {
                Toast.makeText(AttendanceActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI(AttendanceResponse attendance) {
        if (attendance.getCheckInTime() != null) {
            tvCheckInTime.setText(attendance.getCheckInTime());
            btnClockIn.setEnabled(false);
            btnClockOut.setEnabled(attendance.getCheckOutTime() == null);
        }

        if (attendance.getWorkedHours() != null) {
            tvWorkedHours.setText(String.format("%.2f hrs", attendance.getWorkedHours()));
        }

        if (attendance.getStatus() != null) {
            tvStatus.setText(attendance.getStatus());
            if (attendance.getStatus().equalsIgnoreCase("PRESENT")) {
                tvStatus.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
            }
        }
    }
}
