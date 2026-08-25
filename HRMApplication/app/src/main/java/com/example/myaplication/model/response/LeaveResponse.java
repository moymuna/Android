package com.example.myaplication.model.response;

import com.example.myaplication.Enum.LeaveType;
import lombok.Data;

@Data
public class LeaveResponse {
    private Long id;
    private String startDate;
    private String endDate;
    private Double totalDays;
    private String reason;
    private String status;
    private String decidedAt;
    private String rejectionReason;
    private Long employeeId;
    private String employeeName;
    private Long leaveTypeId;
    private LeaveType leaveTypeName;
}
