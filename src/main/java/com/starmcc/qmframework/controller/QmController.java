package com.starmcc.qmframework.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.starmcc.qmframework.config.AesConfiguration;
import com.starmcc.qmframework.config.TransmitConfiguration;
import com.starmcc.qmframework.exception.QmFrameException;
import com.starmcc.qmframework.tools.operation.QmAesTools;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 父类Controller, 编写Controller请继承该类。
 *
 * @Author qm
 * @Date 2018年11月24日 上午1:42:26
 */
public class QmController {

    /**
     * 接口回调方法
     *
     * @param code QmCode
     * @return
     */
    public String sendJSON(QmCode code) {
        return this.sendJSON(code, QmCode.getMsg(code), null);
    }

    /**
     * 接口回调方法
     *
     * @param code QmCode
     * @param data 传递数据
     * @return
     */
    public String sendJSON(QmCode code, Object data) {
        return this.sendJSON(code, QmCode.getMsg(code), data);
    }

    /**
     * 接口回调方法
     *
     * @param code QmCode
     * @param msg  自定义消息
     * @param data 传递数据
     * @return
     */
    public String sendJSON(QmCode code, String msg, Object data) {
        return this.sendJSON(code.getCode(), msg, data);
    }

    /**
     * 接口回调方法
     *
     * @param code code
     * @param msg  自定义消息
     * @return
     */
    public String sendJSON(int code, String msg) {
        return this.sendJSON(code, msg, null);
    }

    /**
     * 接口回调方法
     *
     * @param code code
     * @param data 传递数据
     * @return
     */
    public String sendJSON(int code, Object data) {
        return this.sendJSON(code, "", data);
    }

    /**
     * 接口回调方法
     *
     * @param code code
     * @param msg  自定义消息
     * @param data 传递数据
     * @return
     */
    public String sendJSON(int code, String msg, Object data) {
        Map<String, Object> responseMap = new LinkedHashMap<>(16);
        responseMap.put("code", code);
        responseMap.put("msg", msg);
        responseMap.put("data", data);
        responseMap.put("responseTime", new Date());
        return this.parseJsonToResponse(responseMap);
    }

    /**
     * 解析请求json字符串
     *
     * @param value
     * @return
     */
    public String parseRequestJson(String value) {
        JSONObject jsonObject = JSONObject.parseObject(value);
        String json = jsonObject.getString(TransmitConfiguration.requestKey);
        if (AesConfiguration.start) {
            try {
                json = QmAesTools.decryptAES(json);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return json;
    }

    /**
     * 构建响应数据
     *
     * @param json
     * @return
     */
    private String parseJsonToResponse(Map<String, Object> responseMap) {
        // 解析成json字符串
        String json = JSONObject.toJSONString(responseMap, SerializerFeature.WriteMapNullValue);
        if (AesConfiguration.start) {
            // 如果加密，则对json字符串加密
            try {
                json = QmAesTools.encryptAES(json);
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
