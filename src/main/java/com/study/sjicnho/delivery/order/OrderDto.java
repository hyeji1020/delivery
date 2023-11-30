package com.study.sjicnho.delivery.order;


import com.study.sjicnho.delivery.store.Store;
import com.study.sjicnho.delivery.payment.PaymentOption;

import com.study.sjicnho.delivery.user.User;
import lombok.*;

import javax.jws.soap.SOAPBinding;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OrderDto {
    private Integer orderId;
    private User user;
    private Store store;
    private int paymentAmount;
    private PaymentOption paymentOption;
    private OrderStatus status;
    private String deliveryAddress;
    private String orderDate;

    public User usermake(){
        Integer id = user.getUserId();
        return User.builder()
                .userId(id)
                .build();
    }


    //DTO -> Entity
    public Order toEntity(){
        return Order.builder()
                .orderId(orderId)
                .user(user)
                .store(store)
                .paymentAmount(paymentAmount)
                .paymentOption(paymentOption)
                .status(status)
                .deliveryAddress(deliveryAddress)
                .orderDate(orderDate)
                .build();
    }

    //Entity -> DTO
    public static OrderDto createFromEntity(Order order) {
        return OrderDto.builder()
                .orderId(order.getOrderId())
                .user(order.getUser())
                .store(order.getStore())
                .paymentAmount(order.getPaymentAmount())
                .paymentOption(order.getPaymentOption())
                .status(order.getStatus())
                .deliveryAddress(order.getDeliveryAddress())
                .orderDate(order.getOrderDate())
                .build();
    }
}
