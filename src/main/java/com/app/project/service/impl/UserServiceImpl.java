package com.app.project.service.impl;

import com.app.project.baseframework.exception.enums.ApiErrors;
import com.app.project.entity.UserAccount;
import com.app.project.entity.UserPersonalDetails;
import com.app.project.repository.UserAccountRepository;
import com.app.project.repository.UserPersonalDetailsRepository;
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

import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserAccountRepository userAccountRepository;

    @Autowired
    UserPersonalDetailsRepository userPersonalDetailsRepository;

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

    @Override
    public UserAccountResponse saveOrUpdateUserAccountProfile(UserAccountRequest userAccountRequest, boolean statusUpdateFlag) throws SystemException {
        logger.info("Request received -> {}, statusUpdateFlag -> {}", userAccountRequest, statusUpdateFlag);
        UserAccountResponse userAccountResponse = new UserAccountResponse();
        userAccountResponse.setLoginId(userAccountRequest.getLoginId());
        UserAccount userAccount = userAccountRepository.findByLoginId(userAccountRequest.getLoginId());
        logger.info("saveOrUpdateUserAccountProfile: userAccountRequest -> {}, statusUpdateFlag-> {}", userAccountRequest, statusUpdateFlag);
        if (userAccountRequest == null || Strings.isNullOrEmpty(userAccountRequest.getLoginId())) {
            logger.error("saveOrUpdateUserAccountProfile: Missing mandatory data -> {}", userAccountRequest);
            throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
        }
        if (userAccountRepository.findByLoginId(userAccountRequest.getLoginId()) == null) {
            logger.error("saveOrUpdateUserAccountProfile: User account doesn't exist for loginId -> {}", userAccountRequest.getLoginId());
            throw new SystemException(ApiErrors.USER_DOESNOT_EXISTS);
        }
        if (userAccountRequest.getPersonalInformation() != null) {
            logger.info("saveOrUpdateUserAccountProfile: Personal Information -> {}", userAccountRequest.getPersonalInformation());
            UserPersonalDetails userPersonalDetails = userPersonalDetailsRepository.findByLoginId(userAccountRequest.getLoginId());
            if (userPersonalDetails == null) {
                logger.info("saveOrUpdateUserAccountProfile: Existing profile details NOT available. Saving for loginId -> {}", userAccountRequest.getLoginId());
                userPersonalDetails = new UserPersonalDetails();
                userPersonalDetails.setCreatedTs(DateTimeFormatterUtil.getCurrentTimestampInUTC());
                userAccount.setStatus(Constants.STATUS_ACTIVE);
            } else {
                logger.info("saveOrUpdateUserAccountProfile: Existing profile details available. Updating for loginId -> {}", userAccountRequest.getLoginId());
            }
            userPersonalDetails.setLoginId(userAccountRequest.getLoginId());
            userPersonalDetails.setFirstName(userAccountRequest.getPersonalInformation().getFirstName());
            userPersonalDetails.setLastName(userAccountRequest.getPersonalInformation().getLastName());
            userPersonalDetails.setMobileNumber(userAccountRequest.getPersonalInformation().getMobileNumber());
            userPersonalDetails.setCountryId(userAccountRequest.getPersonalInformation().getCountryId());
            userPersonalDetails.setStateId(userAccountRequest.getPersonalInformation().getStateId());
            userPersonalDetails.setCityId(userAccountRequest.getPersonalInformation().getCityId());


            userPersonalDetails.setUpdatedTs(DateTimeFormatterUtil.getCurrentTimestampInUTC());
            userPersonalDetailsRepository.save(userPersonalDetails);
            logger.info("saveOrUpdateUserAccountProfile: Personal Information saved successfully -> {}", userAccountRequest.getPersonalInformation());
        }



        if (statusUpdateFlag) {
            if (userPersonalDetailsRepository.findByLoginId(userAccountRequest.getLoginId()) == null) {
                logger.info("saveOrUpdateUserAccountProfile: SignUp requirements not completed for loginId -> {}, status -> {}", userAccountRequest.getLoginId(), Constants.STATUS_REGISTERED);
                userAccountResponse.setStatus(Constants.STATUS_REGISTERED);
            } else
            {
                logger.info("saveOrUpdateUserAccountProfile: SignUp requirements are completed for loginId -> {}, status -> {}", userAccountRequest.getLoginId(), Constants.STATUS_ACTIVE);
                //messageCenterService.triggerMessage(Constants.SOURCE_SYSTEM, userAccountRequest.getLoginId(), Constants.MESSAGE_WELCOME, null);
                userAccountResponse.setStatus(Constants.STATUS_ACTIVE);

        }
        }

        else {
            userAccount = userAccountRepository.findByLoginId(userAccountRequest.getLoginId().toLowerCase());
            logger.info("saveOrUpdateUserAccountProfile: Status update not required for loginId -> {}, status -> {}", userAccountRequest.getLoginId(), userAccount.getStatus());
            userAccountResponse.setStatus(userAccount.getStatus());
        }
        logger.info("Response sent successfully for loginId -> {}", userAccountRequest.getLoginId());
        return userAccountResponse;
    }
    @Override
    public UserAccountProfileResponse retrieveUserAccountProfileByLoginId(String loginId) throws SystemException {
        logger.info("Request received -> {}", loginId);
        if (loginId == null) {
            logger.error("retrieveUserAccountProfileByLoginId: Missing mandatory data");
            throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
        }
        UserAccount userAccount = userAccountRepository.findByLoginId(loginId.toLowerCase());
        if (userAccount == null) {
            logger.error("retrieveUserAccountProfileByLoginId: User account doesn't exists");
            throw new SystemException(ApiErrors.USER_DOESNOT_EXISTS);
        }

        UserAccountProfileResponse userAccountProfileResponse = new UserAccountProfileResponse();
        userAccountProfileResponse.setLoginId(loginId);
        userAccountProfileResponse.setEmailId(userAccount.getEmailId());
        userAccountProfileResponse.setAccountStatus(userAccount.getStatus());
        userAccountProfileResponse.setUserPersonalDetails(userPersonalDetailsRepository.findByLoginId(loginId));
        return userAccountProfileResponse;
    }
    @Override
    public UserAccountProfileResponse retrieveUserAccountProfile(RetrieveUserProfileRequest retrieveUserProfileRequest) throws SystemException {
        logger.info("Request received -> {}", retrieveUserProfileRequest);
        if (retrieveUserProfileRequest == null || (Strings.isNullOrEmpty(retrieveUserProfileRequest.getEmailId()) && Strings.isNullOrEmpty(retrieveUserProfileRequest.getLoginId()))) {
            logger.error("retrieveUserAccountProfile: Missing mandatory data");
            throw new SystemException(ApiErrors.MISSING_MANDATORY_FIELDS_FOR_ATTRIBUTES);
        }
        if (!(Strings.isNullOrEmpty(retrieveUserProfileRequest.getEmailId())) && !(Strings.isNullOrEmpty(retrieveUserProfileRequest.getLoginId()))) {
            logger.info("retrieveUserAccountProfile: Retrieving user account details for emailId and loginId combination");
            UserAccount userAccount = userAccountRepository.findByLoginId(retrieveUserProfileRequest.getLoginId().toLowerCase());
            if (userAccount == null) {
                logger.error("retrieveUserAccountProfile: User account doesn't exists");
                throw new SystemException(ApiErrors.USER_DOESNOT_EXISTS);
            }
            if (userAccount.getEmailId().equalsIgnoreCase(retrieveUserProfileRequest.getEmailId())) {
                logger.info("retrieveUserAccountProfile: Retrieving user account details for emailId -> {} and loginId -> {}",
                        retrieveUserProfileRequest.getEmailId(), retrieveUserProfileRequest.getEmailId());
                return retrieveUserAccountProfileByLoginId(retrieveUserProfileRequest.getLoginId());
            } else {
                logger.error("retrieveUserAccountProfile: User account doesn't exists for emailId -> {} and loginId -> {}",
                        retrieveUserProfileRequest.getEmailId(), retrieveUserProfileRequest.getEmailId());
                throw new SystemException(ApiErrors.USER_DOESNOT_EXISTS);
            }
        }
        if (!(Strings.isNullOrEmpty(retrieveUserProfileRequest.getEmailId()))) {
            logger.info("retrieveUserAccountProfile: Retrieving user account details for emailId -> {}", retrieveUserProfileRequest.getEmailId());
            UserAccount userAccount = userAccountRepository.findByEmailId(retrieveUserProfileRequest.getEmailId().toLowerCase());
            if (userAccount == null) {
                logger.error("retrieveUserAccountProfile: User account doesn't exists for loginId -> {}", retrieveUserProfileRequest.getEmailId());
                throw new SystemException(ApiErrors.USER_DOESNOT_EXISTS);
            }
            logger.info("retrieveUserAccountProfile: User account exists for emailId  -> {}", retrieveUserProfileRequest.getEmailId());
            return retrieveUserAccountProfileByLoginId(userAccount.getLoginId());
        }
        if (!Strings.isNullOrEmpty(retrieveUserProfileRequest.getLoginId())) {
            logger.info("retrieveUserAccountProfile: Retrieving user account details for loginId -> {}", retrieveUserProfileRequest.getLoginId());
            return retrieveUserAccountProfileByLoginId(retrieveUserProfileRequest.getLoginId());
        }
        return null;
    }



}
