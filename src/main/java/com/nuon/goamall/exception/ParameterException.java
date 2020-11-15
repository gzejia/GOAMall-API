package com.nuon.goamall.exception;

/**
 * 参数异常
 */
public class ParameterException extends HttpException {

    public ParameterException(int code) {
        this.setCode(code);
        this.setHttpStatusCode(400);
    }
}
