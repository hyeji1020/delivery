package com.study.sjicnho.delivery.payment;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;


public enum PaymentOption {
    //현금, 카드
    CASH, CARD


}
