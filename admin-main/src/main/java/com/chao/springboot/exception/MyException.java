package com.chao.springboot.exception;

import lombok.Getter;


@Getter
public class MyException extends RuntimeException {

    private String code;

    public MyException(String code, String msg) {
        super(msg);
        this.code = code;
    }
}
