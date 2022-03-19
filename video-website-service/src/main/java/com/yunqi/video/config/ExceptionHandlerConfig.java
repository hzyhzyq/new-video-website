package com.yunqi.video.config;


import com.yunqi.video.domain.constant.ErrorInfo;
import com.yunqi.video.domain.response.JsonResponse;
import com.yunqi.video.domain.exception.ConditionException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Tony
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionHandlerConfig {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonResponse<String> commonExceptionHandler(HttpServletRequest request,Exception error){
        if(error instanceof ConditionException){
            ConditionException exception = (ConditionException) error;
            return new JsonResponse<>(exception.getCode(),exception.getMessage());
        }
        return new JsonResponse<>(ErrorInfo.COMMON_ERROR);
    }

}
