package com.livegoods.buytime.message.config;

import com.livegoods.redis.config.RedisCacheConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class BuyactionMessageRedisConfiguration extends RedisCacheConfiguration {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return super.redisTemplate(redisConnectionFactory);
    }
}
