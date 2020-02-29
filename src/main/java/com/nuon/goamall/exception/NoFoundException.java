package com.nuon.goamall.exception;

/**
 * 找不到异常
 */
public class NoFoundException extends HttpException {

    public NoFoundException(int code) {
        this.setCode(code);
        this.setHttpStatusCode(404);
    }
}
