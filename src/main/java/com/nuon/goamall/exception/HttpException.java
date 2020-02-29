package com.nuon.goamall.exception;

/**
 * 网络异常
 */
public class HttpException extends RuntimeException {

    private int code;

    private int httpStatusCode = 500;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }
}
