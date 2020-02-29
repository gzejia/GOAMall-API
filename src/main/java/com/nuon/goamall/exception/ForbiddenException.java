package com.nuon.goamall.exception;

/**
 * 权限异常
 */
public class ForbiddenException extends HttpException {

    public ForbiddenException(int code) {
        this.setCode(code);
        this.setHttpStatusCode(403);
    }
}
