package com.study.sjicnho.delivery.exception;

public class BaseException extends RuntimeException{

    public final ErrorCode errorCode;

    public BaseException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }

}
