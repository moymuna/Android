package com.example.myaplication.model.response;

import com.example.myaplication.Enum.EmployeeStatus;
import com.example.myaplication.Enum.EmploymentType;
import com.example.myaplication.Enum.Gender;

import java.util.Date;

import lombok.Data;

@Data
public class EmployeeResponse {

    private Long id;

    private String contractNo;

    private Date joiningDate;

    private Date dateOfExit;

    private EmployeeStatus status;

    private Date dateOfBirth;

    private Gender gender;

    private String bloodGroup;

    private String employeeCode;

    private EmploymentType employmentType;
    private String image;


    private Long departmentId;
    private String departmentName;


    private Long designationId;
    private String designationTitle;


    private Long officeId;
    private String officeName;


    private AddressResponse presentAddress;
    private AddressResponse permanentAddress;


    private Long userId;
    private String fullName;
    private String email;
    private String role;


    private Long managerId;
    private String managerName;
}
