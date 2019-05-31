package com.starmcc.qmframework.exception;

/**
 * 参数解析错误时触发该异常!
 *
 * @Author qm
 * @Date 2019/4/19 12:34
 */
public class QmParamErrorException extends RuntimeException {

    public QmParamErrorException(String message) {
        super(String.format("required param {%s} is error", message));
    }

    public QmParamErrorException(Throwable cause) {
        super(cause);
    }
}
