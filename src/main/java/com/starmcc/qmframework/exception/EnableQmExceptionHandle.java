package com.starmcc.qmframework.exception;

import com.starmcc.qmframework.exception.QmExceptionHandler;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 使用qm异常处理机制
 *
 * @author starmcc
 * @date 2021/11/19
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(QmExceptionHandler.class)
public @interface EnableQmExceptionHandle {
}
