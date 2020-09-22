package com.starmcc.qmframework.exception;


import com.starmcc.qmframework.controller.QmCode;
import com.starmcc.qmframework.controller.QmResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局异常处理、将程序中可能出现的异常进行捕获，返回JSON格式的错误信息。
 *
 * @Author starmcc
 * @Date 2018年11月24日 上午1:30:20
 */
@ControllerAdvice
@Controller
@RequestMapping(value = "/error")
public class QmExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(QmExceptionHandler.class);

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public String httpRequestMethodNotSupportedException(HttpServletResponse response,
                                                         Exception e) {
        LOG.info("请求方式错误,请核实请求方式 `GET` and `POST`");
        response.setStatus(200);
        return QmResult.sendJson(QmCode._405);
    }

    @ExceptionHandler(QmParamNullException.class)
    @ResponseBody
    public String qmParamNullException(HttpServletResponse response,
                                       Exception e) {
        LOG.info("缺少某些请求参数,请核实请求参数是否正确!");
        e.printStackTrace();
        response.setStatus(200);
        return QmResult.paramNull();
    }

    @ExceptionHandler(QmParamErrorException.class)
    @ResponseBody
    public String qmParamErrorException(HttpServletResponse response,
                                        Exception e) {
        LOG.info("请求参数错误,请核实请求参数是否正确!");
        response.setStatus(200);
        return QmResult.paramFail();
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public String httpMediaTypeNotSupportedException(HttpServletResponse response,
                                                     Exception e) {
        response.setStatus(200);
        return QmResult.sendJson(QmCode._415);
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public String notFoundPage404(HttpServletResponse response,
                                  Exception e) throws IOException {
        LOG.info("请求地址错误,请核实请求地址是否正确!");
        response.setStatus(200);
        return QmResult.sendJson(QmCode._404);
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String defaultException(HttpServletResponse response, Exception e) throws IOException {
        LOG.info("服务器遇到了错误,请检查相关问题!");
        e.printStackTrace();
        response.setStatus(200);
        return QmResult.error();
    }

}