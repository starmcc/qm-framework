package com.starmcc.qmframework.filter;

import com.alibaba.fastjson.JSONObject;
import com.starmcc.qmframework.config.AesConfiguration;
import com.starmcc.qmframework.config.TransmitConfiguration;
import com.starmcc.qmframework.tools.operation.QmAesUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.Objects;

/**
 * @author starmcc
 * @version 2018年11月24日 上午1:23:44
 * qmframework 重写 RequestBody, 并实现AES无缝对称加密
 */
public class QmRequestWrapper extends HttpServletRequestWrapper {

    private static final Logger LOG = LoggerFactory.getLogger(QmRequestWrapper.class);

    private static final String CHARSET_NAME = "UTF-8";
    /**
     * body
     */
    private final byte[] body;

    /**
     * 主要通过构造方法实现
     *
     * @param request
     * @throws IOException
     */
    public QmRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        this.printLog(request);
        this.body = this.getBodyByAes(this.getBodyString(request)).getBytes(Charset.forName(CHARSET_NAME));
    }

    /**
     * 打印日志
     *
     * @param request
     */
    private void printLog(HttpServletRequest request) {
        Enumeration<String> e = request.getHeaderNames();
        // 日志打印
        StringBuffer sbf = new StringBuffer();
        while (e.hasMoreElements()) {
            String name = (String) e.nextElement();
            String value = request.getHeader(name);
            sbf.append(name);
            sbf.append("=");
            sbf.append(value);
            sbf.append("\n");
        }
        LOG.debug(sbf.toString());
        // 日志打印结束
    }

    /**
     * 请求解析，解析格式为{"配置的key名":{"param":"xxx","param2":"xxx"}}的JSON参数
     *
     * @param body 请求body
     * @return json
     */
    private String getBodyByAes(String body) {
        if (StringUtils.isBlank(body)) {
            return "";
        }
        if (StringUtils.isNotBlank(TransmitConfiguration.requestKey)) {
            JSONObject jsonObject = JSONObject.parseObject(body);
            body = jsonObject.getString(TransmitConfiguration.requestKey);
            if (StringUtils.isBlank(body)) {
                return "";
            }
        }
        if (AesConfiguration.start) {
            try {
                body = QmAesUtil.decryptAes(body);
            } catch (Exception e) {
                LOG.error("解密异常: {}", e);
                return null;
            }
        }
        return body;
    }

    /**
     * 获取请求Body
     *
     * @param request ServletRequest
     * @return body
     */
    public String getBodyString(ServletRequest request) {
        StringBuilder sb = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;
        try {
            inputStream = request.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            LOG.error("IOException异常: {}", e);
        } finally {
            if (Objects.nonNull(inputStream)) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    LOG.error("IOException异常: {}", e);
                }
            }
            if (Objects.nonNull(reader)) {
                try {
                    reader.close();
                } catch (IOException e) {
                    LOG.error("IOException异常: {}", e);
                }
            }
        }
        return sb.toString();
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {

        final ByteArrayInputStream bais = new ByteArrayInputStream(body);

        return new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }
        };
    }

    @Override
    public String getHeader(String name) {
        return super.getHeader(name);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        return super.getHeaderNames();
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        return super.getHeaders(name);
    }

}
