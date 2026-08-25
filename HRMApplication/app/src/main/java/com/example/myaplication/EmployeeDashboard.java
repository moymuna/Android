package com.example.myaplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

import com.example.myaplication.model.response.EmployeeResponse;
import com.example.myaplication.session.SesssoinManager;

public class EmployeeDashboard extends AppCompatActivity {

    // =====================================================
    // SESSION
    // =====================================================

    private SesssoinManager sessionManager;
    private EmployeeResponse employee;


    // =====================================================
    // EMPLOYEE PROFILE
    // =====================================================

    private ImageView ivEmployeePhoto;

    private TextView tvWelcome;
    private TextView tvEmployeeName;
    private TextView tvEmployeeCode;

    private TextView tvProfileName;
    private TextView tvDesignation;
    private TextView tvDepartment;
    private TextView tvEmploymentType;


    // =====================================================
    // TODAY ATTENDANCE
    // =====================================================

    private TextView tvAttendanceStatus;
    private TextView tvAttendanceTime;

    private MaterialButton btnAttendance;


    // =====================================================
    // SUMMARY
    // =====================================================

    private TextView tvLeaveBalance;
    private TextView tvPendingRequests;


    // =====================================================
    // QUICK ACTIONS
    // =====================================================

    private MaterialButton btnApplyLeave;
    private MaterialButton btnLeaveBalance;
    private MaterialButton btnSalary;

    private MaterialButton btnPayslip;
    private MaterialButton btnDocuments;
    private MaterialButton btnNotice;

    private MaterialButton btnHoliday;
    private MaterialButton btnProject;
    private MaterialButton btnTraining;
    private MaterialButton btnAdvanceSalary;


    // =====================================================
    // UPCOMING HOLIDAYS
    // =====================================================

    private TextView tvHolidayOne;
    private TextView tvHolidayTwo;


    // =====================================================
    // ANNOUNCEMENT
    // =====================================================

    private TextView tvAnnouncementTitle;
    private TextView tvAnnouncementDescription;


    // =====================================================
    // THIS MONTH
    // =====================================================

    private TextView tvPresentCount;
    private TextView tvAbsentCount;
    private TextView tvLeaveCount;
    private TextView tvWorkedHours;


    // =====================================================
    // LOGOUT
    // =====================================================

    private MaterialButton btnLogout;


    // =====================================================
    // ON CREATE
    // =====================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_employee_dashboard);


        // =================================================
        // INITIALIZE VIEWS
        // =================================================

        initViews();


        // =================================================
        // SESSION
        // =================================================

        sessionManager = new SesssoinManager(this);

        employee = sessionManager.getEmployee();


        // =================================================
        // CHECK EMPLOYEE SESSION
        // =================================================

        if (employee == null) {

            goToLogin();

            return;
        }


        // =================================================
        // LOAD EMPLOYEE INFORMATION
        // =================================================

        loadEmployeeData();


        // =================================================
        // SET DEFAULT DASHBOARD DATA
        // =================================================

        setDefaultDashboardData();


        // =================================================
        // BUTTON LISTENERS
        // =================================================

