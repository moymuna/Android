package com.example.myaplication.model.response;

import lombok.Data;

@Data
public class NoticeResponse {
    private Long id;
    private String title;
    private String description;
    private String publishDate;
    private Long officeId;
    private String officeName;
}
