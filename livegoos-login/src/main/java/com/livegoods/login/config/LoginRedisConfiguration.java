package com.livegoods.login.config;

import com.livegoods.redis.config.RedisCacheConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Login 配置Redis相关内容
 */
@Configuration
public class LoginRedisConfiguration extends RedisCacheConfiguration {
    /**
     * 创建缓存管理器
     * @param redisConnectionFactory Redis连接工厂
     * @return
     */
    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        return super.redisTemplate(redisConnectionFactory);
    }
}
