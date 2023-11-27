package com.study.sjicnho.delivery.order;


import com.study.sjicnho.delivery.food.Food;
import com.study.sjicnho.delivery.food.FoodDto;
import com.study.sjicnho.delivery.payment.PaymentOption;
import com.study.sjicnho.delivery.store.Store;
import com.study.sjicnho.delivery.user.User;
import lombok.*;

import javax.persistence.NamedEntityGraph;
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
    private PaymentOption option;
    private OrderStatus status;
    private String deliveryAddress;
    private Date orderDate;


    //DTO -> Entity
    public Order toEntity(){
        return Order.builder()
                .orderId(orderId)
                .user(user)
                .store(store)
                .paymentAmount(paymentAmount)
                .option(option)
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
                .option(order.getOption())
                .status(order.getStatus())
                .deliveryAddress(order.getDeliveryAddress())
                .orderDate(order.getOrderDate())
                .build();
    }
}
