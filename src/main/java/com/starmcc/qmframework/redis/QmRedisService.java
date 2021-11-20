package com.starmcc.qmframework.redis;


import redis.clients.jedis.*;
import redis.clients.jedis.resps.KeyedListElement;

import java.util.List;
import java.util.Map;
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
    Jedis getJedis(final int index);

    /**
     * 获取所有key
     *
     * @param pattern pattern
     * @return {@link Set}<{@link String}>
     */
    Set<String> keys(final String pattern);


    /**
     * 获取所有key 根据库
     *
     * @param pattern pattern
     * @param index   库索引
     * @return {@link Set}<{@link String}>
     */
    Set<String> keys(final String pattern, final int index);


    /**
     * 删除一个或多个key
     *
     * @param keyModels key模型数组
     * @return {@link Long}
     */
    Long del(final QmRedisKeyModel... keyModels);

    /**
     * 判断某个key是否还存在
     *
     * @param keyModel key模型
     * @return {@link Boolean}
     */
    Boolean exists(final QmRedisKeyModel keyModel);

    /**
     * 设置过期时间
     *
     * @param key     key
     * @param index   库索引
     * @param seconds 秒
     * @return {@link Long}
     */
    Long expire(final String key, final int index, final long seconds);


    /**
     * 设置过期时间
     *
     * @param keyModel key模型
     * @return {@link Long}
     */
    Long expire(final QmRedisKeyModel keyModel);

    /**
     * 获取值
     *
     * @param keyModel key模型
     * @return {@link String}
     */
    String get(final QmRedisKeyModel keyModel);


    /**
     * 设置值
     *
     * @param keyModel key模型
     * @param value    价值
     * @return {@link String}
     */
    String set(final QmRedisKeyModel keyModel, final String value);

    /**
     * 查看某个key还有几秒过期，-1表示永不过期 ，-2表示已过期
     *
     * @param keyModel key模型
     * @return {@link Long}
     */
    Long timeToLive(final QmRedisKeyModel keyModel);

    /*########################  string(字符串)操作  ####################*/

    /**
     * 返回 key 中字符串值的子字符
     *
     * @param keyModel    key模型
     * @param startOffset 起始偏移量
     * @param endOffset   结束偏移量
     * @return {@link String}
     */
    String getrange(final QmRedisKeyModel keyModel, final long startOffset, final long endOffset);


    /**
     * 将给定 key 的值设为 value ，并返回 key 的旧值(old value)。
     *
     * @param keyModel key模型
     * @param value    价值
     * @return {@link String}
     */
    String getSet(final QmRedisKeyModel keyModel, final String value);

    /**
     * 锁
     *
     * @param keyModel  key模型
     * @param procedure 执行
     * @return boolean
     */
    void lock(final QmRedisKeyModel keyModel, final QmRedisTemplate.Procedure procedure);

    /**
     * 锁
     *
     * @param keyModel key模型
     * @return boolean
     */
    boolean lock(final QmRedisKeyModel keyModel);


    /**
     * 返回 key 所储存的字符串值的长度。
     *
     * @param keyModel key模型
     * @return {@link Long}
     */
    Long strlen(final QmRedisKeyModel keyModel);


    /**
     * 将 key 中储存的数字值增一。
     *
     * @param keyModel key模型
     */
    Long incr(final QmRedisKeyModel keyModel);


    /**
     * 将 key 所储存的值加上给定的增量值（increment）。
     *
     * @param keyModel  key模型
     * @param increment 增量
     * @return {@link Long}
     */
    Long incrBy(final QmRedisKeyModel keyModel, final long increment);


    /**
     * 将 key 所储存的值加上给定的浮点增量值（increment）。
     *
     * @param keyModel  key模型
     * @param increment 增量
     * @return {@link Double}
     */
    Double incrbyfloat(final QmRedisKeyModel keyModel, final double increment);


    /**
     * 将 key 中储存的数字值减一。
     *
     * @param keyModel key模型
     * @return {@link Long}
     */
    Long decr(final QmRedisKeyModel keyModel);


    /**
     * key 所储存的值减去给定的减量值（decrement）。
     *
     * @param keyModel  key模型
     * @param decrement 减量
     * @return {@link Long}
     */
    Long decrBy(final QmRedisKeyModel keyModel, final long decrement);


    /**
     * 如果 key 已经存在并且是一个字符串， APPEND 命令将指定的 value 追加到该 key 原来值（value）的末尾。
     *
     * @param keyModel key模型
     * @param value    价值
     * @return {@link Long}
     */
    Long append(final QmRedisKeyModel keyModel, final String value);

    /*########################  hash(哈希)操作  ####################*/


    /**
     * 删除一个或多个哈希表字段
     *
     * @param keyModel key模型
     * @param fields   字段
     * @return {@link Long}
     */
    Long hdel(final QmRedisKeyModel keyModel, final String... fields);


    /**
     * 查看哈希表 key 中，指定的字段是否存在。
     *
     * @param keyModel key模型
     * @param field    字段
     * @return {@link Boolean}
     */
    Boolean hexists(final QmRedisKeyModel keyModel, final String field);


    /**
     * 获取存储在哈希表中指定字段的值。
     *
     * @param keyModel key模型
     * @param field    字段
     * @return {@link String}
     */
    String hget(final QmRedisKeyModel keyModel, final String field);


    /**
     * 获取在哈希表中指定 key 的所有字段和值
     *
     * @param keyModel key模型
     * @return {@link Map}<{@link String}, {@link String}>
     */
    Map<String, String> hget(final QmRedisKeyModel keyModel);

    /**
     * 为哈希表 key 中的指定字段的整数值加上增量 increment 。
     *
     * @param keyModel  key模型
     * @param field     字段
     * @param increment 增量
     * @return {@link Long}
     */
    Long hincrBy(final QmRedisKeyModel keyModel, final String field, final long increment);


    /**
     * 为哈希表 key 中的指定字段的浮点数值加上增量 increment 。
     *
     * @param keyModel  key模型
     * @param field     字段
     * @param increment 增量
     * @return {@link Double}
     */
    Double hincrByFloat(final QmRedisKeyModel keyModel, final String field, final double increment);


    /**
     * 获取所有哈希表中的字段
     *
     * @param keyModel key模型
     * @return {@link Set}<{@link String}>
     */
    Set<String> hkeys(final QmRedisKeyModel keyModel);


    /**
     * 获取哈希表中字段的数量
     *
     * @param keyModel key模型
     * @return {@link Long}
     */
    Long hlen(final QmRedisKeyModel keyModel);


    /**
     * 获取所有给定字段的值
     *
     * @param keyModel key模型
     * @return {@link List}<{@link String}>
     */
    List<String> hmget(final QmRedisKeyModel keyModel);


    /**
     * 同时将多个 field-value (域-值)对设置到哈希表 key 中。
     *
     * @param keyModel key模型
     * @param hash     哈希
     * @return {@link String}
     */
    String hmset(final QmRedisKeyModel keyModel, final Map<String, String> hash);


    /**
     * 将哈希表 key 中的字段 field 的值设为 value 。
     *
     * @param keyModel key模型
     * @param field    字段
     * @param value    价值
     * @return {@link String}
     */
    Long hset(final QmRedisKeyModel keyModel, final String field, final String value);


    /**
     * 只有在字段 field 不存在时，设置哈希表字段的值。
     *
     * @param keyModel key模型
     * @param field    字段
     * @param value    价值
     * @return {@link String}
     */
    Long hsetnx(final QmRedisKeyModel keyModel, final String field, final String value);


    /**
     * 获取哈希表中所有值。
     *
     * @param keyModel key模型
     * @return {@link List}<{@link String}>
     */
    List<String> hvals(final QmRedisKeyModel keyModel);

    /**
     * 迭代哈希表中的键值对。
     *
     * @param keyModel key模型
     * @param cursor   光标
     * @return {@link ScanResult}<{@link Entry}<{@link String}, {@link String}>>
     */
    ScanResult<Map.Entry<String, String>> hvals(final QmRedisKeyModel keyModel, final String cursor);


    /*########################  List(列表)操作  ####################*/


    /**
     * 移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     *
     * @param keyModels key模型
     * @return {@link List}<{@link String}>
     */
    List<String> blpop(final QmRedisKeyModel... keyModels);


    /**
     * 移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     *
     * @param keyModel key模型
     * @param timeout  超时
     * @return {@link KeyedListElement}
     */
    KeyedListElement blpop(final QmRedisKeyModel keyModel, final long timeout);


    /**
     * 移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     *
     * @param keyModels key模型
     * @return {@link List}<{@link String}>
     */
    List<String> brpop(final QmRedisKeyModel... keyModels);


    /**
     * 移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     *
     * @param keyModel key模型
     * @param timeout  超时
     * @return {@link KeyedListElement}
     */
    KeyedListElement brpop(final QmRedisKeyModel keyModel, final long timeout);


    /**
     * 通过索引获取列表中的元素
     *
     * @param keyModel key模型
     * @param index    索引
     * @return {@link String}
     */
    String lindex(final QmRedisKeyModel keyModel, final long index);


    /**
     * 在列表的元素前或者后插入元素
     *
     * @param keyModel key模型
     * @param where    where
     * @param pivot
     * @param value
     * @return {@link Long}
     */
    Long linsert(final QmRedisKeyModel keyModel, final ListPosition where, final String pivot, final String value);


    /**
     * 获取列表长度
     *
     * @param keyModel key模型
     * @return {@link Long}
     */
    Long llen(final QmRedisKeyModel keyModel);


    /**
     * 移出并获取列表的第一个元素
     *
     * @param keyModel key模型
     * @return {@link Long}
     */
    Long lpop(final QmRedisKeyModel keyModel);


    /**
     * 将一个或多个值插入到列表头部
     *
     * @param keyModel key模型
     * @param values   值
     * @return {@link Long}
     */
    Long lpush(final QmRedisKeyModel keyModel, final String... values);


    /**
     * 将一个值插入到已存在的列表头部
     *
     * @param keyModel key模型
     * @param values   值
     * @return {@link Long}
     */
    Long lpushx(final QmRedisKeyModel keyModel, final String... values);


    /**
     * 获取列表指定范围内的元素
     *
     * @param keyModel key模型
     * @param start    开始
     * @param stop     停止
     * @return {@link List}<{@link String}>
     */
    List<String> lrange(final QmRedisKeyModel keyModel, final long start, final long stop);


    /**
     * 移除列表元素
     *
     * @param keyModel key模型
     * @param count    数
     * @param value    值
     * @return {@link Long}
     */
    Long lrem(final QmRedisKeyModel keyModel, final long count, final String value);


    /**
     * 通过索引设置列表元素的值
     *
     * @param keyModel key模型
     * @param index    搜因
     * @param value    值
     * @return {@link String}
     */
    String lset(final QmRedisKeyModel keyModel, final long index, final String value);


    /**
     * 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
     *
     * @param keyModel key模型
     * @param start    开始
     * @param stop     停止
     * @return {@link String}
     */
    String ltrim(final QmRedisKeyModel keyModel, final long start, final long stop);


    /**
     * 移除列表的最后一个元素，并将该元素添加到另一个列表并返回
     *
     * @param sourceKeyModel 源key模型
     * @param targetKeyModel 目标key模型
     * @return {@link String}
     */
    String rpoplpush(final QmRedisKeyModel sourceKeyModel, final QmRedisKeyModel targetKeyModel);


    /**
     * 在列表中添加一个或多个值
     *
     * @param keyModel key模型
     * @param values   值
     * @return {@link Long}
     */
    Long rpush(final QmRedisKeyModel keyModel, final String... values);

    /**
     * 为已存在的列表添加值
     *
     * @param keyModel key模型
     * @param values   值
     * @return {@link Long}
     */
    Long rpushx(final QmRedisKeyModel keyModel, final String... values);

    /*########################  Set(集合)操作  ####################*/

    /**
     * 向集合添加一个或多个成员
     *
     * @param keyModel key模型
     * @param values   值
     * @return {@link Long}
     */
    Long sadd(final QmRedisKeyModel keyModel, final String... values);


    /**
     * 获取集合的成员数
     *
     * @param keyModel key模型
     * @return {@link Long}
     */
    Long scard(final QmRedisKeyModel keyModel);


    /**
     * 返回第一个集合与其他集合之间的差异。
     *
     * @param keyModels 关键模型
     * @return {@link Set}<{@link String}>
     */
    Set<String> sdiff(final QmRedisKeyModel... keyModels);


    /**
     * 返回给定所有集合的差集并存储在 destination 中
     *
     * @param destinationKeyModel key模型
     * @param keyModels           key模型
     * @return {@link Long}
     */
    Long sdiffstore(final QmRedisKeyModel destinationKeyModel, final QmRedisKeyModel... keyModels);


    /**
     * 判断 member 元素是否是集合 key 的成员
     *
     * @param keyModel key模型
     * @param member   成员
     * @return {@link Boolean}
     */
    Boolean sismember(final QmRedisKeyModel keyModel, final String member);


    /**
     * 返回集合中的所有成员
     *
     * @param keyModel key模型
     * @return {@link Set}<{@link String}>
     */
    Set<String> smembers(final QmRedisKeyModel keyModel);

    /**
     * 将 member 元素从 source 集合移动到 destination 集合
     *
     * @param srcKeyModel src关键模型
     * @param dstKeyModel dst关键模型
     * @param member      成员
     * @return {@link Long}
     */
    Long smove(final QmRedisKeyModel srcKeyModel, final QmRedisKeyModel dstKeyModel, final String member);


    /**
     * 移除并返回集合中的一个随机元素
     *
     * @param keyModel key模型
     * @return {@link String}
     */
    String spop(final QmRedisKeyModel keyModel);


    /**
     * 返回集合中一个或多个随机数
     *
     * @param keyModel key模型
     * @return {@link List}<{@link String}>
     */
    List<String> srandmember(final QmRedisKeyModel keyModel);

    /**
     * 返回集合中一个或多个随机数
     *
     * @param keyModel key模型
     * @param count    数量
     * @return {@link List}<{@link String}>
     */
    List<String> srandmember(final QmRedisKeyModel keyModel, final int count);


    /**
     * 移除集合中一个或多个成员
     *
     * @param keyModel key模型
     * @param member   成员
     * @return {@link Long}
     */
    Long srem(final QmRedisKeyModel keyModel, final String... member);


    /**
     * 返回所有给定集合的并集
     *
     * @param keyModels key模型
     * @return {@link Set}<{@link String}>
     */
    Set<String> sunion(final QmRedisKeyModel... keyModels);


    /**
     * 所有给定集合的并集存储在 destination 集合中
     *
     * @param keyModel  key模型
     * @param keyModels key模型
     * @return {@link Long}
     */
    Long sunionstore(final QmRedisKeyModel keyModel, final QmRedisKeyModel... keyModels);


    /**
     * 迭代集合中的元素
     *
     * @param keyModel key模型
     * @param cursor   光标
     * @return {@link ScanResult}<{@link String}>
     */
    ScanResult<String> sscan(final QmRedisKeyModel keyModel, final String cursor);

    /*########################  sorted set(有序集合)操作  ####################*/


    /**
     * 向有序集合添加一个成员，或者更新已存在成员的分数
     *
     * @param keyModel key模型
     * @param score    分数
     * @param member   成员
     * @return {@link Long}
     */
    Long zadd(final QmRedisKeyModel keyModel, final double score, final String member);


    /**
     * 向有序集合添加一个或多个成员，或者更新已存在成员的分数
     *
     * @param keyModel     key模型
     * @param scoreMembers 分成员
     * @return {@link Long}
     */
    Long zadd(final QmRedisKeyModel keyModel, final Map<String, Double> scoreMembers);


    /**
     * 获取有序集合的成员数
     *
     * @param keyModel key模型
     * @return {@link Long}
     */
    Long zcard(final QmRedisKeyModel keyModel);


    /**
     * 计算在有序集合中指定区间分数的成员数
     *
     * @param keyModel key模型
     * @param min      最小值
     * @param max      最大值
     * @return {@link Long}
     */
    Long zcount(final QmRedisKeyModel keyModel, final double min, final double max);


    /**
     * 有序集合中对指定成员的分数加上增量 increment
     *
     * @param keyModel  key模型
     * @param increment 增量
     * @param member    成员
     * @return {@link Double}
     */
    Double zincrby(final QmRedisKeyModel keyModel, final double increment, final String member);


    /**
     * 计算给定的一个或多个有序集的交集并将结果集存储在新的有序集合 destination 中
     *
     * @param keyModel key模型
     * @param sets     集
     * @return {@link Long}
     */
    Long zinterstore(final QmRedisKeyModel keyModel, final String... sets);


    /**
     * 在有序集合中计算指定字典区间内成员数量
     *
     * @param keyModel key模型
     * @param min      最小值
     * @param max      最大值
     * @return {@link Long}
     */
    Long zlexcount(final QmRedisKeyModel keyModel, final String min, final String max);


    /**
     * 通过索引区间返回有序集合指定区间内的成员
     *
     * @param keyModel key模型
     * @param min      最小值
     * @param max      最大值
     * @return {@link Set}<{@link String}>
     */
    Set<String> zrange(final QmRedisKeyModel keyModel, final long min, final long max);


    /**
     * 通过字典区间返回有序集合的成员
     *
     * @param keyModel key模型
     * @param min      最小值
     * @param max      最大值
     * @param offset
     * @param count
     * @return {@link Set}<{@link String}>
     */
    Set<String> zrangeByLex(final QmRedisKeyModel keyModel, final String min, final String max, final int offset, final int count);


    /**
     * 通过字典区间返回有序集合的成员
     *
     * @param keyModel key模型
     * @param min      最小值
     * @param max      最大值
     * @return {@link Set}<{@link String}>
     */
    Set<String> zrangeByLex(final QmRedisKeyModel keyModel, final String min, final String max);

    /**
     * 通过分数返回有序集合指定区间内的成员
     *
     * @param keyModel key模型
     * @param min      最小值
     * @param max      最大值
     * @return {@link Set}<{@link String}>
     */
    Set<String> zrangeByScore(final QmRedisKeyModel keyModel, final double min, final double max);


    /**
     * 返回有序集合中指定成员的索引
     *
     * @param keyModel key模型
     * @param member   成员
     * @return {@link Long}
     */
    Long zrank(final QmRedisKeyModel keyModel, final String member);


    /**
     * 移除有序集合中的一个或多个成员
     *
     * @param keyModel key模型
     * @param members  成员
     * @return {@link Long}
     */
    Long zrem(final QmRedisKeyModel keyModel, final String... members);


    /**
     * 移除有序集合中给定的字典区间的所有成员
     *
     * @param keyModel key模型
     * @param min      最小值
     * @param max      最大值
     * @return {@link Long}
     */
    Long zremrangeByLex(final QmRedisKeyModel keyModel, final String min, final String max);


    /**
     * 移除有序集合中给定的排名区间的所有成员
     *
     * @param keyModel key模型
     * @param start    开始
     * @param stop     停止
     * @return {@link Long}
     */
    Long zremrangeByRank(final QmRedisKeyModel keyModel, final long start, final long stop);


    /**
     * 移除有序集合中给定的分数区间的所有成员
     *
     * @param keyModel key模型
     * @param min      最小值
     * @param max      最大值
     * @return {@link Long}
     */
    Long zremrangeByScore(final QmRedisKeyModel keyModel, final double min, final double max);


    /**
     * 返回有序集中指定区间内的成员，通过索引，分数从高到低
     *
     * @param keyModel key模型
     * @param start    开始
     * @param stop     停止
     * @return {@link Set}<{@link String}>
     */
    Set<String> zrevrange(final QmRedisKeyModel keyModel, final long start, final long stop);


    /**
     * 返回有序集中指定分数区间内的成员，分数从高到低排序
     *
     * @param keyModel key模型
     * @param max      最大值
     * @param min      最小值
     * @return {@link Set}<{@link String}>
     */
    Set<String> zrevrangeByScore(final QmRedisKeyModel keyModel, final double max, final double min);


    /**
     * 返回有序集合中指定成员的排名，有序集成员按分数值递减(从大到小)排序
     *
     * @param keyModel key模型
     * @param member   成员
     * @return {@link Long}
     */
    Long zrevrank(final QmRedisKeyModel keyModel, final String member);


    /**
     * 返回有序集中，成员的分数值
     *
     * @param keyModel key模型
     * @param member   成员
     * @return {@link Double}
     */
    Double zscore(final QmRedisKeyModel keyModel, final String member);

    /**
     * 计算给定的一个或多个有序集的并集，并存储在新的 key 中
     *
     * @param keyModel key模型
     * @param sets     集
     * @return {@link Long}
     */
    Long zunionstore(final QmRedisKeyModel keyModel, final String... sets);

    /**
     * 迭代有序集合中的元素（包括元素成员和元素分值）
     *
     * @param keyModel key模型
     * @param cursor   光标
     * @return {@link ScanResult}<{@link Tuple}>
     */
    ScanResult<Tuple> zscan(final QmRedisKeyModel keyModel, final String cursor);
}
