package com.example.myaplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myaplication.model.response.EmployeeResponse;
import com.example.myaplication.session.SesssoinManager;

public class EmployeeDashboard extends AppCompatActivity {

    // Employee information
    private TextView tvWelcome;
    private TextView tvEmployeeName;
    private TextView tvEmployeeCode;
    private TextView tvDesignation;
    private TextView tvDepartment;

    // Quick action buttons
    private Button btnAttendance;
    private Button btnApplyLeave;
    private Button btnLeaveBalance;
    private Button btnSalary;
    private Button btnPayslip;
    private Button btnDocuments;
    private Button btnNotice;
    private Button btnHoliday;
    private Button btnProject;
    private Button btnTraining;
    private Button btnLogout;

    private SesssoinManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_employee_dashboard);

        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {

                    Insets systemBars =
                            insets.getInsets(
                                    WindowInsetsCompat.Type.systemBars()
                            );

                    v.setPadding(
                            systemBars.left,
                            systemBars.top,
                            systemBars.right,
                            systemBars.bottom
                    );

                    return insets;
                }
        );

        init();

        loadEmployeeData();

        setupClickListeners();
    }

    // =========================================================
    // INITIALIZE
    // =========================================================

    private void init() {

        // Session
        sessionManager = new SesssoinManager(this);

        // Employee information
        tvWelcome = findViewById(R.id.tvWelcome);
        tvEmployeeName = findViewById(R.id.tvEmployeeName);
        tvEmployeeCode = findViewById(R.id.tvEmployeeCode);
        tvDesignation = findViewById(R.id.tvDesignation);
        tvDepartment = findViewById(R.id.tvDepartment);

        // Buttons
        btnAttendance = findViewById(R.id.btnAttendance);
        btnApplyLeave = findViewById(R.id.btnApplyLeave);
        btnLeaveBalance = findViewById(R.id.btnLeaveBalance);
        btnSalary = findViewById(R.id.btnSalary);
        btnPayslip = findViewById(R.id.btnPayslip);
        btnDocuments = findViewById(R.id.btnDocuments);
        btnNotice = findViewById(R.id.btnNotice);
        btnHoliday = findViewById(R.id.btnHoliday);
        btnProject = findViewById(R.id.btnProject);
        btnTraining = findViewById(R.id.btnTraining);
        btnLogout = findViewById(R.id.btnLogout);
    }

    // =========================================================
    // LOAD EMPLOYEE DATA
    // =========================================================

    private void loadEmployeeData() {

        EmployeeResponse employee =
                sessionManager.getEmployee();

        if (employee == null) {

            Toast.makeText(
                    this,
                    "Employee information not found",
                    Toast.LENGTH_SHORT
            ).show();

            logout();

            return;
        }

        // Full Name
        String fullName = employee.getFullName();

        if (fullName != null && !fullName.isEmpty()) {

            tvWelcome.setText("Welcome, " + fullName);
            tvEmployeeName.setText(fullName);

        } else {

            tvWelcome.setText("Welcome");
            tvEmployeeName.setText("Employee");
        }

        // Employee Code
        String employeeCode = employee.getEmployeeCode();

        if (employeeCode != null && !employeeCode.isEmpty()) {

            tvEmployeeCode.setText(
                    "Employee ID: " + employeeCode
            );

        } else {

            tvEmployeeCode.setText(
                    "Employee ID: N/A"
            );
        }

        // Designation
        String designation = employee.getDesignationTitle();

        if (designation != null && !designation.isEmpty()) {

            tvDesignation.setText(
                    "Designation: " + designation
            );

        } else {

            tvDesignation.setText(
                    "Designation: N/A"
            );
        }

        // Department
        String department = employee.getDepartmentName();

        if (department != null && !department.isEmpty()) {

            tvDepartment.setText(
                    "Department: " + department
            );

        } else {

            tvDepartment.setText(
                    "Department: N/A"
            );
        }
    }

    // =========================================================
    // CLICK LISTENERS
    // =========================================================

    private void setupClickListeners() {

        // Attendance
        btnAttendance.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            EmployeeDashboard.this,
                            AttendanceActivity.class
                    );

            startActivity(intent);
        });

        // Apply Leave
        btnApplyLeave.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            EmployeeDashboard.this,
                            ApplyLeaveActivity.class
                    );

            startActivity(intent);
        });

        // Leave Balance
        btnLeaveBalance.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            EmployeeDashboard.this,
                            LeaveBalanceActivity.class
                    );

            startActivity(intent);
        });

        // Salary
        btnSalary.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            EmployeeDashboard.this,
                            MySalaryActivity.class
                    );

            startActivity(intent);
        });

        // Payslip
        btnPayslip.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            EmployeeDashboard.this,
                            PayslipActivity.class
                    );

            startActivity(intent);
        });

        // Documents
        btnDocuments.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            EmployeeDashboard.this,
                            DocumentActivity.class
                    );

            startActivity(intent);
        });

        // Notice
        btnNotice.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            EmployeeDashboard.this,
                            NoticeBoardActivity.class
                    );

            startActivity(intent);
        });

        // Holiday
        btnHoliday.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            EmployeeDashboard.this,
                            HolidayListActivity.class
                    );

            startActivity(intent);
        });

        // Project
        btnProject.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            EmployeeDashboard.this,
                            ProjectActivity.class
                    );

            startActivity(intent);
        });

        // Training
        btnTraining.setOnClickListener(v -> {

            Intent intent =
                    new Intent(
                            EmployeeDashboard.this,
                            TrainingActivity.class
                    );

            startActivity(intent);
        });

        // Logout
        btnLogout.setOnClickListener(v -> logout());
    }

    // =========================================================
    // LOGOUT
    // =========================================================

    private void logout() {

        sessionManager.logout();

        Intent intent =
                new Intent(
                        EmployeeDashboard.this,
                        login.class
                );

        intent.setFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TASK
        );

        startActivity(intent);

        finish();
    }


}