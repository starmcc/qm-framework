package com.starmcc.qmframework.filter;

import com.starmcc.qmframework.config.SpecialConfiguration;
import com.starmcc.qmframework.config.VersionConfiguration;
import com.starmcc.qmframework.controller.QmCode;
import com.starmcc.qmframework.controller.QmResult;
import com.starmcc.qmframework.tools.spring.QmSpringManager;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author starmcc
 * @version 2018年11月24日 上午1:15:27
 * 该过滤器主要实现版本控制、重写RequestBody、实现AES对称无痕解密
 */
public class InitFilter implements Filter {
    /**
     * Logger slf4j
     */
    private static final Logger LOG = LoggerFactory.getLogger(InitFilter.class);

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            chain.doFilter(req, res);
            return;
        }
        LOG.info("※ Request URI{" + request.getRequestURI() + "} ※");
        //特殊请求
        if (verifySpecialUri(request)) {
            chain.doFilter(req, res);
            return;
        }
        // 设置请求头和相应头
        settingRequsetOrResponse(request, response);
        //版本控制
        if (!verifyVersion(request)) {
            response.getWriter().write(QmResult.sendJson(QmCode._102));
            return;
        }
        /**
         * 重写RequestBody,并对body进行对称AES解密。
         */
        ServletRequest requestWrapper = new QmRequestWrapper(request);
        chain.doFilter(requestWrapper, response);
    }

    /**
     * 设置请求头和相应头,支持跨域。
     *
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     */
    private void settingRequsetOrResponse(HttpServletRequest request,
                                          HttpServletResponse response)
            throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
    }


    /**
     * 验证是否为特殊请求
     *
     * @param request
     * @return Returns the specified data according to the method
     */
    private boolean verifySpecialUri(HttpServletRequest request) {
        for (String uri : SpecialConfiguration.getUri()) {
            if (QmSpringManager.verifyMatchURI(uri, request.getRequestURI())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 版本验证工具
     *
     * @param request
     * @return Returns the specified data according to the method
     * @throws IOException
     */
    private boolean verifyVersion(HttpServletRequest request) throws IOException {
        //不开启版本控制
        if (!VersionConfiguration.isStart()) {
            return true;
        }
        ;
        //目前版本号
        String versionRequest = request.getHeader("version");
        LOG.info("※ Request version{" + versionRequest + "} ※");
        LOG.info("※ Current version{" + VersionConfiguration.getNow() + "} ※");
        if (VersionConfiguration.getNow().equals(versionRequest)) {
            //通过
            return true;
        }
        LOG.debug("※ Enter version control judgment ※");

        if (ArrayUtils.isNotEmpty(VersionConfiguration.getAllows())) {
            for (String version : VersionConfiguration.getAllows()) {
                if (version.equals(versionRequest)) {
                    //通过
                    return true;
                }
            }
        }
        LOG.debug("※ The request failed. The server is not configured to allow a version ※");
        return false;
    }

}
