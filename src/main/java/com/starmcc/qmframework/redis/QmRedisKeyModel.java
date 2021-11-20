package com.starmcc.qmframework.redis;

import com.starmcc.qmframework.exception.QmFrameworkException;
import javafx.util.Builder;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Objects;

/**
 * qm缓存模型
 *
 * @author starmcc
 * @date 2021/11/19
 */
public class QmRedisKeyModel implements Serializable {

    private static final long serialVersionUID = -107379311974997784L;

    /**
     * Number of milliseconds in a standard second.
     */
    public static final int MILLISECOND_PER_SECOND = 1000;
    /**
     * Number of seconds in a standard half a minute.
     */
    public static final int SECOND_HALF_MINUTE = 30;
    /**
     * Number of seconds in a standard minute.
     */
    public static final int SECOND_PER_MINUTE = 60;
    /**
     * Number of seconds in a standard hour.
     */
    public static final int SECOND_PER_HOUR = 60 * SECOND_PER_MINUTE;
    /**
     * Number of seconds in a standard half a hour.
     */
    public static final int SECOND_PER_HALF_HOUR = SECOND_PER_HOUR / 2;
    /**
     * Number of seconds in a standard day.
     */
    public static final int SECOND_PER_DAY = 24 * SECOND_PER_HOUR;
    /**
     * Number of seconds in a standard Week
     */
    public static final int SECOND_PER_WEEK = 7 * SECOND_PER_DAY;

    /**
     * Number of seconds in a standard Month
     */
    public static final int SECOND_PER_MONTH = 30 * SECOND_PER_DAY;

    /**
     * 键的分隔符
     */
    private static final String KEY_SEPARATOR = "$!$";

    /**
     * 库索引
     */
    private Integer index;
    /**
     * 有效时间
     */
    private Long expTime;
    /**
     * 关键的前缀
     */
    private String keyPrefix;
    /**
     * 扩展参数
     */
    private String[] expands;

    private QmRedisKeyModel(QmRedisKeyModelBuilder builder) {
        this(builder.index, builder.expTime, builder.keyPrefix, builder.expands);
    }

    public static QmRedisKeyModelBuilder builder() {
        return new QmRedisKeyModelBuilder();
    }


    /**
     * QmRedisKey模型构造方法
     *
     * @param index     库索引
     * @param expTime   有效时间
     * @param keyPrefix 键的前缀
     * @param expands   扩展参数
     */
    public QmRedisKeyModel(Integer index, Long expTime, String keyPrefix, String... expands) {
        this.index = Objects.isNull(index) ? 0 : index;
        final int min_index = 0;
        final int max_index = 15;
        if (this.index < min_index || this.index > max_index) {
            throw new QmFrameworkException("Redis index is out of bounds!");
        }
        this.expTime = Objects.isNull(expTime) ? SECOND_PER_MINUTE : expTime;
        if (StringUtils.isBlank(keyPrefix)) {
            throw new QmFrameworkException("Cannot build an empty Redis key!");
        }
        this.keyPrefix = keyPrefix;
        this.expands = expands;
    }

    /**
     * 构建Key
     *
     * @return {@link String}
     */
    public String buildKey() {
        StringBuffer expandKey = new StringBuffer();
        if (ArrayUtils.isEmpty(expands)) {
            return this.keyPrefix;
        }
        for (String expand : expands) {
            expandKey.append(KEY_SEPARATOR).append(expand);
        }
        return this.keyPrefix + expandKey.toString();
    }


    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public String[] getExpands() {
        return expands;
    }

    public void setExpands(String[] expands) {
        this.expands = expands;
    }

    public Long getExpTime() {
        return expTime;
    }

    public void setExpTime(Long expTime) {
        this.expTime = expTime;
    }

    public static class QmRedisKeyModelBuilder implements Builder<QmRedisKeyModel> {

        private Integer index;
        private Long expTime;
        private String keyPrefix;
        private String[] expands;

        public QmRedisKeyModelBuilder index(Integer index) {
            this.index = index;
            return this;
        }

        public QmRedisKeyModelBuilder expTime(Long expTime) {
            this.expTime = expTime;
            return this;
        }

        public QmRedisKeyModelBuilder keyPrefix(String keyPrefix) {
            this.keyPrefix = keyPrefix;
            return this;
        }

        public QmRedisKeyModelBuilder expand(String expand) {
            if (StringUtils.isBlank(expand)) {
                return this;
            }
            if (ArrayUtils.isEmpty(this.expands)) {
                this.expands = new String[0];
            }
            this.expands = ArrayUtils.add(this.expands, expand);
            return this;
        }

        public QmRedisKeyModelBuilder expands(String... expands) {
            if (ArrayUtils.isEmpty(expands)) {
                return this;
            }
            if (ArrayUtils.isEmpty(this.expands)) {
                this.expands = new String[0];
            }
            this.expands = ArrayUtils.addAll(this.expands, expands);
            return this;
        }

        @Override
        public QmRedisKeyModel build() {
            return new QmRedisKeyModel(this);
        }
    }


}
