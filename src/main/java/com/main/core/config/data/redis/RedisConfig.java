package com.main.core.config.data.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by Administrator on 2017/6/3.
 */
@ComponentScan
public class RedisConfig {
    /**
     * master:主节点名称
     */
    @Bean
    public RedisConnectionFactory jedisConnectionFactory() {
        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration() .master("master")
                .sentinel("192.168.1.251", 26379) .sentinel("192.168.1.251", 26380);
        return new JedisConnectionFactory(sentinelConfig);
    }
    @Bean
    public RedisTemplate redisTemplate(){
        RedisTemplate redisTemplate=new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }

}