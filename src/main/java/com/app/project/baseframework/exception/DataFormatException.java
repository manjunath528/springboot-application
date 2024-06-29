package com.app.project.baseframework.exception;


import com.app.project.baseframework.exception.enums.ApiErrors;

/**
 * Created by Manjunath Reddy
 */
public class DataFormatException extends BaseException {
    public DataFormatException(ApiErrors apiErrorsEnum) {
        super(apiErrorsEnum.getHttpStatus(), apiErrorsEnum.getErrorCode(), apiErrorsEnum.getErrorDescription());
    }
}