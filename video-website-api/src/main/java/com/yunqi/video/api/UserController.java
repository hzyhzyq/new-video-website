package com.yunqi.video.api;

import com.yunqi.video.domain.response.JsonResponse;
import com.yunqi.video.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/login")
    public JsonResponse<String> test(@RequestParam(value = "userName") String userName,
                                     @RequestParam(value = "password") String password,
                                     @RequestParam(value = "rememberMe") Boolean rememberMe){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName,password,rememberMe);
        try {
            subject.login(token);
        }
        catch (AuthenticationException e){
            return new JsonResponse<>("500","error");
        }
        return new JsonResponse<>("success");
    }
    @PostMapping("/test1")
    public JsonResponse<String> test1(){
        Subject subject = SecurityUtils.getSubject();
        return new JsonResponse<>("1234");
    }
    @PostMapping("/logout")
    public JsonResponse<String> logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new JsonResponse<>("logout");
    }


}
