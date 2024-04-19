package com.study.sjicnho.delivery.food.exception;

import com.study.sjicnho.delivery.ErrorCode;

public class BaseException extends RuntimeException{

    public final ErrorCode errorCode;

    public BaseException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }

}
