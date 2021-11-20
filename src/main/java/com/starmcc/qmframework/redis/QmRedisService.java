package com.starmcc.qmframework.redis;


import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * 使用qm缓存Redis服务接口
 *
 * @author starmcc
 * @date 2021/11/19
 */
public interface QmRedisService {

    /**
     * 获取JedisPool
     *
     * @return {@link JedisPool}
     */
    JedisPool getJedisPool();

    /**
     * 获取jedis
     *
     * @param index 库索引
     * @return {@link Jedis}
     */
    Jedis getJedis();

    /**
     * 获取jedis，并选择redis库。jedis默认是0号库，可传入0-14之间的数选择库存放数据
     *
     * @param index 库索引
     * @return {@link Jedis}
     */
    Jedis getJedis(int index);

    /**
     * 获取所有键
     *
     * @param pattern 模式
     * @return {@link Set}<{@link String}>
     */
    Set<String> keys(String pattern);

    /**
     * 获取所有键 根据库
     *
     * @param pattern 模式
     * @return {@link Set}<{@link String}>
     */
    Set<String> keys(String pattern, int index);


    /**
     * 获取值
     *
     * @param key 关键
     * @return {@link String}
     */
    String get(QmRedisKeyModel key);

    /**
     * 设置值
     *
     * @param key   关键
     * @param value 价值
     */
    void set(QmRedisKeyModel key, String value);

}
