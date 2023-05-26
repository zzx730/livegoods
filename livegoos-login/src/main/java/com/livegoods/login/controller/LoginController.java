package com.livegoods.login.controller;

import com.livegoods.commons.vo.LivegoodsResult;
import com.livegoods.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    /**
     * 发送验证码到手机号
     * @param phone
     * @return
     */
    @PostMapping("/sendyzm")
    public LivegoodsResult sendyzm(String phone) {
        return loginService.sendyzm(phone);

    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public LivegoodsResult login(String username,String password) {
        return loginService.login(username,password);
    }

    @GetMapping("/getLoginUser")
    public UserDetails getCurrentUser(@AuthenticationPrincipal UserDetails userDetails){
        return userDetails;
    }
}
