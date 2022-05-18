package com.yunqi.video.domain.response;

import com.yunqi.video.domain.constant.ErrorInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tony
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonResponse<T> {
    private String code;
    private String message;
    private T data;

    public JsonResponse(T data) {
        this.data = data;
        this.code= ErrorInfo.SUCCESS.getErrorCode();
        this.message=ErrorInfo.SUCCESS.getErrorMessage();
    }

    public JsonResponse(ErrorInfo errorInfo) {
        this.code = errorInfo.getErrorCode();
        this.message = errorInfo.getErrorMessage();
        this.data = (T) "error";
    }

    public JsonResponse(String code,String message) {
        this.code = code;
        this.message = message;
    }
}
