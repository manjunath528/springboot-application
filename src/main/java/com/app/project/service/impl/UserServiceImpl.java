package com.app.project.service.impl;

import com.app.project.baseframework.exception.enums.ApiErrors;
import com.app.project.entity.UserAccount;
import com.app.project.repository.UserAccountRepository;
import com.app.project.service.UserService;
import com.app.project.service.dto.*;
import com.app.project.common.Constants;
import com.app.project.common.DateTimeFormatterUtil;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.project.baseframework.exception.SystemException;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserAccountRepository userAccountRepository;

    @Override
    public UserSignUpResponse signUp(UserSignUpRequest userSignUpRequest) throws SystemException {
        logger.info("Request received -> {}", userSignUpRequest);
        if (userSignUpRequest == null || Strings.isNullOrEmpty(userSignUpRequest.getEmailId()) || Strings.isNullOrEmpty(userSignUpRequest.getLoginId())
                || Strings.isNullOrEmpty(userSignUpRequest.getPassword()) ) {
            logger.error("signUp: Missing mandatory data -> {}", userSignUpRequest);
            throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
        }
        if (userAccountRepository.findByEmailId(userSignUpRequest.getEmailId().toLowerCase()) != null) {
            logger.error("signUp: User already registered for emailId -> {}", userSignUpRequest.getEmailId());
            throw new SystemException(ApiErrors.USER_ALREADY_EXISTS);
        }
        if (userAccountRepository.findByLoginId(userSignUpRequest.getLoginId().toLowerCase()) != null) {
            logger.error("signUp: User already registered for loginId -> {}", userSignUpRequest.getLoginId());
            throw new SystemException(ApiErrors.USER_ALREADY_EXISTS);
        }
        UserAccount userAccount = new UserAccount();
        logger.info("userSignUpRequest: loginId -> {}, emailId -> {}", userSignUpRequest.getLoginId(), userSignUpRequest.getEmailId());
        userAccount.setLoginId(userSignUpRequest.getLoginId());
        userAccount.setEmailId(userSignUpRequest.getEmailId());
        userAccount.setPassword(userSignUpRequest.getPassword());
        userAccount.setStatus(Constants.STATUS_REGISTERED);
        userAccount.setCreatedTs(DateTimeFormatterUtil.getCurrentTimestampInUTC());
        userAccount.setUpdatedTs(DateTimeFormatterUtil.getCurrentTimestampInUTC());
        try {
            userAccountRepository.save(userAccount);
        } catch (Exception ex) {
            logger.error("signUp: Exception while saving the data. {}", ex);
            throw new SystemException(ApiErrors.INTERNAL_PROCESSING_ERROR);
        }
        logger.info("UserAccount registered successfully: loginId -> {}, emailId-> {}", userSignUpRequest.getLoginId(), userSignUpRequest.getEmailId());
        UserSignUpResponse userSignUpResponse = new UserSignUpResponse();
        userSignUpResponse.setLoginId(userAccount.getLoginId());
        userSignUpResponse.setEmailId(userAccount.getEmailId());
        userSignUpResponse.setAccountStatus(userAccount.getStatus());
        logger.info("Response sent successfully");
        return userSignUpResponse;
    }


    @Override
    public UserLogInResponse userLogin(UserLogInRequest userLogInRequest) throws SystemException {
        logger.info("Request received -> {}", userLogInRequest);
        UserLogInResponse userLogInResponse = new UserLogInResponse();
        if (userLogInRequest == null || Strings.isNullOrEmpty(userLogInRequest.getLoginId()) || Strings.isNullOrEmpty(userLogInRequest.getPassword())) {
            logger.error("userLogin: Missing mandatory data");
            throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
        }
        if (userAccountRepository.findByLoginId(userLogInRequest.getLoginId()) == null) {
            logger.error("userLogin: Account doesn't exists for loginId -> {}", userLogInRequest.getLoginId());
            throw new SystemException(ApiErrors.USER_DOESNOT_EXISTS);
        }
        logger.info("userLogin: User authorization check for loginId -> {}", userLogInRequest.getLoginId());
        UserAccount userAccount = userAccountRepository.userAuthorizationCheck(userLogInRequest.getLoginId(), userLogInRequest.getPassword());
        if (userAccount == null) {
            logger.error("userLogin: Password mismatch for -> {}", userLogInRequest.getLoginId());
            throw new SystemException(ApiErrors.USER_ACCOUNT_PASSWORD_MISMATCH);
        }
        userLogInResponse.setLoginId(userLogInRequest.getLoginId());
        userLogInResponse.setEmailId(userAccount.getEmailId());
        userLogInResponse.setAccountStatus(userAccount.getStatus());
        return userLogInResponse;
    }


    @Override
    public UserLogOutResponse userLogout(UserLogOutRequest userLogOutRequest) throws SystemException {
        logger.info("Request received -> {}", userLogOutRequest);
        if (userLogOutRequest == null || Strings.isNullOrEmpty(userLogOutRequest.getLoginId()) || Strings.isNullOrEmpty(userLogOutRequest.getToken())) {
            logger.error("userLogout: Missing mandatory data");
            throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
        }
        logger.info("userLogout: User logout for loginId -> {}", userLogOutRequest.getLoginId());
        //TODO -- Expire the token
        UserLogOutResponse userLogOutResponse = new UserLogOutResponse();
        userLogOutResponse.setLoginId(userLogOutRequest.getLoginId());
        userLogOutResponse.setMessage(Constants.USER_LOGOUT_MESSAGE);
        logger.info("userLogout: User logout is successful for loginId -> {}", userLogOutRequest.getLoginId());
        return userLogOutResponse;
    }

}
