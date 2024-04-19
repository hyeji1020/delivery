package com.study.sjicnho.delivery.user.exception;

import com.study.sjicnho.delivery.ErrorCode;
import com.study.sjicnho.delivery.BaseException;

public class NoSuchUserException extends BaseException {
    public NoSuchUserException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
    }
}
