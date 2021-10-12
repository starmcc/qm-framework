package com.starmcc.qmframework.exception;

/**
 * @author starmcc
 * @version 2019/1/30 19:46
 * 框架异常
 */
public class QmFrameException extends RuntimeException {


    public QmFrameException() {
        super();
    }

    public QmFrameException(String message) {
        super(message);
    }

    public QmFrameException(String message, Throwable cause) {
        super(message, cause);
    }
}
