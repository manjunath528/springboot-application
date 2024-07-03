package com.app.project.controller;


import com.app.project.baseframework.controller.BaseController;
import com.app.project.baseframework.exception.BaseException;
import com.app.project.service.UserService;
import com.app.project.service.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiRoute.BASE_MAPPING_API_V1)
@CrossOrigin(origins = "*")
public class UserController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;



    @PostMapping(value = ApiRoute.USER_SIGN_UP)
    public ResponseEntity<UserSignUpResponse> userSignUp(@RequestBody UserSignUpRequest userSignUpRequest) throws BaseException {
        logger.info("userSignUpRequest: Received");
        return new ResponseEntity<>(userService.signUp(userSignUpRequest), HttpStatus.OK);
    }

    @PostMapping(value = ApiRoute.USER_LOG_IN)
    public ResponseEntity<UserLogInResponse> userLogin(@RequestBody UserLogInRequest userLogInRequest) throws BaseException {
        logger.info("userLogin: Received");
        return new ResponseEntity<>(userService.userLogin(userLogInRequest), HttpStatus.OK);
    }

    @PostMapping(value = ApiRoute.USER_LOG_OUT)
    public ResponseEntity<UserLogOutResponse> userLogout(@RequestBody UserLogOutRequest userLogOutRequest) throws BaseException{
        logger.info("userLogout: Received");
        return new ResponseEntity<>(userService.userLogout(userLogOutRequest), HttpStatus.OK);
    }

    @PostMapping(value = ApiRoute.USER_ACCOUNT_PERSONAL_PROFILE)
    public ResponseEntity<UserAccountResponse> saveUserAccountProfile(@RequestBody UserAccountRequest userAccountRequest) throws BaseException {
        logger.info("saveUserAccountProfile: Received");
        return new ResponseEntity<>(userService.saveOrUpdateUserAccountProfile(userAccountRequest, true), HttpStatus.OK);
    }

    @PutMapping(value = ApiRoute.USER_ACCOUNT_PERSONAL_PROFILE)
    public ResponseEntity<UserAccountResponse> updateUserAccountProfile(@RequestBody UserAccountRequest userAccountRequest) throws BaseException {
        logger.info("updateUserAccountProfile: Received");
        return new ResponseEntity<>(userService.saveOrUpdateUserAccountProfile(userAccountRequest, false), HttpStatus.OK);
    }
    @GetMapping(value = ApiRoute.USER_ACCOUNT_PERSONAL_PROFILE)
    public ResponseEntity<UserAccountProfileResponse> retrieveUserAccountProfileByLoginId(@RequestParam(value = "loginId") String loginId) throws BaseException {
        logger.info("retrieveUserAccountProfileByLoginId: Received for loginId -> {}", loginId);
        return new ResponseEntity<>(userService.retrieveUserAccountProfileByLoginId(loginId), HttpStatus.OK);
    }

    @PostMapping(value = ApiRoute.USER_ACCOUNT_PERSONAL_PROFILE_RETRIEVE)
    public ResponseEntity<UserAccountProfileResponse> retrieveUserAccountProfile(@RequestBody RetrieveUserProfileRequest retrieveUserProfileRequest) throws BaseException {
        logger.info("retrieveUserAccountProfile: Received  -> {}", retrieveUserProfileRequest);
        return new ResponseEntity<>(userService.retrieveUserAccountProfile(retrieveUserProfileRequest), HttpStatus.OK);
    }

    @PostMapping(value = ApiRoute.USER_ACCOUNT_HEALTH_PROFILE)
    public ResponseEntity<UserHealthProfileResponse> saveUserHealthProfile(@RequestBody UserHealthProfileRequest userHealthProfileRequest) throws BaseException {
        logger.info("saveOrUpdateUserHealthProfile: Received");
        return new ResponseEntity<>(userService.saveOrUpdateUserHealthProfile(userHealthProfileRequest, true), HttpStatus.OK);
    }

    @PutMapping(value = ApiRoute.USER_ACCOUNT_HEALTH_PROFILE)
    public ResponseEntity<UserHealthProfileResponse> updateUserHealthProfile(@RequestBody UserHealthProfileRequest userHealthProfileRequest) throws BaseException {
        logger.info("saveOrUpdateUserHealthProfile: Received");
        return new ResponseEntity<>(userService.saveOrUpdateUserHealthProfile(userHealthProfileRequest, false), HttpStatus.OK);
    }
    @DeleteMapping(value = ApiRoute.USER_ACCOUNT_DETAILS_DELETE)
    public ResponseEntity<UserDeleteResponse> userAccountDetailsDelete(@RequestParam(value = "loginId") String loginId) throws BaseException {
        logger.info("userAccountDetailsDelete: Received");
        return new ResponseEntity<>(userService.userAccountDetailsDelete(loginId), HttpStatus.OK);
    }

}
