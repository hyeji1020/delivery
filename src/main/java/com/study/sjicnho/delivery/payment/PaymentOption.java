package com.study.sjicnho.delivery.payment;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="payment_option")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentOption {

    @Id
    private Integer optionId;
    private String option;

}
