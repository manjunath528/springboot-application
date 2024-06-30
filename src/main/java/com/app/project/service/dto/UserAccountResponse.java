package com.app.project.service.dto;


import java.io.Serializable;

public class UserAccountResponse implements Serializable {
    private String loginId;
    private String status;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserAccountResponse{" +
                "loginId='" + loginId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
