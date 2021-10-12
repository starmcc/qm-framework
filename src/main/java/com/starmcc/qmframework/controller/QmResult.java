package com.starmcc.qmframework.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.starmcc.qmframework.config.AesConfiguration;
import com.starmcc.qmframework.config.TransmitConfiguration;
import com.starmcc.qmframework.exception.QmFrameException;
import com.starmcc.qmframework.tools.operation.QmAesUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author starmcc
 * @version 2020/9/22 15:36
 * 状态返回静态工具
 */
public class QmResult {

    public static String success() {
        return QmResult.sendJson(QmCode._1);
    }

    public static String success(Object data) {
        return QmResult.sendJson(QmCode._1, data);
    }

    public static String success(String msg, Object data) {
        return QmResult.sendJson(QmCode._1, msg, data);
    }

    public static String fail() {
        return QmResult.sendJson(QmCode._2);
    }

    public static String fail(Object data) {
        return QmResult.sendJson(QmCode._2, data);
    }

    public static String fail(String msg, Object data) {
        return QmResult.sendJson(QmCode._2, msg, data);
    }

    public static String error() {
        return QmResult.sendJson(QmCode._500);
    }

    public static String error(Object data) {
        return QmResult.sendJson(QmCode._500, data);
    }

    public static String error(String msg, Object data) {
        return QmResult.sendJson(QmCode._500, msg, data);
    }

    public static String paramNull() {
        return QmResult.sendJson(QmCode._100);
    }

    public static String paramNull(Object data) {
        return QmResult.sendJson(QmCode._100, data);
    }

    public static String paramNull(String msg, Object data) {
        return QmResult.sendJson(QmCode._100, msg, data);
    }

    public static String paramFail() {
        return QmResult.sendJson(QmCode._101);
    }

    public static String paramFail(Object data) {
        return QmResult.sendJson(QmCode._101, data);
    }

    public static String paramFail(String msg, Object data) {
        return QmResult.sendJson(QmCode._101, msg, data);
    }

    public static String loginNotIn() {
        return QmResult.sendJson(QmCode._103);
    }

    public static String loginNotIn(Object data) {
        return QmResult.sendJson(QmCode._103, data);
    }

    public static String loginNotIn(String msg, Object data) {
        return QmResult.sendJson(QmCode._103, msg, data);
    }

    public static String permissionDenied() {
        return QmResult.sendJson(QmCode._104);
    }

    public static String permissionDenied(Object data) {
        return QmResult.sendJson(QmCode._104, data);
    }

    public static String permissionDenied(String msg, Object data) {
        return QmResult.sendJson(QmCode._104, msg, data);
    }

    public static String ssoError() {
        return QmResult.sendJson(QmCode._105);
    }

    public static String ssoError(Object data) {
        return QmResult.sendJson(QmCode._105, data);
    }

    public static String ssoError(String msg, Object data) {
        return QmResult.sendJson(QmCode._105, msg, data);
    }

    public static String unknowError() {
        return QmResult.sendJson(QmCode._999);
    }

    public static String unknowError(Object data) {
        return QmResult.sendJson(QmCode._999, data);
    }

    public static String unknowError(String msg, Object data) {
        return QmResult.sendJson(QmCode._999, msg, data);
    }


    /**
     * 接口回调方法
     *
     * @param code QmCode
     * @return Returns the specified data according to the method
     */
    public static String sendJson(QmCode code) {
        return QmResult.sendJson(code, QmCode.getMsg(code), null);
    }

    /**
     * 接口回调方法
     *
     * @param code QmCode
     * @param data 传递数据
     * @return Returns the specified data according to the method
     */
    public static String sendJson(QmCode code, Object data) {
        return QmResult.sendJson(code, QmCode.getMsg(code), data);
    }

    /**
     * 接口回调方法
     *
     * @param code QmCode
     * @param msg  自定义消息
     * @param data 传递数据
     * @return Returns the specified data according to the method
     */
    public static String sendJson(QmCode code, String msg, Object data) {
        return QmResult.sendJson(code.getCode(), msg, data);
    }

    /**
     * 接口回调方法
     *
     * @param code code
     * @param msg  自定义消息
     * @return Returns the specified data according to the method
     */
    public static String sendJson(int code, String msg) {
        return QmResult.sendJson(code, msg, null);
    }

    /**
     * 接口回调方法
     *
     * @param code code
     * @param data 传递数据
     * @return Returns the specified data according to the method
     */
    public static String sendJson(int code, Object data) {
        return QmResult.sendJson(code, "", data);
    }

    /**
     * 接口回调方法
     *
     * @param code code
     * @param msg  自定义消息
     * @param data 传递数据
     * @return Returns the specified data according to the method
     */
    public static String sendJson(int code, String msg, Object data) {
        Map<String, Object> responseMap = new LinkedHashMap<>(16);
        responseMap.put("code", code);
        responseMap.put("msg", msg);
        responseMap.put("data", data);
        responseMap.put("responseTime", new Date());
        return QmResult.parseJsonToResponse(responseMap);
    }

    /**
     * 构建响应数据
     *
     * @param responseMap
     * @return Returns the specified data according to the method
     */
    protected static String parseJsonToResponse(Map<String, Object> responseMap) {
        // 解析成json字符串
        String json = JSONObject.toJSONString(responseMap, SerializerFeature.WriteMapNullValue);
        if (AesConfiguration.start) {
            // 如果加密，则对json字符串加密
            try {
                json = QmAesUtil.encryptAes(json);
            } catch (Exception e) {
                throw new QmFrameException("加密异常", e);
            }
        }
        // 不加密逻辑
        if (StringUtils.isBlank(TransmitConfiguration.responseKey)) {
            // 如果没有key则直接返回
            return json;
        }
        Map<String, Object> valueMap = new HashMap<>(16);
        valueMap.put(TransmitConfiguration.responseKey,
                AesConfiguration.start ? json : responseMap);
        return JSONObject.toJSONString(valueMap, SerializerFeature.WriteMapNullValue);
    }

}
