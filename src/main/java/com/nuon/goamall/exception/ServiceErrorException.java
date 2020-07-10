package com.nuon.goamall.exception;

/**
 * 服务器异常
 */
public class ServiceErrorException extends HttpException {

    public ServiceErrorException(int code) {
        this.setCode(code);
        this.setHttpStatusCode(500);
    }
}
