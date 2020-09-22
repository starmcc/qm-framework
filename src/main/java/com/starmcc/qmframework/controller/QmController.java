package com.starmcc.qmframework.controller;

/**
 * 父类Controller, 编写Controller请继承该类。
 *
 * @Author starmcc
 * @Date 2018年11月24日 上午1:42:26
 */
public class QmController {

    @Deprecated
    public String sendJSON(QmCode code) {
        return this.sendJSON(code, QmCode.getMsg(code), null);
    }

    @Deprecated
    public String sendJSON(QmCode code, Object data) {
        return QmResult.sendJson(code, QmCode.getMsg(code), data);
    }

    @Deprecated
    public String sendJSON(QmCode code, String msg, Object data) {
        return QmResult.sendJson(code.getCode(), msg, data);
    }

    @Deprecated
    public String sendJSON(int code, String msg) {
        return QmResult.sendJson(code, msg, null);
    }

    @Deprecated
    public String sendJSON(int code, Object data) {
        return QmResult.sendJson(code, "", data);
    }

    @Deprecated
    public String sendJSON(int code, String msg, Object data) {
        return QmResult.sendJson(code, msg, data);
    }

}
