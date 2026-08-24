package com.example.myaplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.navigation.NavigationView;

import com.example.myaplication.model.response.EmployeeResponse;
import com.example.myaplication.session.SesssoinManager;

public class EmployeeDashboard extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private MaterialToolbar toolbar;

    private ShapeableImageView imgEmployee;

    private TextView tvWelcome;
    private TextView tvEmployeeName;
    private TextView tvEmployeeCode;
    private TextView tvDesignation;
    private TextView tvDepartment;

    private SesssoinManager sessionManager;
    private EmployeeResponse employee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_employee_dashboard);


        // =========================================
        // INITIALIZE
        // =========================================

        initViews();


        // =========================================
        // SESSION
        // =========================================

        sessionManager = new SesssoinManager(this);

        employee = sessionManager.getEmployee();


        // =========================================
        // CHECK LOGIN
        // =========================================

        if (employee == null) {

            goToLogin();

            return;
        }


        // =========================================
        // LOAD EMPLOYEE DATA
        // =========================================

        loadEmployeeData();


        // =========================================
        // HAMBURGER MENU
        // =========================================

        toolbar.setNavigationOnClickListener(v -> {

            drawerLayout.openDrawer(navigationView);

        });


        // =========================================
        // NOTIFICATION
        // =========================================

        findViewById(R.id.btnNotification)
                .setOnClickListener(v -> {

                    Toast.makeText(
                            EmployeeDashboard.this,
                            "Notifications",
                            Toast.LENGTH_SHORT
                    ).show();

                });


        // =========================================
        // NAVIGATION MENU
        // =========================================

        navigationView.setNavigationItemSelectedListener(item -> {

            int id = item.getItemId();


            if (id == R.id.nav_dashboard) {

                drawerLayout.closeDrawer(navigationView);

            }


            else if (id == R.id.nav_attendance) {

                Toast.makeText(
                        this,
                        "Attendance",
                        Toast.LENGTH_SHORT
                ).show();

                drawerLayout.closeDrawer(navigationView);

            }


            else if (id == R.id.nav_leave) {

                Toast.makeText(
                        this,
                        "Leave",
                        Toast.LENGTH_SHORT
                ).show();

                drawerLayout.closeDrawer(navigationView);

            }


            else if (id == R.id.nav_salary) {

                Toast.makeText(
                        this,
                        "My Salary",
                        Toast.LENGTH_SHORT
                ).show();

                drawerLayout.closeDrawer(navigationView);

            }


            else if (id == R.id.nav_payslip) {

                Toast.makeText(
                        this,
                        "Payslip",
                        Toast.LENGTH_SHORT
                ).show();

                drawerLayout.closeDrawer(navigationView);

            }


            else if (id == R.id.nav_documents) {

                Toast.makeText(
                        this,
                        "Documents",
                        Toast.LENGTH_SHORT
                ).show();

                drawerLayout.closeDrawer(navigationView);

            }


            else if (id == R.id.nav_notice) {

                Toast.makeText(
                        this,
                        "Notice Board",
                        Toast.LENGTH_SHORT
                ).show();

                drawerLayout.closeDrawer(navigationView);

            }


            else if (id == R.id.nav_holiday) {

                Toast.makeText(
                        this,
                        "Holiday",
                        Toast.LENGTH_SHORT
                ).show();

                drawerLayout.closeDrawer(navigationView);

            }


            else if (id == R.id.nav_project) {

                Toast.makeText(
                        this,
                        "Project",
                        Toast.LENGTH_SHORT
                ).show();

                drawerLayout.closeDrawer(navigationView);

            }


            else if (id == R.id.nav_training) {

                Toast.makeText(
                        this,
                        "Training",
                        Toast.LENGTH_SHORT
                ).show();

                drawerLayout.closeDrawer(navigationView);

            }


            else if (id == R.id.nav_logout) {

                logout();

            }

            return true;
        });


        // =========================================
        // DASHBOARD LOGOUT
        // =========================================

        findViewById(R.id.btnLogout)
                .setOnClickListener(v -> logout());
    }


    // =====================================================
    // INITIALIZE VIEWS
    // =====================================================

    private void initViews() {

        drawerLayout = findViewById(R.id.drawerLayout);

        navigationView = findViewById(R.id.navigationView);

        toolbar = findViewById(R.id.toolbar);


        imgEmployee = findViewById(R.id.imgEmployee);

        tvWelcome = findViewById(R.id.tvWelcome);

        tvEmployeeName = findViewById(R.id.tvEmployeeName);

        tvEmployeeCode = findViewById(R.id.tvEmployeeCode);

        tvDesignation = findViewById(R.id.tvDesignation);

        tvDepartment = findViewById(R.id.tvDepartment);

    }


    // =====================================================
    // LOAD EMPLOYEE DATA
    // =====================================================

    private void loadEmployeeData() {

        tvWelcome.setText("Welcome");

        tvEmployeeName.setText(
                employee.getFullName() != null
                        ? employee.getFullName()
                        : "Employee"
        );


        tvEmployeeCode.setText(
                "Employee ID: " +
                        (employee.getEmployeeCode() != null
                                ? employee.getEmployeeCode()
                                : "N/A")
        );


        tvDesignation.setText(
                "Designation: " +
                        (employee.getDesignationTitle() != null
                                ? employee.getDesignationTitle()
                                : "N/A")
        );


        tvDepartment.setText(
                "Department: " +
                        (employee.getDepartmentName() != null
                                ? employee.getDepartmentName()
                                : "N/A")
        );


        // =========================================
        // LOAD EMPLOYEE PHOTO
        // =========================================

        if (employee.getImage() != null &&
                !employee.getImage().trim().isEmpty()) {

            Glide.with(this)
                    .load(employee.getImage())
                    .placeholder(R.drawable.ic_person)
                    .error(R.drawable.ic_person)
                    .into(imgEmployee);

        } else {

            imgEmployee.setImageResource(
                    R.drawable.ic_person
            );
        }


        // =========================================
        // LOAD DRAWER HEADER
        // =========================================

        android.view.View headerView =
                navigationView.getHeaderView(0);


        ShapeableImageView navEmployeeImage =
                headerView.findViewById(
                        R.id.navEmployeeImage
                );


        TextView navEmployeeName =
                headerView.findViewById(
                        R.id.navEmployeeName
                );


        TextView navEmployeeCode =
                headerView.findViewById(
                        R.id.navEmployeeCode
                );


        navEmployeeName.setText(
                employee.getFullName() != null
                        ? employee.getFullName()
                        : "Employee"
        );


        navEmployeeCode.setText(
                employee.getEmployeeCode() != null
                        ? employee.getEmployeeCode()
                        : "Employee ID"
        );


        if (employee.getImage() != null &&
                !employee.getImage().trim().isEmpty()) {

            Glide.with(this)
                    .load(employee.getImage())
                    .placeholder(R.drawable.ic_person)
                    .error(R.drawable.ic_person)
                    .into(navEmployeeImage);

        } else {

            navEmployeeImage.setImageResource(
                    R.drawable.ic_person
            );
        }
    }


    // =====================================================
    // LOGOUT
    // =====================================================

    private void logout() {

        sessionManager.logout();


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
    // GO LOGIN
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