package com.livegoods.login.dao.impl;

import com.livegoods.commons.pojo.LoginLog;
import com.livegoods.login.dao.LoginLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
// 登录数据层实现类
@Repository
public class LoginLogDaoImpl implements LoginLogDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存登录日志
     * @param loginLog
     */
    @Override
    public void insertLoginLog(LoginLog loginLog) {
        mongoTemplate.save(loginLog);
    }
}
