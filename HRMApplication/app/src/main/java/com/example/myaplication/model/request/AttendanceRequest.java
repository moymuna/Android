package com.example.myaplication.model.request;

import lombok.Data;

@Data
public class AttendanceRequest {
    private String date;
    private String checkInTime;
    private String checkOutTime;
    private String status;
    private Long employeeId;
}
