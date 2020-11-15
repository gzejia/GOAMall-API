package com.nuon.goamall.exception;

/**
 * 未登录异常
 */
public class NoAuthorizationException extends HttpException {

    public NoAuthorizationException(int code) {
        this.setCode(code);
        this.setHttpStatusCode(401);
    }
}
