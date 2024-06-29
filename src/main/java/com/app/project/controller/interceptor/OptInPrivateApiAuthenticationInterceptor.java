package com.app.project.controller.interceptor;

import com.app.project.common.AnnotationUtils;
import com.app.project.controller.auth.PrivateApiAuthenticated;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Created by Manjunath Reddy
 */
public class OptInPrivateApiAuthenticationInterceptor extends PrivateApiAuthenticationInterceptor {

    protected boolean privateAuthenticationRequired(HandlerMethod handlerMethod) {
        return AnnotationUtils.findAnnotation(handlerMethod, PrivateApiAuthenticated.class).isPresent();
    }

}