package com.starmcc.qmframework.redis;

import com.starmcc.qmframework.config.QmRedisConfiguration;
import com.starmcc.qmframework.redis.QmRedisServiceImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 使用qm跨域配置
 *
 * @author starmcc
 * @date 2021/11/19
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@EnableConfigurationProperties(QmRedisConfiguration.class)
@Import(QmRedisServiceImpl.class)
public @interface EnableQmRedisService {
}
