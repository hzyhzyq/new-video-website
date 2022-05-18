package com.yunqi.video.api;

import com.yunqi.video.domain.bean.User;
import com.yunqi.video.domain.response.JsonResponse;
import com.yunqi.video.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService service;

    /**
     * user login
     * @param principal
     * @param password
     * @param rememberMe
     * @return
     */
    @PostMapping("/login")
    public JsonResponse<String> test(@RequestParam(value = "principal") String principal,
                                     @RequestParam(value = "password") String password,
                                     @RequestParam(value = "rememberMe") Boolean rememberMe){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(principal,password,rememberMe);
        try {
            subject.login(token);
        }
        catch (AuthenticationException e){
            subject.getSession().removeAttribute("currentUserId");
            JsonResponse<String> loginFail = new JsonResponse<>("500", "loginFail");
            loginFail.setData("error");
            return loginFail;
        }
        return new JsonResponse<>("success");
    }

    /**
     * user logout
     * @return
     */
    @GetMapping("/logout")
    public JsonResponse<String> logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.getSession().removeAttribute("currentUserId");
        subject.logout();
        return new JsonResponse<>("logout");
    }

    @GetMapping("/get_user_info")
    public JsonResponse<User> getUserInfo(){
        Subject subject = SecurityUtils.getSubject();
        String currentUserId = (String)subject.getSession().getAttribute("currentUserId");
        User userInfoByUserID = service.getUserInfoByUserID(currentUserId);
        return new JsonResponse<>(userInfoByUserID);
    }
}
