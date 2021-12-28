package com.starmcc.qmframework.exception;


/**
 * @author starmcc
 * @date 2021/12/28
 * redis异常
 */
public class QmRedisServiceException extends RuntimeException {
    private static final long serialVersionUID = 3387875927505134042L;

    public QmRedisServiceException(String message) {
        super(String.format("Redis {%s} RuntimeException", message));
    }

    public QmRedisServiceException(String message, Throwable cause) {
        super(String.format("Redis {%s} RuntimeException", message), cause);
    }
}
