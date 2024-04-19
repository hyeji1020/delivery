package com.study.sjicnho.delivery.food.exception;

import com.study.sjicnho.delivery.ErrorCode;

public class NoSuchFoodException extends BaseException{
    public NoSuchFoodException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
    }
}