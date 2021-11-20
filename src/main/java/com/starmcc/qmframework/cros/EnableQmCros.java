package com.starmcc.qmframework.cros;

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
@Import(QmCrosFilterConfig.class)
public @interface EnableQmCros {
}
