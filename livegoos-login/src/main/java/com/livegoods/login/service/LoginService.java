package com.livegoods.login.service;

import com.livegoods.commons.vo.LivegoodsResult;
// 用户登录服务接口
public interface LoginService {
    // 发送验证码
    LivegoodsResult sendyzm(String phone);
    // 登录
    LivegoodsResult login(String username,String validateCode);
}
