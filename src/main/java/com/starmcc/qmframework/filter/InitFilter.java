package com.starmcc.qmframework.filter;

import com.starmcc.qmframework.config.SpecialConfiguration;
import com.starmcc.qmframework.config.VersionConfiguration;
import com.starmcc.qmframework.controller.QmCode;
import com.starmcc.qmframework.controller.QmController;
import com.starmcc.qmframework.tools.spring.QmSpringManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 该过滤器主要实现版本控制、重写RequestBody、实现AES对称无痕解密
 *
 * @Author starmcc
 * @Date 2018年11月24日 上午1:15:27
 */
public class InitFilter extends QmController implements Filter {
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
            chain.doFilter(request, response);
            return;
        }
        // 设置请求头和相应头
        settingRequsetOrResponse(request, response);
        LOG.info("※※※请求URI：" + request.getRequestURI() + "※※※");
        //特殊请求
        if (verifySpecialURI(request)) {
            chain.doFilter(request, response);
            return;
        }
        //版本控制
        if (!verifyVersion(request)) {
            response.getWriter().write(super.sendJSON(QmCode._102));
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
     * @return
     */
    private boolean verifySpecialURI(HttpServletRequest request) {
        for (String uri : SpecialConfiguration.uri) {
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
     * @return
     * @throws IOException
     */
    private boolean verifyVersion(HttpServletRequest request) throws IOException {
        //不开启版本控制
        if (!VersionConfiguration.start) {
            return true;
        }
        ;
        //目前版本号
        String versionRequest = request.getHeader("version");
        LOG.info("※※※请求版本号：" + versionRequest + "※※※");
        LOG.info("※※※当前版本号：" + VersionConfiguration.now + "※※※");
        if (VersionConfiguration.now.equals(versionRequest)) {
            //通过
            return true;
        }
        LOG.debug("※※※进入版本控制判断※※※");
        if (VersionConfiguration.allows != null && VersionConfiguration.allows.length > 0) {
            for (String version : VersionConfiguration.allows) {
                if (version.equals(versionRequest)) {
                    //通过
                    return true;
                }
            }
        }
        LOG.debug("※※※请求失败,服务器并无配置可允许版本※※※");
        return false;
    }

}
