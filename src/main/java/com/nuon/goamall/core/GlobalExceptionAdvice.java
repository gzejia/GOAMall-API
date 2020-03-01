package com.nuon.goamall.core;

import com.nuon.goamall.core.configuration.ExceptionCodeConfiguration;
import com.nuon.goamall.exception.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;

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

    /**
     * Http访问错误日志
     */
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

    /**
     * 请求Body参数错误日志
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public UnifyResponse ExceptionHandle(HttpServletRequest req, MethodArgumentNotValidException e) {
        List<ObjectError> errList = e.getBindingResult().getAllErrors();
        String errMsg = formatNotValidErrMsg(errList);
        return new UnifyResponse(10001, errMsg, req.getMethod() + " " + req.getRequestURI());
    }

    /**
     * @param list 错误日志数据
     * @return 获取拼接错误提示信息
     */
    public String formatNotValidErrMsg(List<ObjectError> list) {
        StringBuilder sb = new StringBuilder();
        list.forEach(err -> {
            sb.append(err.getDefaultMessage()).append(";");
        });
        return sb.toString().substring(0, sb.length() - 1);
    }

    /**
     * 请求URL参数错误日志
     */
    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public UnifyResponse ExceptionHandle(HttpServletRequest req, ConstraintViolationException e) {
        StringBuilder errMsgSb = new StringBuilder();
        e.getConstraintViolations().forEach(cv -> {
            errMsgSb.append(cv.getMessage()).append(";");
        });
        String errMsg = errMsgSb.toString().substring(0, errMsgSb.length() - 1);
        return new UnifyResponse(10001, errMsg, req.getMethod() + " " + req.getRequestURI());
    }
}
