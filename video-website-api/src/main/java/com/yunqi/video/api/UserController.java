package com.yunqi.video.api;

import com.yunqi.video.config.shiroConfig.JwtToken;
import com.yunqi.video.domain.response.JsonResponse;
import com.yunqi.video.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/test")
    public JsonResponse<String> test(){

        Subject subject = SecurityUtils.getSubject();
        subject.login(new JwtToken("123"));
        return new JsonResponse<>("123");
    }
    @PostMapping("/test1")
    public JsonResponse<String> test1(){
        service.testt();
        return new JsonResponse<>("1234");
    }


}
