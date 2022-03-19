package com.yunqi.video.domain.exception;

import com.yunqi.video.domain.constant.ErrorInfo;

/**
 * @author Tony
 */
public class ConditionException extends RuntimeException{
    public static final long serialVersionUID = 1L;
    private String code;

    public ConditionException(ErrorInfo errorInfo) {
        super(errorInfo.getErrorMessage());
        this.code = errorInfo.getErrorCode();
    }
    public ConditionException(String message) {
        super(message);
        this.code = ErrorInfo.COMMON_ERROR.getErrorCode();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
