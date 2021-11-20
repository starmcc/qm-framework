package com.starmcc.qmframework.exception;

/**
 * @author starmcc
 * @version 2019/1/30 19:46
 * 框架异常
 */
public class QmFrameworkException extends RuntimeException {


    private static final long serialVersionUID = -1336849196152458485L;

    public QmFrameworkException() {
        super();
    }

    public QmFrameworkException(String message) {
        super(message);
    }

    public QmFrameworkException(String message, Throwable cause) {
        super(message, cause);
    }
}
