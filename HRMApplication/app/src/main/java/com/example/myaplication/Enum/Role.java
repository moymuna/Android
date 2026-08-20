package com.example.myaplication.Enum;

public enum Role {
    ADMIN,
    HR,
    MANAGER,
    EMPLOYEE,
    APPLICANT;

    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
