package com.example.myaplication.model.response;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class SalaryResponse {
    private Long id;
    private BigDecimal basicSalary;
    private BigDecimal hra;
    private BigDecimal conveyanceAllowance;
    private BigDecimal medicalAllowance;
    private BigDecimal specialAllowance;
    private BigDecimal providentFund;
    private BigDecimal professionalTax;
    private BigDecimal incomeTax;
    private BigDecimal grossMonthly;
    private BigDecimal totalDeductions;
    private String effectiveFrom;
    private String effectiveTo;
    private Boolean active;
    private BigDecimal netMonthly;
    private Long employeeId;
    private String employeeName;
    private String employeeCode;
    private Long salaryGradeId;
    private Integer gradeNumber;
    private String gradeTitle;
}
