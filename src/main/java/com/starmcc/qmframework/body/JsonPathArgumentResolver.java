package com.starmcc.qmframework.body;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.starmcc.qmframework.config.TransmitConfiguration;
import com.starmcc.qmframework.exception.QmParamErrorException;
import com.starmcc.qmframework.exception.QmParamNullException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author starmcc
 * @version 2018年9月10日 V1.0
 * 自定义解析json数据
 */
public class JsonPathArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String JSONBODY_ATTRIBUTE = "JSON_REQUEST_BODY";

    /**
     * 设置支持的方法参数类型
     *
     * @param parameter 方法参数
     * @return 支持的类型
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // 支持带@QmBody注解的参数
        return parameter.hasParameterAnnotation(QmBody.class);
    }

    /**
     * 利用fastjson进行参数解析
     * 注意：非基本类型返回null会报空指针异常，要通过反射或者JSON工具类创建一个空对象
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        String jsonBody = this.getRequestBody(webRequest);
        JSONObject jsonObject = JSON.parseObject(jsonBody);
        if (jsonObject == null) {
            throw new QmParamNullException(TransmitConfiguration.getRequestKey());
        }
        // 根据@qmBody注解value作为json解析的key
        QmBody qmBody = parameter.getParameterAnnotation(QmBody.class);
        // 注解的value是JSON的key
        String key = qmBody.key();
        Object value = null;
        // 如果@qmBody注解没有设置value，则取参数名FrameworkServlet作为json解析的key
        if (StringUtils.isNotEmpty(key)) {
            value = jsonObject.get(key);
        } else {
            // 注解为设置value则用参数名当做json的key
            key = parameter.getParameterName();
            value = jsonObject.get(key);
        }
        // 如果required = true 则不允许value == null
        if (value == null && qmBody.required()) {
            throw new QmParamNullException(key);
        } else if (value == null && qmBody.required() == false) {
            return value;
        }
        Class<?> parameterType = parameter.getParameterType();
        // 通过注解的value或者参数名解析，能拿到value进行解析
        //基本类型
        if (parameterType.isPrimitive()) {
            return parsePrimitive(parameterType.getName(), value);
        }
        // 基本类型包装类
        if (isPackDataTypes(parameterType)) {
            return parseBasicTypeWrapper(parameterType, value);
            // 字符串类型
        } else if (parameterType == String.class) {
            if (StringUtils.isEmpty(value.toString()) && qmBody.required()) {
                throw new QmParamNullException(key);
            }
            return value.toString();
        }
        // 解析Date时间
        if (parameterType == Date.class) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .parse(value.toString());
        }
        // 如果是list则解析list
        if (parameterType.isAssignableFrom(List.class)) {
            Type genericType = parameter.getGenericParameterType();
            if (genericType instanceof ParameterizedType) {
                try {
                    ParameterizedType pt = (ParameterizedType) genericType;
                    //得到泛型里的class类型对象
                    Class<?> genericClazz = (Class<?>) pt.getActualTypeArguments()[0];
                    return JSON.parseArray(value.toString(), genericClazz);
                } catch (Exception e) {
                    try {
                        return JSON.parseArray(value.toString());
                    } catch (Exception e1) {
                        if (qmBody.required()) {
                            throw new QmParamErrorException(key);
                        }
                        return null;
                    }
                }
            }
            return JSON.parseArray(value.toString());
        }
        return JSON.parseObject(value.toString(), parameterType);
    }


    /**
     * 基本类型解析
     *
     * @param String parameterTypeName
     * @param Object value
     * @return Object
     */
    private Object parsePrimitive(String parameterTypeName, Object value) {
        final String booleanTypeName = "boolean";
        if (booleanTypeName.equals(parameterTypeName)) {
            return Boolean.valueOf(value.toString());
        }
        final String intTypeName = "int";
        if (intTypeName.equals(parameterTypeName)) {
            return Integer.valueOf(value.toString());
        }
        final String charTypeName = "char";
        if (charTypeName.equals(parameterTypeName)) {
            return value.toString().charAt(0);
        }
        final String shortTypeName = "short";
        if (shortTypeName.equals(parameterTypeName)) {
            return Short.valueOf(value.toString());
        }
        final String longTypeName = "long";
        if (longTypeName.equals(parameterTypeName)) {
            return Long.valueOf(value.toString());
        }
        final String floatTypeName = "float";
        if (floatTypeName.equals(parameterTypeName)) {
            return Float.valueOf(value.toString());
        }
        final String doubleTypeName = "double";
        if (doubleTypeName.equals(parameterTypeName)) {
            return Double.valueOf(value.toString());
        }
        final String byteTypeName = "byte";
        if (byteTypeName.equals(parameterTypeName)) {
            return Byte.valueOf(value.toString());
        }
        return null;
    }

    /**
     * 基本类型包装类型解析
     *
     * @param parameterType Class
     * @param value         Object
     * @return Object
     */
    private Object parseBasicTypeWrapper(Class<?> parameterType, Object value) {
        if (Number.class.isAssignableFrom(parameterType)) {
            Number number = (Number) value;
            if (parameterType == Integer.class) {
                return number.intValue();
            } else if (parameterType == Short.class) {
                return number.shortValue();
            } else if (parameterType == Long.class) {
                return number.longValue();
            } else if (parameterType == Float.class) {
                return number.floatValue();
            } else if (parameterType == Double.class) {
                return number.doubleValue();
            } else if (parameterType == Byte.class) {
                return number.byteValue();
            }
        } else if (parameterType == Boolean.class) {
            return Boolean.valueOf(value.toString());
        } else if (parameterType == Character.class) {
            return value.toString().charAt(0);
        }
        return null;
    }

    /**
     * 是否为包装数据类型
     *
     * @param clazz class
     * @return boolean
     */
    private boolean isPackDataTypes(Class clazz) {
        Set<Class> classSet = new HashSet<>();
        classSet.add(Integer.class);
        classSet.add(Long.class);
        classSet.add(Short.class);
        classSet.add(Float.class);
        classSet.add(Double.class);
        classSet.add(Boolean.class);
        classSet.add(Byte.class);
        classSet.add(Character.class);
        return classSet.contains(clazz);
    }


    /**
     * 获取请求体JSON字符串
     *
     * @param webRequest NativeWebRequest
     * @return String
     */
    private String getRequestBody(NativeWebRequest webRequest) {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        // 有就直接获取
        String jsonBody = (String) webRequest.getAttribute(JSONBODY_ATTRIBUTE, NativeWebRequest.SCOPE_REQUEST);
        // 没有就从请求中读取
        if (jsonBody == null) {
            try {
                jsonBody = IOUtils.toString(servletRequest.getReader());
                webRequest.setAttribute(JSONBODY_ATTRIBUTE, jsonBody, NativeWebRequest.SCOPE_REQUEST);
            } catch (IOException e) {
                throw new QmParamErrorException(e);
            }
        }
        return jsonBody;
    }
}
