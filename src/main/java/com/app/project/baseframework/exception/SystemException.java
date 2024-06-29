package com.app.project.baseframework.exception;

import com.app.project.baseframework.exception.enums.ApiErrors;

/**
 * Created by Manjunath Reddy
 */
public class SystemException extends BaseException {
    public SystemException(ApiErrors apiErrorsEnum) {
        super(apiErrorsEnum.getHttpStatus(), apiErrorsEnum.getErrorCode(), apiErrorsEnum.getErrorDescription());
    }

}
