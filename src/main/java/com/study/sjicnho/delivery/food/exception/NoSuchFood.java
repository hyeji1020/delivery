package com.study.sjicnho.delivery.food.exception;

import com.study.sjicnho.delivery.ErrorCode;

public class NoSuchFood extends BaseException{
    public NoSuchFood(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
    }
}
