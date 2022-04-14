package com.yunqi.video.config.shiroConfig;

import com.alibaba.fastjson.JSONObject;
import com.yunqi.video.domain.constant.ErrorInfo;
import com.yunqi.video.domain.response.JsonResponse;
import org.apache.shiro.web.filter.authc.UserFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class MyFilter extends UserFilter {

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse res = (HttpServletResponse) response;
        res.setStatus(HttpServletResponse.SC_OK);
        res.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = res.getWriter();
        JsonResponse<String> responseEntity = new JsonResponse(ErrorInfo.TOKEN_EXPIRED);
        writer.append(JSONObject.toJSONString(responseEntity));
        writer.close();
        return false;
    }
}
