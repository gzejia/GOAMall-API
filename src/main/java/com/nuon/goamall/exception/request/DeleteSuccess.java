package com.nuon.goamall.exception.request;

import com.nuon.goamall.exception.HttpException;

public class DeleteSuccess extends HttpException {

    public DeleteSuccess(int code){
        this.setHttpStatusCode(code);
        this.setCode(200);
    }
}
