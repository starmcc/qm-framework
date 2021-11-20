package com.starmcc.qmframework.redis;

import com.starmcc.qmframework.config.QmRedisConfiguration;
import com.starmcc.qmframework.exception.QmFrameworkException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * qm缓存,执行
 *
 * @author starmcc
 * @date 2021/11/20
 */
public class QmRedisTemplate {

    private static final Logger LOG = LoggerFactory.getLogger(QmRedisServiceImpl.class);

    private final JedisPool jedisPool;


    @Autowired
    public QmRedisTemplate(QmRedisConfiguration configuration) {
        if (Objects.isNull(configuration)) {
            throw new QmFrameworkException("Redis configuration is null!");
        }

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        //========= jedisPool的一些配置=============================
        //最大连接数
        poolConfig.setMaxTotal(configuration.getMaxTotal());
        //最多空闲数
        poolConfig.setMaxIdle(configuration.getMaxIdle());
        //当池中没有连接时，最多等待5秒
        poolConfig.setMaxWaitMillis(configuration.getMaxWaitMillis());
        this.jedisPool = new JedisPool(poolConfig,
                configuration.getHost(),
                configuration.getPort(),
                configuration.getTimeOut(),
                configuration.getPassword()
        );
    }


    public JedisPool getJedisPool() {
        return this.jedisPool;
    }

    public Jedis getJedis() {
        return this.getJedis(0);
    }

    public Jedis getJedis(final int index) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
        } catch (Exception e) {
            LOG.error("Redis error {}", e.getMessage(), e);
        }
        jedis.select(index);
        return jedis;
    }

    /**
     * 执行
     *
     * @param keyModel   key模型
     * @param function   函数
     * @param setExpTime 设置过期时间
     * @return {@link T}
     */
    protected Object execute(final QmRedisKeyModel keyModel, final ExecuteFunction fnc, boolean setExpTime) {
        Jedis jedis = null;
        try {
            jedis = this.getJedis(keyModel.getIndex());
            String key = keyModel.buildKey();
            Object obj = fnc.run(jedis, key);
            if (setExpTime) {
                jedis.expire(key, keyModel.getExpTime());
            }
            return obj;
        } finally {
            jedis.close();
        }
    }

    /**
     * 执行
     *
     * @param keyModel   key模型
     * @param consumer   函数
     * @param setExpTime 设置过期时间
     */
    protected void execute(final QmRedisKeyModel keyModel, final ExecuteConsumer consumer, boolean setExpTime) {
        Jedis jedis = null;
        try {
            jedis = this.getJedis(keyModel.getIndex());
            String key = keyModel.buildKey();
            consumer.run(jedis, key);
            if (setExpTime) {
                jedis.expire(key, keyModel.getExpTime());
            }
        } finally {
            jedis.close();
        }
    }

    @FunctionalInterface
    public interface Procedure {
        /**
         * 运行
         */
        void run();
    }

    @FunctionalInterface
    public interface ExecuteFunction {
        /**
         * 运行
         *
         * @param jedis jedis
         * @param key   关键
         * @return {@link Object}
         */
        Object run(final Jedis jedis, final String key);
    }

    @FunctionalInterface
    public interface ExecuteConsumer {

        /**
         * 运行
         *
         * @param jedis jedis
         * @param key   关键
         */
        void run(final Jedis jedis, final String key);
    }
}
