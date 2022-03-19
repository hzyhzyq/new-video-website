package com.yunqi.video.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class RequestAttributesUtil {
    /**
     * 获取当前Request中包含的token
     * @return
     */
    public static Integer getCurrentUserId(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String token = attributes.getRequest().getHeader("token");
        return TokenUtil.checkToken(token);
    }
}
