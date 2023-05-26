package com.livegoods.login.dao.impl;

import com.livegoods.commons.pojo.ValidateCode;
import com.livegoods.login.dao.ValidateCodeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

// 验证码数据层实现类
@Repository
public class ValidateCodeDaoImpl implements ValidateCodeDao {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 保存验证码到redis 2分钟
     * @param key
     * @param value
     */
    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key,value, Duration.ofMinutes(2L));
    }

    /**
     * 在redis中根据key获取value
     * @param key
     * @return
     */
    @Override
    public ValidateCode get(String key) {
        ValidateCode validateCode = (ValidateCode) redisTemplate.opsForValue().get(key);
        return validateCode;
    }

    /**
     *  在redis中根据key删除value
     * @param key
     * @return
     */
    @Override
    public Boolean delete(String key) {
        Boolean flag = redisTemplate.delete(key);
        return flag;
    }
}
