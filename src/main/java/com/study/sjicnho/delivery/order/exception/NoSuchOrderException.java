package com.study.sjicnho.delivery.order.exception;

import com.study.sjicnho.delivery.exception.BaseException;
import com.study.sjicnho.delivery.exception.ErrorCode;

public class NoSuchOrderException extends BaseException {
    public NoSuchOrderException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
    }
}
