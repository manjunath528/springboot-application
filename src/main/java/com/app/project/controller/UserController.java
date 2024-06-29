package com.app.project.controller;


import com.app.project.baseframework.controller.BaseController;
import com.app.project.baseframework.exception.BaseException;
import com.app.project.baseframework.exception.HttpException;
import com.app.project.service.UserService;
import com.app.project.service.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.SystemException;

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



}
