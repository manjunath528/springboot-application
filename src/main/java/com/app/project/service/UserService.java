package com.app.project.service;

import com.app.project.service.dto.*;

import com.app.project.baseframework.exception.SystemException;


public interface UserService {

    UserSignUpResponse signUp(UserSignUpRequest userSignUpRequest) throws SystemException;

    UserLogInResponse userLogin(UserLogInRequest userLogInRequest) throws SystemException;

    UserLogOutResponse userLogout(UserLogOutRequest userLogOutRequest) throws SystemException;

}
