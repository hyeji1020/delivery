package com.study.sjicnho.delivery.store.exception;

import com.study.sjicnho.delivery.ErrorCode;
import com.study.sjicnho.delivery.food.exception.BaseException;

public class NoSuchStore extends BaseException {

    public NoSuchStore(ErrorCode errorCode) {
        super(errorCode.getMessage(), errorCode);
    }
}
