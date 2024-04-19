package com.study.sjicnho.delivery.order.exception;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.study.sjicnho.delivery.BaseException;
import com.study.sjicnho.delivery.ErrorCode;

public class NoSuchOrderException extends BaseException {
    public NoSuchOrderException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
    }
}
