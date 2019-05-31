package com.starmcc.qmframework.exception;

/**
 * 参数为空时触发该异常！
 *
 * @Author qm
 * @Date 2019/4/19 12:34
 */
public class QmParamNullException extends RuntimeException {

    public QmParamNullException(String message) {
        super(String.format("required param {%s} is not present", message));
    }
}
