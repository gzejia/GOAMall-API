package com.nuon.goamall.exception.request;

import com.nuon.goamall.exception.HttpException;

public class CreateSuccess extends HttpException {

    public CreateSuccess(int code) {
        this.setHttpStatusCode(code);
        this.setCode(201);
    }
}
