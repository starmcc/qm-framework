package com.starmcc.qmframework.exception;

/**
 * @author starmcc
 * @version 2019/4/19 12:34
 * 参数解析错误时触发该异常!
 */
public class QmParamErrorException extends RuntimeException {

    private static final long serialVersionUID = 3560548224601970240L;

    public QmParamErrorException(String message) {
        super(String.format("required param {%s} is error", message));
    }

    public QmParamErrorException(Throwable cause) {
        super(cause);
    }
}
