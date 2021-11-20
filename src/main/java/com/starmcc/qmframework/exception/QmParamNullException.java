package com.starmcc.qmframework.exception;

/**
 * @author starmcc
 * @version 2019/4/19 12:34
 * 参数为空时触发该异常！
 */
public class QmParamNullException extends RuntimeException {

    private static final long serialVersionUID = -3595220673229609587L;

    public QmParamNullException(String message) {
        super(String.format("required param {%s} is not present", message));
    }
}
