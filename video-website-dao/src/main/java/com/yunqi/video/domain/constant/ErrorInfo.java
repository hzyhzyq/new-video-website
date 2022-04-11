package com.yunqi.video.domain.constant;

/**
 * @author Tony
 *
 */

public enum ErrorInfo {
    /**
     * COMMON_ERROR: 服务器异常
     */
    SUCCESS("成功","200"),
    COMMON_ERROR("服务器异常","500"),
    LOGGIN_ERROR("账号或密码错误","501"),
    TOKEN_EXPIRED("未登录","502");


    private final String errorMessage;
    private final String errorCode;

    private ErrorInfo(String errorMessage, String errorCode){
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
