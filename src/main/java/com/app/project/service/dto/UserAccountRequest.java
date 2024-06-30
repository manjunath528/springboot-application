package com.app.project.service.dto;


import java.io.Serializable;
import java.util.List;

public class UserAccountRequest implements Serializable {
    private String loginId;
    private PersonalInformation personalInformation;

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public PersonalInformation getPersonalInformation() {
        return personalInformation;
    }

    public void setPersonalInformation(PersonalInformation personalInformation) {
        this.personalInformation = personalInformation;
    }


    @Override
    public String toString() {
        return "UserAccountRequest{" +
                "loginId='" + loginId + '\'' +
                ", personalInformation=" + personalInformation +
                '}';
    }
}