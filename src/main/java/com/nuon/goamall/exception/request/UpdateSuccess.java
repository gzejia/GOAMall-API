package com.nuon.goamall.exception.request;

import com.nuon.goamall.exception.HttpException;

public class UpdateSuccess extends HttpException {

    public UpdateSuccess(int code) {
        this.setHttpStatusCode(code);
        this.setCode(200);
    }
}