        setupButtonListeners();
    }


    // =====================================================
    // INITIALIZE ALL VIEWS
    // =====================================================

    private void initViews() {


        // =================================================
        // EMPLOYEE PROFILE
        // =================================================

        ivEmployeePhoto = findViewById(
                R.id.ivEmployeePhoto
        );


        tvWelcome = findViewById(
                R.id.tvWelcome
        );


        tvEmployeeName = findViewById(
                R.id.tvEmployeeName
        );


        tvEmployeeCode = findViewById(
                R.id.tvEmployeeCode
        );


        tvProfileName = findViewById(
                R.id.tvProfileName
        );


        tvDesignation = findViewById(
                R.id.tvDesignation
        );


        tvDepartment = findViewById(
                R.id.tvDepartment
        );


        tvEmploymentType = findViewById(
                R.id.tvEmploymentType
        );


        // =================================================
        // ATTENDANCE
        // =================================================

        tvAttendanceStatus = findViewById(
                R.id.tvAttendanceStatus
        );


        tvAttendanceTime = findViewById(
                R.id.tvAttendanceTime
        );


        btnAttendance = findViewById(
                R.id.btnAttendance
        );


        // =================================================
        // SUMMARY
        // =================================================

        tvLeaveBalance = findViewById(
                R.id.tvLeaveBalance
        );


        tvPendingRequests = findViewById(
                R.id.tvPendingRequests
        );


        // =================================================
        // QUICK ACTIONS
        // =================================================

        btnApplyLeave = findViewById(
                R.id.btnApplyLeave
        );


        btnLeaveBalance = findViewById(
                R.id.btnLeaveBalance
        );


        btnSalary = findViewById(
                R.id.btnSalary
        );


        btnPayslip = findViewById(
                R.id.btnPayslip
        );


        btnDocuments = findViewById(
                R.id.btnDocuments
        );


        btnNotice = findViewById(
                R.id.btnNotice
        );


        btnHoliday = findViewById(
                R.id.btnHoliday
        );


        btnProject = findViewById(
                R.id.btnProject
        );


        btnTraining = findViewById(
                R.id.btnTraining
        );

        btnAdvanceSalary = findViewById(
                R.id.btnAdvanceSalary
        );


        // =================================================
        // UPCOMING HOLIDAYS
        // =================================================

        tvHolidayOne = findViewById(
                R.id.tvHolidayOne
        );


        tvHolidayTwo = findViewById(
                R.id.tvHolidayTwo
        );


        // =================================================
        // ANNOUNCEMENT
        // =================================================

        tvAnnouncementTitle = findViewById(
                R.id.tvAnnouncementTitle
        );


        tvAnnouncementDescription = findViewById(
                R.id.tvAnnouncementDescription
        );


        // =================================================
        // THIS MONTH
        // =================================================

        tvPresentCount = findViewById(
                R.id.tvPresentCount
        );


        tvAbsentCount = findViewById(
                R.id.tvAbsentCount
        );


        tvLeaveCount = findViewById(
                R.id.tvLeaveCount
        );


        tvWorkedHours = findViewById(
                R.id.tvWorkedHours
        );


        // =================================================
        // LOGOUT
        // =================================================

        btnLogout = findViewById(
                R.id.btnLogout
        );
    }


    // =====================================================
    // LOAD EMPLOYEE DATA
    // =====================================================

    private void loadEmployeeData() {


        // =================================================
        // EMPLOYEE NAME
        // =================================================

        String employeeName = "Employee";


        if (employee.getFullName() != null &&
                !employee.getFullName().trim().isEmpty()) {

            employeeName = employee.getFullName();
        }


        // =================================================
        // WELCOME
        // =================================================

        tvWelcome.setText(
                "Welcome back 👋"
        );


        // =================================================
        // WELCOME EMPLOYEE NAME
        // =================================================

        tvEmployeeName.setText(
                employeeName
        );


        // =================================================
        // PROFILE NAME
        // =================================================

        tvProfileName.setText(
                employeeName
        );


        // =================================================
        // EMPLOYEE CODE
        // =================================================

        String employeeCode = "N/A";


        if (employee.getEmployeeCode() != null &&
                !employee.getEmployeeCode().trim().isEmpty()) {

            employeeCode = employee.getEmployeeCode();
        }


        tvEmployeeCode.setText(
                "Employee ID: " + employeeCode
        );


        // =================================================
        // DESIGNATION
        // =================================================

        if (employee.getDesignationTitle() != null &&
                !employee.getDesignationTitle().trim().isEmpty()) {

            tvDesignation.setText(
                    employee.getDesignationTitle()
            );

        } else {

            tvDesignation.setText(
                    "Designation"
            );
        }


        // =================================================
        // DEPARTMENT
        // =================================================

        if (employee.getDepartmentName() != null &&
                !employee.getDepartmentName().trim().isEmpty()) {

            tvDepartment.setText(
                    employee.getDepartmentName()
            );

        } else {

            tvDepartment.setText(
                    "Department"
            );
        }


        // =================================================
        // EMPLOYMENT TYPE
        // =================================================

        if (employee.getEmploymentType() != null) {

            tvEmploymentType.setText(
                    employee.getEmploymentType().toString()
            );

        } else {

            tvEmploymentType.setText(
                    "FULL TIME"
            );
        }


        // =================================================
        // EMPLOYEE PHOTO
        // =================================================

        if (employee.getImage() != null &&
                !employee.getImage().trim().isEmpty()) {

            Glide.with(this)
                    .load(employee.getImage())
                    .placeholder(
                            R.drawable.ic_profile_placeholder
                    )
                    .error(
                            R.drawable.ic_profile_placeholder
                    )
                    .into(ivEmployeePhoto);

        } else {

            ivEmployeePhoto.setImageResource(
                    R.drawable.ic_profile_placeholder
            );
        }
    }


    // =====================================================
    // DEFAULT DASHBOARD DATA
    // =====================================================

    private void setDefaultDashboardData() {


        // =================================================
        // ATTENDANCE
        // =================================================

        tvAttendanceStatus.setText(
                "Not Marked"
        );


        tvAttendanceTime.setText(
                "Attendance for today"
        );


        btnAttendance.setText(
                "Clock In"
        );


        // =================================================
        // LEAVE
        // =================================================

        tvLeaveBalance.setText(
                "--"
        );


        // =================================================
        // PENDING REQUESTS
        // =================================================

        tvPendingRequests.setText(
                "--"
        );


        // =================================================
        // HOLIDAYS
        // =================================================

        tvHolidayOne.setText(
                "No upcoming holiday information"
        );


        tvHolidayTwo.setText(
                ""
        );


        // =================================================
        // ANNOUNCEMENT
        // =================================================

        tvAnnouncementTitle.setText(
                "No recent announcement"
        );


        tvAnnouncementDescription.setText(
                ""
        );


        // =================================================
        // THIS MONTH
        // =================================================

        tvPresentCount.setText(
                "Present\n--"
        );


        tvAbsentCount.setText(
                "Absent\n--"
        );


        tvLeaveCount.setText(
                "On Leave\n--"
        );


        tvWorkedHours.setText(
                "Hours\n--"
        );
    }


    // =====================================================
    // BUTTON LISTENERS
    // =====================================================

    private void setupButtonListeners() {


        // =================================================
        // ATTENDANCE
        // =================================================

        btnAttendance.setOnClickListener(v -> {
            startActivity(new Intent(EmployeeDashboard.this, AttendanceActivity.class));
        });


        // =================================================
        // APPLY LEAVE
        // =================================================

        btnApplyLeave.setOnClickListener(v -> {
            startActivity(new Intent(EmployeeDashboard.this, ApplyLeaveActivity.class));
        });


        // =================================================
        // LEAVE BALANCE
        // =================================================

        btnLeaveBalance.setOnClickListener(v -> {
            startActivity(new Intent(EmployeeDashboard.this, LeaveBalanceActivity.class));
        });


        // =================================================
        // SALARY
        // =================================================

        btnSalary.setOnClickListener(v -> {
            startActivity(new Intent(EmployeeDashboard.this, MySalaryActivity.class));
        });


        // =================================================
        // PAYSLIP
        // =================================================

        btnPayslip.setOnClickListener(v -> {
            startActivity(new Intent(EmployeeDashboard.this, PayslipActivity.class));
        });


        // =================================================
        // DOCUMENTS
        // =================================================

        btnDocuments.setOnClickListener(v -> {
            startActivity(new Intent(EmployeeDashboard.this, DocumentActivity.class));
        });


        // =================================================
        // NOTICE
        // =================================================

        btnNotice.setOnClickListener(v -> {
            startActivity(new Intent(EmployeeDashboard.this, NoticeBoardActivity.class));
        });


        // =================================================
        // HOLIDAYS
        // =================================================

        btnHoliday.setOnClickListener(v -> {
            startActivity(new Intent(EmployeeDashboard.this, HolidayListActivity.class));
        });


        // =================================================
        // PROJECTS
        // =================================================

        btnProject.setOnClickListener(v -> {
            startActivity(new Intent(EmployeeDashboard.this, ProjectActivity.class));
        });


        // =================================================
        // TRAINING
        // =================================================

        btnTraining.setOnClickListener(v -> {
            startActivity(new Intent(EmployeeDashboard.this, TrainingActivity.class));
        });

        btnAdvanceSalary.setOnClickListener(v -> {
            startActivity(new Intent(EmployeeDashboard.this, AdvanceSalaryActivity.class));
        });


        // =================================================
        // LOGOUT
        // =================================================

        btnLogout.setOnClickListener(v -> {

            logout();
        });
    }


    // =====================================================
    // LOGOUT
    // =====================================================

    private void logout() {

        if (sessionManager != null) {

            sessionManager.logout();
        }


        Intent intent = new Intent(
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


    // =====================================================
    // GO TO LOGIN
    // =====================================================

    private void goToLogin() {

        Intent intent = new Intent(
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