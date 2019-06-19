package com.yed.glhf.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yed.glhf.common.json.util.ObjectMapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

    @Autowired
    private Environment environment;


    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        return getConnectionFactory("spring.redis.glhf");
    }

    private JedisConnectionFactory getConnectionFactory(String prefix) {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setHostName(environment.getProperty(prefix + ".host"));
        redisStandaloneConfiguration.setPort(environment.getProperty(prefix + ".port", Integer.class));
        redisStandaloneConfiguration.setPassword(RedisPassword.of(environment.getProperty(prefix + ".password")));
        redisStandaloneConfiguration.setDatabase(environment.getProperty(prefix + ".database", Integer.class));
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(environment.getProperty(prefix + ".max-idle", Integer.class));
        jedisPoolConfig.setMaxTotal(environment.getProperty(prefix + ".max-total", Integer.class));
        jedisPoolConfig.setMaxWaitMillis(environment.getProperty(prefix + ".max-wait-millis", Integer.class));
        jedisPoolConfig.setBlockWhenExhausted(environment.getProperty(prefix + ".block-when-exhausted", Boolean.class));
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jedisPoolingClientConfigurationBuilder = JedisClientConfiguration.builder().usePooling().poolConfig(jedisPoolConfig);
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisStandaloneConfiguration, jedisPoolingClientConfigurationBuilder.build());
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }

    @Bean
    RedisTemplate<Object, Object> redisTemplate() {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = ObjectMapperUtils.getObjectMapper();
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        return redisTemplate;
    }

    @Override
    @Bean
    public CacheManager cacheManager() {
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager
                .RedisCacheManagerBuilder
                .fromConnectionFactory(redisConnectionFactory());
        RedisCacheManager cacheManager = builder.build();
        return cacheManager;
    }
}
