package com.study.sjicnho.delivery.store.exception;

import com.study.sjicnho.delivery.common.exception.ErrorCode;
import com.study.sjicnho.delivery.common.exception.BaseException;

public class NoSuchStoreException extends BaseException {

    public NoSuchStoreException(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
    }
}
