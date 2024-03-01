package com.chao.springboot.exception;

import com.chao.springboot.comm.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class MyExceptionHandler {

    /**
     * 如果抛出的的是ServiceException，则调用该方法
     * @param se 业务异常
     * @return Result
     */
    @ExceptionHandler(MyException.class)
    @ResponseBody
    public Result handle(MyException se){
        return Result.error(se.getCode(), se.getMessage());
    }
}
