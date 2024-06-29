package com.app.project.baseframework.exception;


import com.app.project.baseframework.exception.enums.ApiErrors;

/**
 * Created by Manjunath Reddy
 */
public class BusinessException extends BaseException {

    public BusinessException(ApiErrors apiErrorsEnum) {
        super(apiErrorsEnum.getHttpStatus(), apiErrorsEnum.getErrorCode(), apiErrorsEnum.getErrorDescription());
    }

}
