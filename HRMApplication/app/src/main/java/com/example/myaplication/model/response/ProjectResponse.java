package com.example.myaplication.model.response;

import lombok.Data;
import java.util.List;

@Data
public class ProjectResponse {
    private Long id;
    private String projectName;
    private String description;
    private String startDate;
    private String endDate;
    private List<Long> employeeId;
    private List<String> employeeName;
    private Long officeId;
    private String officeName;
}
