package com.livegoods.login.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.livegoods.commons.vo.LivegoodsResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MyAuthenticationService implements AuthenticationFailureHandler, AuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        System.out.println("登陆失败后续处理.......");
        LivegoodsResult error = LivegoodsResult.error();
        response.setContentType("application/json;charset=UTF-8");
        // 将LivegoodsResult对象转换成String
        response.getWriter().write(objectMapper.writeValueAsString(error));
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println("登陆成功后续处理.......");
        LivegoodsResult result = LivegoodsResult.ok();
        response.setContentType("application/json;charset=UTF-8");
        result.setMessage("登录成功");
        // 将LivegoodsResult对象转换成String
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
