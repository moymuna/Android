package com.example.myaplication.model.request;

import lombok.Data;

@Data
public class LeaveRequest {
    private String startDate;
    private String endDate;
    private Double totalDays;
    private String reason;
    private String status;
    private String rejectionReason;
    private Long employeeId;
    private Long leaveTypeId;
}
