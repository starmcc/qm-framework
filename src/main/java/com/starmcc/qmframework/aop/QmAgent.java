package com.starmcc.qmframework.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author starmcc
 * @Date 2020/9/22 15:47
 * @Description AOP 切入点
 */
@Target(ElementType.METHOD)//使用在方法上
@Retention(RetentionPolicy.RUNTIME)
public @interface QmAgent {
}
