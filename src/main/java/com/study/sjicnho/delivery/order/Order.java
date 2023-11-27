package com.study.sjicnho.delivery.order;

import com.study.sjicnho.delivery.payment.PaymentOption;
import com.study.sjicnho.delivery.store.Store;
import com.study.sjicnho.delivery.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;

@Entity
@Table(name="orders")
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @ManyToOne
    @JoinColumn(name="storeId")
    private Store store;

    private int paymentAmount;

    @ManyToOne
    @JoinColumn(name="optionId")
    private PaymentOption option;

    @ManyToOne
    @JoinColumn(name="statusId")
    private OrderStatus status;
    private String deliveryAddress;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
}
