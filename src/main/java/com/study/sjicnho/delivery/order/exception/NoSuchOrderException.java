package com.study.sjicnho.delivery.order.exception;

import com.study.sjicnho.delivery.common.exception.BaseException;
import com.study.sjicnho.delivery.common.exception.ErrorCode;

public class NoSuchOrderException extends BaseException {
    public NoSuchOrderException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
    }
}
