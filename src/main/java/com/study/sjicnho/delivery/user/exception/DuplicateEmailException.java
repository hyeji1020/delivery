package com.study.sjicnho.delivery.user.exception;

import com.study.sjicnho.delivery.common.exception.BaseException;
import com.study.sjicnho.delivery.common.exception.ErrorCode;

public class DuplicateEmailException extends BaseException {
    public DuplicateEmailException() {
        super(ErrorCode.DUPLICATE_EMAIL.getMessage(), ErrorCode.DUPLICATE_EMAIL);
    }
}
