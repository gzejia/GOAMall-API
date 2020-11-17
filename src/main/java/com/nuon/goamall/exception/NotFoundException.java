package com.nuon.goamall.exception;

/**
 * 找不到异常
 */
public class NotFoundException extends HttpException {

    public NotFoundException(int code) {
        this.setCode(code);
        this.setHttpStatusCode(404);
    }
}
