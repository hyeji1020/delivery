package com.study.sjicnho.delivery.food.exception;

import com.study.sjicnho.delivery.common.exception.BaseException;
import com.study.sjicnho.delivery.common.exception.ErrorCode;

public class NoSuchFoodException extends BaseException {
    public NoSuchFoodException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
    }
}
