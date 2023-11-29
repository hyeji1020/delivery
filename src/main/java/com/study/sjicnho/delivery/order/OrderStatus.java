package com.study.sjicnho.delivery.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;



public enum OrderStatus {
    
    // 주문완료, 주문취소
    ORDER, CANCEL 

}
