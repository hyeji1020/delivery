package com.study.sjicnho.delivery.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
public enum ErrorCode {

    ORDER_NOT_FOUND(NOT_FOUND,"주문 정보를 찾을 수 없습니다."),
    USER_NOT_FOUND(NOT_FOUND, "사용자 정보를 찾을 수 없습니다."),
    STORE_NOT_FOUND(NOT_FOUND, "해당 음식점이 존재하지 않습니다."),
    FOOD_NOT_FOUND(NOT_FOUND, "해당 음식이 존재하지 않습니다.");


    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
