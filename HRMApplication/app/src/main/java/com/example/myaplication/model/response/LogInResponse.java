package com.example.myaplication.model.response;
import com.example.myaplication.Enum.Role;

import lombok.Data;
@Data
public class LogInResponse {
    private String token;
    private String refreshToken;
    private Long id;
    private String fullName;
    private String email;
    private Role role;
}
