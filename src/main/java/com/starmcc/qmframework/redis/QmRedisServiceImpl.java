package com.starmcc.qmframework.redis;

import com.starmcc.qmframework.config.QmRedisConfiguration;
import com.starmcc.qmframework.exception.QmFrameworkException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Objects;
import java.util.Set;

/**
 * 使用qm缓存Redis服务实现类
 *
 * @author starmcc
 * @date 2021/11/19
 */
public class QmRedisServiceImpl implements QmRedisService {

    private static final Logger LOG = LoggerFactory.getLogger(QmRedisServiceImpl.class);

    private final JedisPool jedisPool;

    @Autowired
    public QmRedisServiceImpl(QmRedisConfiguration configuration) {
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
                null
        );
    }

    @Override
    public JedisPool getJedisPool() {
        return this.jedisPool;
    }

    @Override
    public Jedis getJedis() {
        return this.getJedis(0);
    }

    @Override
    public Jedis getJedis(int index) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
        } catch (Exception e) {
            e.printStackTrace();
        }
        jedis.select(index);
        return jedis;
    }

    @Override
    public Set<String> keys(String pattern) {
        for (int i = 0; i < 16; i++) {

        }
        return null;
    }

    @Override
    public Set<String> keys(String pattern, int index) {
        Jedis jedis = null;
        try {
            jedis = this.getJedis(index);
            return jedis.keys(pattern);
        } finally {
            jedis.close();
        }
    }


    /*########################  string(字符串)的操作  ####################*/
    @Override
    public String get(QmRedisKeyModel key) {
        Jedis jedis = null;
        try {
            jedis = this.getJedis(key.getIndex());
            return jedis.get(key.buildKey());
        } finally {
            jedis.close();
        }
    }

    @Override
    public void set(QmRedisKeyModel key, String value) {
        Jedis jedis = null;
        try {
            jedis = this.getJedis(key.getIndex());
            jedis.set(key.buildKey(), value);
        } finally {
            jedis.close();
        }
    }
}
