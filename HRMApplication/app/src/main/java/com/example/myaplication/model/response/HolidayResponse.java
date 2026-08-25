package com.example.myaplication.model.response;

import lombok.Data;

@Data
public class HolidayResponse {
    private Long id;
    private String name;
    private String date;
    private Boolean recurringYearly;
    private String description;
}
