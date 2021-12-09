package com.starmcc.qmframework.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.*;
import redis.clients.jedis.resps.KeyedListElement;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 使用qm缓存Redis服务实现类
 *
 * @author starmcc
 * @date 2021/11/19
 */
public class QmRedisServiceImpl implements QmRedisService {

    private static final Logger LOG = LoggerFactory.getLogger(QmRedisServiceImpl.class);

    @Autowired
    private QmRedisTemplate template;


    @Override
    public JedisPool getJedisPool() {
        return template.getJedisPool();
    }

    @Override
    public Jedis getJedis() {
        return template.getJedis();
    }

    @Override
    public Jedis getJedis(final int index) {
        return template.getJedis(index);
    }

    /*########################  基础操作  ####################*/

    @Override
    public Set<String> keys(final String pattern) {
        return this.keys(pattern, 0);
    }

    @Override
    public Set<String> keys(final String pattern, final int index) {
        QmRedisKeyModel keyModel = QmRedisKeyModel.builder().index(index).build();
        return (Set<String>) template.execute(keyModel, (jedis, key) -> {
            return jedis.keys(pattern);
        }, false);
    }

    @Override
    public Long del(final QmRedisKeyModel... keyModels) {
        return Arrays.stream(keyModels).collect(Collectors.summingLong(keyModel -> {
            return (Long) template.execute(keyModel, (jedis, key) -> {
                return jedis.del(key);
            }, false);
        }));
    }

    @Override
    public Boolean exists(final QmRedisKeyModel keyModel) {
        return (Boolean) template.execute(keyModel, (jedis, key) -> {
            return jedis.exists(key);
        }, false);
    }

    @Override
    public Long expire(final String key, final int index, final long seconds) {
        QmRedisKeyModel keyModel = QmRedisKeyModel.builder().keyPrefix(key).index(index).expTime(seconds).build();
        return (Long) template.execute(keyModel, (jedis, k) -> {
            return jedis.expire(k, seconds);
        }, false);
    }

