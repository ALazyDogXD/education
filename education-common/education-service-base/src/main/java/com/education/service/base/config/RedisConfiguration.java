package com.education.service.base.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * @author ALazyDogXD
 * @date 2022/2/21 23:08
 * @description Redis 配置文件
 */

@Configuration
@ConditionalOnClass(RedisAutoConfiguration.class)
public class RedisConfiguration {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(lettuceConnectionFactory);
        StringRedisSerializer serializer = new StringRedisSerializer();
        template.setKeySerializer(serializer);
        template.setHashKeySerializer(serializer);
        Jackson2JsonRedisSerializer<Serializable> jsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Serializable.class);
        template.setValueSerializer(jsonRedisSerializer);
        template.setHashValueSerializer(jsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

}
