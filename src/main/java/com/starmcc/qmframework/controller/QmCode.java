package com.starmcc.qmframework.controller;


import com.starmcc.qmframework.config.TransmitConfiguration;

/**
 * @author starmcc
 * @version 2018年10月26日 上午12:58:22
 * 数据请求状态码工具类
 */
public enum QmCode {
    /**
     * 成功
     */
    _1(1, "操作成功", "Success"),
    /**
     * 失败
     */
    _2(2, "操作失败", "Defeated"),
    /**
     * 参数提供不完整
     */
    _100(100, "参数提供不完整", "Parameter is incomplete"),
    /**
     * 参数错误
     */
    _101(101, "参数错误", "Parameter error"),
    /**
     * 版本号未通过
     */
    _102(102, "未通过版本校验", "Version validation failed"),
    /**
     * 未登录/登录过期/请求ip校验失败
     */
    _103(103, "登录状态已失效", "Login not in"),
    /**
     * 权限不足，拒绝访问
     */
    _104(104, "无访问权限", "Permission denied"),
    /**
     * 您的账号已在其他地方登录!
     */
    _105(105, "您的账号已在其他地方登录!", "Your account has been logged in elsewhere"),
    /**
     * 请求404,找不到资源
     */
    _404(404, "资源丢失啦", "Can't find resources"),
    /**
     * 请求405,请求类型错误POST or GET
     */
    _405(405, "请求方式错误", "Wrong request mode"),
    /**
     * 请求415,不支持的媒体类型
     */
    _415(415, "不支持的媒体类型", "Unsupported media types"),
    /**
     * 服务器错误
     */
    _500(500, "服务器开小差啦", "Server Error"),
    /**
     * 未知错误
     */
    _999(999, "未知错误", "Server unknow");

    /**
     * 编码
     */
    private final int code;
    /**
     * 中文msg
     */
    private final String cnMsg;
    /**
     * 英文msg
     */
    private final String enMsg;

    private QmCode(int code, String cnMsg, String enMsg) {
        this.code = code;
        this.cnMsg = cnMsg;
        this.enMsg = enMsg;
    }

    public int getCode() {
        return code;
    }

    public String getCnMsg() {
        return cnMsg;
    }

    public String getEnMsg() {
        return enMsg;
    }

    /**
     * 根据code获取对应msg信息
     *
     * @param code
     * @return msg
     */
    public static final String getMsg(QmCode code) {
        return "cn".equalsIgnoreCase(TransmitConfiguration.getResponseMessageLang()) ? code.getCnMsg() : code.getEnMsg();
    }
}
