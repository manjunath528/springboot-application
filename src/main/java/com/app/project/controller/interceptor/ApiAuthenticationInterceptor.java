package com.app.project.controller.interceptor;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Manjunath Reddy
 */
public abstract class ApiAuthenticationInterceptor implements PreHandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // preHandle will be triggered twice in case of Async request due to deferredResult.
        // To distinguish between the initial request and the subsequent dispatch after asynchronous handling completes,
        // we check for dispatcherType to ignore processing
        if (DispatcherType.ASYNC.equals(request.getDispatcherType())) {
            return true;
        }

        if (!isAuthenticationRequired(handler))
            return true;

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        return authenticate(request, response, handlerMethod);
    }

    private boolean isAuthenticationRequired(Object handler) {
        // For custom controller (the ones we care about) the object will be a HandlerMethod.
        // For framework handlers (like Springs CORS handler) this may be another type of object,
        // in which case we just don't care about it.
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = ((HandlerMethod) handler);
            // ErrorControllers don't require authentication in any way.
            if (ErrorController.class.isAssignableFrom(handlerMethod.getBeanType()))
                return false;
            return isAuthenticationRequired(handlerMethod);
        }
        return false;
    }

    // subclasses can override to short-circuit authentication
    protected boolean isAuthenticationRequired(HandlerMethod handlerMethod) {
        return true;
    }

    protected abstract boolean authenticate(HttpServletRequest request, HttpServletResponse response, HandlerMethod handler);
}