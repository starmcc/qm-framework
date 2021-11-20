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
 * @author starmcc
 * @version 2018年11月24日 上午1:30:20
 * 全局异常处理、将程序中可能出现的异常进行捕获，返回JSON格式的错误信息。
 */
@ControllerAdvice
@Controller
@RequestMapping(value = "/error")
public class QmExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(QmExceptionHandler.class);

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public String httpRequestMethodNotSupportedException(HttpServletResponse response, Exception e) {
        LOG.warn("Incorrect request, please verify the request `GET` and `POST` {}", e.getMessage());
        response.setStatus(200);
        return QmResult.sendJson(QmCode._405);
    }

    @ExceptionHandler(QmParamNullException.class)
    @ResponseBody
    public String qmParamNullException(HttpServletResponse response, Exception e) {
        LOG.warn("Some request parameters are missing, please verify the request parameters are correct! {}", e.getMessage());
        response.setStatus(200);
        return QmResult.paramNull();
    }

    @ExceptionHandler(QmParamErrorException.class)
    @ResponseBody
    public String qmParamErrorException(HttpServletResponse response, Exception e) {
        LOG.warn("Request parameters error, please verify the request parameters are correct! {}", e.getMessage());
        response.setStatus(200);
        return QmResult.paramFail();
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public String httpMediaTypeNotSupportedException(HttpServletResponse response, Exception e) {
        LOG.warn("An unsupported HTTP media type! {}", e.getMessage(), e);
        response.setStatus(200);
        return QmResult.sendJson(QmCode._415);
    }


    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public String notFoundPage404(HttpServletResponse response, Exception e) throws IOException {
        LOG.warn("The requested address is incorrect. Please verify that the requested address is correct! {}", e.getMessage());
        response.setStatus(200);
        return QmResult.sendJson(QmCode._404);
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String defaultException(HttpServletResponse response, Exception e) throws IOException {
        LOG.error("Server encountered an error, please check the problem!");
        LOG.error("defaultException {}", e.getMessage(), e);
        response.setStatus(200);
        return QmResult.error();
    }

}