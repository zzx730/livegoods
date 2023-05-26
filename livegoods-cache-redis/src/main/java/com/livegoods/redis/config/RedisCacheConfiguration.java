package com.livegoods.redis.config;

import org.springframework.cache.CacheManager;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * 定义一个父类，用于提供模板配置
 */
public abstract class RedisCacheConfiguration {
    // protected： 同一个类中 同一个包中 不同包的子类 都可以访问，但是不同包的无关类不能访问
    protected RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;
    }

    protected CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){
        org.springframework.data.redis.cache.RedisCacheConfiguration configuration = org.springframework.data.redis.cache.RedisCacheConfiguration.defaultCacheConfig();
        // 默认超时时间
        configuration = configuration.entryTtl(Duration.ofMinutes(30L))
        // 不缓存空数据
        .disableCachingNullValues()
        // key的序列化器
        .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
        // value的序列化器
        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

        return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory)).cacheDefaults(configuration).build();
    }

}
