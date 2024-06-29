package com.app.project.controller.interceptor;

import com.app.project.baseframework.exception.HttpException;
import com.app.project.baseframework.exception.enums.ApiErrors;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * Created by Manjunath Reddy
 */
abstract class PrivateApiAuthenticationInterceptor extends ApiAuthenticationInterceptor {
    public static final String PRIVATE_API_AUTHENTICATED_ATTRIBUTE = "PRIVATE_API_AUTHENTICATED";

    protected abstract boolean privateAuthenticationRequired(HandlerMethod handlerMethod);

    @Override
    protected boolean authenticate(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod) {
        if (!privateAuthenticationRequired(handlerMethod))
            return true;

        validateTokenOrThrow(request);

        request.setAttribute(PRIVATE_API_AUTHENTICATED_ATTRIBUTE, true);

        return true;
    }

    private void validateTokenOrThrow(HttpServletRequest request) {
        try {
            //TODO -- Implement validation
        } catch (IllegalArgumentException ex) {
            throw new HttpException(ApiErrors.INVALID_REQUEST.getHttpStatus(), UUID.randomUUID().toString(),
                    ApiErrors.INVALID_REQUEST.getErrorCode(), ApiErrors.INVALID_REQUEST.getErrorDescription(), ex);
        }
    }
}
