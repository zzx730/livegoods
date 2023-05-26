package com.livegoods.login.dao;

import com.livegoods.commons.pojo.LoginLog;

// 登录数据层接口
public interface LoginLogDao {
    // 保存日志
    void insertLoginLog(LoginLog loginLog);
}
