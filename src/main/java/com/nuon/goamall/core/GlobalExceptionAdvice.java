package com.nuon.goamall.core;

import com.nuon.goamall.core.configuration.ExceptionCodeConfiguration;
import com.nuon.goamall.exception.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常拦截处理
 */
@ControllerAdvice
public class GlobalExceptionAdvice {

    @Autowired
    private ExceptionCodeConfiguration codeConfiguration;

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public UnifyResponse ExceptionHandle(HttpServletRequest req, Exception e) {
        return new UnifyResponse(999, "服务器异常", req.getMethod() + " " + req.getRequestURI());
    }

    @ResponseBody
    @ExceptionHandler(value = HttpException.class)
    public ResponseEntity<UnifyResponse> HttpExceptionHandle(HttpServletRequest req, HttpException e) {
        // 请求方式和地址
        String requestStr = req.getMethod() + " " + req.getRequestURI();
        // 错误原因
        String errMsg = codeConfiguration.getMessage(e.getCode());
        // 返回结果数据
        UnifyResponse unifyResponse = new UnifyResponse(e.getCode(), errMsg, requestStr);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpStatus httpStatus = HttpStatus.resolve(e.getHttpStatusCode());

        return new ResponseEntity<>(unifyResponse, httpHeaders, httpStatus);
    }
}
