package com.app.project.service.dto;



import com.app.project.entity.*;

import java.io.Serializable;
import java.util.List;

public class UserAccountProfileResponse implements Serializable {
    private String loginId;
    private String emailId;
    private String accountStatus;

    private UserPersonalDetails userPersonalDetails;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public UserPersonalDetails getUserPersonalDetails() {
        return userPersonalDetails;
    }

    public void setUserPersonalDetails(UserPersonalDetails userPersonalDetails) {
        this.userPersonalDetails = userPersonalDetails;
    }


    @Override
    public String toString() {
        return "UserAccountProfileResponse{" +
                "loginId='" + loginId + '\'' +
                ", emailId='" + emailId + '\'' +
                ", accountStatus='" + accountStatus + '\'' +
                ", userPersonalDetails=" + userPersonalDetails +
                '}';
    }
}