    @Override
    public Long expire(final QmRedisKeyModel keyModel) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.expire(key, keyModel.getExpTime());
        }, false);
    }

    @Override
    public Long timeToLive(final QmRedisKeyModel keyModel) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.ttl(key);
        }, false);
    }


    /*########################  string(字符串)操作  ####################*/

    @Override
    public String get(final QmRedisKeyModel keyModel) {
        return (String) template.execute(keyModel, (jedis, key) -> {
            return jedis.get(key);
        }, false);
    }

    @Override
    public String set(final QmRedisKeyModel keyModel, final String value) {
        return (String) template.execute(keyModel, (jedis, key) -> {
            return jedis.setex(key, keyModel.getExpTime(), value);
        }, false);
    }


    @Override
    public String getrange(final QmRedisKeyModel keyModel, final long startOffset, final long endOffset) {
        return (String) template.execute(keyModel, (jedis, key) -> {
            return jedis.getrange(key, startOffset, endOffset);
        }, false);
    }

    @Override
    public String getSet(final QmRedisKeyModel keyModel, final String value) {
        return (String) template.execute(keyModel, (jedis, key) -> {
            return jedis.getSet(key, value);
        }, true);
    }

    @Override
    public void lock(final QmRedisKeyModel keyModel, final QmRedisTemplate.Procedure procedure) {
        template.execute(keyModel, (jedis, key) -> {
            Long lock = jedis.setnx(keyModel.buildKey(), "lock");
            boolean is = Objects.nonNull(lock) && lock.compareTo(1L) == 0;
            if (is) {
                procedure.run();
                // 执行完毕后，锁释放
                this.del(keyModel);
            }
        }, true);
    }


    @Override
    public boolean lock(final QmRedisKeyModel keyModel) {
        return (boolean) template.execute(keyModel, (jedis, key) -> {
            Long lock = jedis.setnx(key, "lock");
            return Objects.nonNull(lock) && lock.compareTo(1L) == 1;
        }, true);
    }

    @Override
    public Long strlen(final QmRedisKeyModel keyModel) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.strlen(key);
        }, false);
    }

    @Override
    public Long incr(final QmRedisKeyModel keyModel) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.incr(key);
        }, true);
    }

    @Override
    public Long incrBy(final QmRedisKeyModel keyModel, final long increment) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.incrBy(key, increment);
        }, true);
    }

    @Override
    public Double incrbyfloat(final QmRedisKeyModel keyModel, final double increment) {
        return (Double) template.execute(keyModel, (jedis, key) -> {
            return jedis.incrByFloat(key, increment);
        }, true);
    }

    @Override
    public Long decr(final QmRedisKeyModel keyModel) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.decr(key);
        }, true);
    }

    @Override
    public Long decrBy(final QmRedisKeyModel keyModel, final long decrement) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.decrBy(key, decrement);
        }, true);
    }

    @Override
    public Long append(final QmRedisKeyModel keyModel, final String value) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.append(key, value);
        }, true);
    }

    /*########################  hash(哈希)操作  ####################*/

    @Override
    public Long hdel(final QmRedisKeyModel keyModel, final String... fields) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.hdel(key, fields);
        }, true);
    }

    @Override
    public Boolean hexists(final QmRedisKeyModel keyModel, final String field) {
        try {
            return (Boolean) template.execute(keyModel, (jedis, key) -> {
                return jedis.hexists(key, field);
            }, false);
        } catch (Exception e) {
            LOG.error("Redis Error {}", e.getMessage(), e);
            return true;
        }
    }


    @Override
    public String hget(final QmRedisKeyModel keyModel, final String field) {
        return (String) template.execute(keyModel, (jedis, key) -> {
            return jedis.hget(key, field);
        }, false);
    }


    @Override
    public Map<String, String> hgetAll(final QmRedisKeyModel keyModel) {
        return (Map<String, String>) template.execute(keyModel, (jedis, key) -> {
            return jedis.hgetAll(key);
        }, false);
    }


    @Override
    public Long hincrBy(final QmRedisKeyModel keyModel, final String field, final long increment) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.hincrBy(key, field, increment);
        }, true);
    }


    @Override
    public Double hincrByFloat(final QmRedisKeyModel keyModel, final String field, final double increment) {
        return (Double) template.execute(keyModel, (jedis, key) -> {
            return jedis.hincrByFloat(key, field, increment);
        }, true);
    }


    @Override
    public Set<String> hkeys(final QmRedisKeyModel keyModel) {
        return (Set<String>) template.execute(keyModel, (jedis, key) -> {
            return jedis.hkeys(key);
        }, false);
    }

    @Override
    public Long hlen(final QmRedisKeyModel keyModel) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.hlen(key);
        }, false);
    }

    @Override
    public List<String> hmget(final QmRedisKeyModel keyModel) {
        return (List<String>) template.execute(keyModel, (jedis, key) -> {
            return jedis.hmget(key);
        }, false);
    }

    @Override
    public String hmset(final QmRedisKeyModel keyModel, final Map<String, String> hash) {
        return (String) template.execute(keyModel, (jedis, key) -> {
            return jedis.hmset(key, hash);
        }, true);
    }


    @Override
    public Long hset(final QmRedisKeyModel keyModel, final String field, final String value) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.hset(key, field, value);
        }, true);
    }


    @Override
    public Long hsetnx(final QmRedisKeyModel keyModel, final String field, final String value) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.hsetnx(key, field, value);
        }, true);
    }


    @Override
    public List<String> hvals(final QmRedisKeyModel keyModel) {
        return (List<String>) template.execute(keyModel, (jedis, key) -> {
            return jedis.hvals(key);
        }, false);
    }


    @Override
    public ScanResult<Map.Entry<String, String>> hvals(final QmRedisKeyModel keyModel, final String cursor) {
        return (ScanResult<Map.Entry<String, String>>) template.execute(keyModel, (jedis, key) -> {
            return jedis.hscan(key, cursor);
        }, false);
    }


    /*########################  List(列表)操作  ####################*/


    @Override
    public List<String> blpop(final QmRedisKeyModel... keyModels) {
        List<String> list = new ArrayList<>();
        for (QmRedisKeyModel keyModel : keyModels) {
            List<String> tempList = (List<String>) template.execute(keyModel, (jedis, key) -> {
                return jedis.blpop(key);
            }, false);
            list.addAll(tempList);
        }
        return list;
    }

    @Override
    public KeyedListElement blpop(final QmRedisKeyModel keyModel, final long timeout) {
        return (KeyedListElement) template.execute(keyModel, (jedis, key) -> {
            return jedis.blpop(timeout, key);
        }, false);
    }


    @Override
    public List<String> brpop(final QmRedisKeyModel... keyModels) {
        List<String> list = new ArrayList<>();
        for (QmRedisKeyModel keyModel : keyModels) {
            List<String> tempList = (List<String>) template.execute(keyModel, (jedis, key) -> {
                return jedis.brpop(key);
            }, false);
            list.addAll(tempList);
        }
        return list;
    }

    @Override
    public KeyedListElement brpop(final QmRedisKeyModel keyModel, final long timeout) {
        return (KeyedListElement) template.execute(keyModel, (jedis, key) -> {
            return jedis.brpop(timeout, key);
        }, false);
    }

    @Override
    public String lindex(final QmRedisKeyModel keyModel, final long index) {
        return (String) template.execute(keyModel, (jedis, key) -> {
            return jedis.lindex(key, index);
        }, false);
    }

    @Override
    public Long linsert(final QmRedisKeyModel keyModel, final ListPosition where, final String pivot, final String value) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.linsert(key, where, pivot, value);
        }, true);
    }

    @Override
    public Long llen(final QmRedisKeyModel keyModel) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.llen(key);
        }, false);
    }


    @Override
    public Long lpop(final QmRedisKeyModel keyModel) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.lpop(key);
        }, true);
    }

    @Override
    public Long lpush(final QmRedisKeyModel keyModel, final String... values) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.lpush(key, values);
        }, true);
    }


    @Override
    public Long lpushx(final QmRedisKeyModel keyModel, final String... values) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.lpushx(key, values);
        }, true);
    }

    @Override
    public List<String> lrange(final QmRedisKeyModel keyModel, final long start, final long stop) {
        return (List<String>) template.execute(keyModel, (jedis, key) -> {
            return jedis.lrange(key, start, stop);
        }, false);
    }


    @Override
    public Long lrem(final QmRedisKeyModel keyModel, final long count, final String value) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.lrem(key, count, value);
        }, true);
    }

    @Override
    public String lset(final QmRedisKeyModel keyModel, final long index, final String value) {
        return (String) template.execute(keyModel, (jedis, key) -> {
            return jedis.lset(key, index, value);
        }, true);
    }


    @Override
    public String ltrim(final QmRedisKeyModel keyModel, final long start, final long stop) {
        return (String) template.execute(keyModel, (jedis, key) -> {
            return jedis.ltrim(key, start, stop);
        }, true);
    }

    @Override
    public String rpoplpush(final QmRedisKeyModel sourceKeyModel, final QmRedisKeyModel targetKeyModel) {
        return (String) template.execute(targetKeyModel, (jedis, key) -> {
            return jedis.rpoplpush(sourceKeyModel.buildKey(), key);
        }, true);
    }

    @Override
    public Long rpush(final QmRedisKeyModel keyModel, final String... values) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.rpush(key, values);
        }, true);
    }

    @Override
    public Long rpushx(final QmRedisKeyModel keyModel, final String... values) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.rpushx(key, values);
        }, true);
    }


    /*########################  Set(集合)操作  ####################*/


    @Override
    public Long sadd(final QmRedisKeyModel keyModel, final String... values) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.sadd(key, values);
        }, true);
    }

    @Override
    public Long scard(final QmRedisKeyModel keyModel) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.scard(key);
        }, false);
    }

    @Override
    public Set<String> sdiff(final QmRedisKeyModel... keyModels) {
        return (Set<String>) template.execute(keyModels[0], (jedis, key) -> {
            List<String> keys = Arrays.stream(keyModels).map(QmRedisKeyModel::buildKey).collect(Collectors.toList());
            return jedis.sdiff(keys.toArray(new String[keys.size()]));
        }, false);
    }

    @Override
    public Long sdiffstore(final QmRedisKeyModel destinationKeyModel, final QmRedisKeyModel... keyModels) {
        return (Long) template.execute(destinationKeyModel, (jedis, key) -> {
            List<String> keys = Arrays.stream(keyModels).map(QmRedisKeyModel::buildKey).collect(Collectors.toList());
            return jedis.sdiffstore(key, keys.toArray(new String[keys.size()]));
        }, true);
    }

    @Override
    public Boolean sismember(final QmRedisKeyModel keyModel, final String member) {
        return (Boolean) template.execute(keyModel, (jedis, key) -> {
            return jedis.sismember(key, member);
        }, false);
    }


    @Override
    public Set<String> smembers(final QmRedisKeyModel keyModel) {
        return (Set<String>) template.execute(keyModel, (jedis, key) -> {
            return jedis.smembers(key);
        }, false);
    }

    @Override
    public Long smove(final QmRedisKeyModel srcKeyModel, final QmRedisKeyModel dstKeyModel, final String member) {
        return (Long) template.execute(dstKeyModel, (jedis, key) -> {
            return jedis.smove(dstKeyModel.buildKey(), key, member);
        }, true);
    }


    @Override
    public String spop(final QmRedisKeyModel keyModel) {
        return (String) template.execute(keyModel, (jedis, key) -> {
            return jedis.spop(key);
        }, false);
    }

    @Override
    public List<String> srandmember(QmRedisKeyModel keyModel) {
        return this.srandmember(keyModel, 1);
    }

    @Override
    public List<String> srandmember(final QmRedisKeyModel keyModel, final int count) {
        return (List<String>) template.execute(keyModel, (jedis, key) -> {
            return jedis.srandmember(key, count);
        }, false);
    }

    @Override
    public Long srem(final QmRedisKeyModel keyModel, final String... member) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.srem(key, member);
        }, false);
    }


    @Override
    public Set<String> sunion(final QmRedisKeyModel... keyModels) {
        return (Set<String>) template.execute(keyModels[0], (jedis, key) -> {
            List<String> keys = Arrays.stream(keyModels).map(QmRedisKeyModel::buildKey).collect(Collectors.toList());
            return jedis.sunion(keys.toArray(new String[keys.size()]));
        }, false);
    }


    @Override
    public Long sunionstore(final QmRedisKeyModel keyModel, final QmRedisKeyModel... keyModels) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            List<String> keys = Arrays.stream(keyModels).map(QmRedisKeyModel::buildKey).collect(Collectors.toList());
            return jedis.sunionstore(key, keys.toArray(new String[keys.size()]));
        }, true);
    }


    @Override
    public ScanResult<String> sscan(final QmRedisKeyModel keyModel, final String cursor) {
        return (ScanResult<String>) template.execute(keyModel, (jedis, key) -> {
            return jedis.sscan(key, cursor);
        }, true);
    }


    /*########################  sorted set(有序集合)操作  ####################*/

    @Override
    public Long zadd(final QmRedisKeyModel keyModel, final double score, final String member) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.zadd(key, score, member);
        }, true);
    }

    @Override
    public Long zadd(final QmRedisKeyModel keyModel, final Map<String, Double> scoreMembers) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.zadd(key, scoreMembers);
        }, true);
    }

    @Override
    public Long zcard(final QmRedisKeyModel keyModel) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.zcard(key);
        }, false);
    }

    @Override
    public Long zcount(final QmRedisKeyModel keyModel, final double min, final double max) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.zcount(key, min, max);
        }, false);
    }

    @Override
    public Double zincrby(final QmRedisKeyModel keyModel, final double increment, final String member) {
        return (Double) template.execute(keyModel, (jedis, key) -> {
            return jedis.zincrby(key, increment, member);
        }, true);
    }

    @Override
    public Long zinterstore(final QmRedisKeyModel keyModel, final String... sets) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.zinterstore(key, sets);
        }, false);
    }

    @Override
    public Long zlexcount(final QmRedisKeyModel keyModel, final String min, final String max) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.zlexcount(key, min, max);
        }, false);
    }


    @Override
    public Set<String> zrange(final QmRedisKeyModel keyModel, final long min, final long max) {
        return (Set<String>) template.execute(keyModel, (jedis, key) -> {
            return jedis.zrange(key, min, max);
        }, false);
    }

    @Override
    public Set<String> zrangeByLex(final QmRedisKeyModel keyModel, final String min, final String max, final int offset, final int count) {
        return (Set<String>) template.execute(keyModel, (jedis, key) -> {
            return jedis.zrangeByLex(key, min, max, offset, count);
        }, false);
    }

    @Override
    public Set<String> zrangeByLex(final QmRedisKeyModel keyModel, final String min, final String max) {
        return (Set<String>) template.execute(keyModel, (jedis, key) -> {
            return jedis.zrangeByLex(key, min, max);
        }, false);
    }

    @Override
    public Set<String> zrangeByScore(final QmRedisKeyModel keyModel, final double min, final double max) {
        return (Set<String>) template.execute(keyModel, (jedis, key) -> {
            return jedis.zrangeByScore(key, min, max);
        }, false);
    }


    @Override
    public Long zrank(final QmRedisKeyModel keyModel, final String member) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.zrank(key, member);
        }, false);
    }

    @Override
    public Long zrem(final QmRedisKeyModel keyModel, final String... members) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.zrem(key, members);
        }, true);
    }

    @Override
    public Long zremrangeByLex(final QmRedisKeyModel keyModel, final String min, final String max) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.zremrangeByLex(key, min, max);
        }, true);
    }

    @Override
    public Long zremrangeByRank(final QmRedisKeyModel keyModel, final long start, final long stop) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.zremrangeByRank(key, start, stop);
        }, true);
    }

    @Override
    public Long zremrangeByScore(final QmRedisKeyModel keyModel, final double min, final double max) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.zremrangeByScore(key, min, max);
        }, true);
    }

    @Override
    public Set<String> zrevrange(final QmRedisKeyModel keyModel, final long start, final long stop) {
        return (Set<String>) template.execute(keyModel, (jedis, key) -> {
            return jedis.zrevrange(key, start, stop);
        }, false);
    }

    @Override
    public Set<String> zrevrangeByScore(final QmRedisKeyModel keyModel, final double max, final double min) {
        return (Set<String>) template.execute(keyModel, (jedis, key) -> {
            return jedis.zrevrangeByScore(key, max, min);
        }, false);
    }

    @Override
    public Long zrevrank(final QmRedisKeyModel keyModel, final String member) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.zrevrank(key, member);
        }, false);
    }

    @Override
    public Double zscore(final QmRedisKeyModel keyModel, final String member) {
        return (Double) template.execute(keyModel, (jedis, key) -> {
            return jedis.zscore(key, member);
        }, false);
    }

    @Override
    public Long zunionstore(final QmRedisKeyModel keyModel, final String... sets) {
        return (Long) template.execute(keyModel, (jedis, key) -> {
            return jedis.zunionstore(key, sets);
        }, false);
    }

    @Override
    public ScanResult<Tuple> zscan(final QmRedisKeyModel keyModel, final String cursor) {
        return (ScanResult<Tuple>) template.execute(keyModel, (jedis, key) -> {
            return jedis.zscan(key, cursor);
        }, false);
    }
}
