package com.study.sjicnho.delivery.order.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


@Getter
public enum OrderStatus {
    //주문 수락,취소,거절
    ACCEPTING("accepting"),
    ACCEPTED("accepted"),
    CANCELED("canceled"),
    REJECTED("rejected");

    private final String status;

    OrderStatus(final String status) {
        this.status = status;
    }
}
