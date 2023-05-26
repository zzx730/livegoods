package com.livegoods.login.dao;


import com.livegoods.commons.pojo.ValidateCode;

// 验证码数据层接口
public interface ValidateCodeDao {
    // 保存验证码到redis
    void set(String key,Object value);
    // 根据手机号取出验证码 用于跟用户输入的验证码比对
    ValidateCode get(String key);
    // 删除验证码
    Boolean delete(String key);
}
