package com.study.sjicnho.delivery.order.dto;

import com.study.sjicnho.delivery.food.entity.Food;
import com.study.sjicnho.delivery.order.OrderStatus;
import com.study.sjicnho.delivery.order.entity.Order;
import com.study.sjicnho.delivery.store.Store;
import com.study.sjicnho.delivery.payment.PaymentOption;
import com.study.sjicnho.delivery.user.entity.User;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OrderDto {

    private Integer orderId;
    private User user;
    private Store store;

    @NotNull
    private int paymentAmount;

    @Min(value = 1, message = "주문 수량은 최소 1개 이상이어야 합니다.")
    private int quantity;

    private PaymentOption paymentOption;

    private OrderStatus status;

    @NotNull(message = "주소는 필수 입력 값입니다.")
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
                .quantity(quantity)
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
                .quantity(order.getQuantity())
                .paymentAmount(order.getPaymentAmount())
                .paymentOption(order.getPaymentOption())
                .status(order.getStatus())
                .deliveryAddress(order.getDeliveryAddress())
                .orderDate(order.getOrderDate())
                .build();
    }
}
