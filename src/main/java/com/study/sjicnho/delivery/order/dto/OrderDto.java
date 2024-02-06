package com.study.sjicnho.delivery.order.dto;

import com.study.sjicnho.delivery.order.OrderStatus;
import com.study.sjicnho.delivery.order.entity.Order;
import com.study.sjicnho.delivery.payment.PaymentOption;
import com.study.sjicnho.delivery.user.entity.User;
import lombok.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OrderDto {

    private User user;
    private List<OrderLineDto> orderLines;

    @NotNull
    private int totalAmount;

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

    public Order toEntity() {
        return new Order(
                user,
                orderLines.stream().map(OrderLineDto::toEntity).collect(Collectors.toList()),
                totalAmount,
                paymentOption,
                status,
                deliveryAddress,
                orderDate
        );
    }

    // Entity -> DTO
    public static OrderDto createFromEntity(Order order) {
        return OrderDto.builder()
                .user(order.getUser())
                .totalAmount(order.getTotalAmount())
                .paymentOption(order.getPaymentOption())
                .status(order.getStatus())
                .deliveryAddress(order.getDeliveryAddress())
                .orderDate(order.getOrderDate())
                .orderLines(order.getOrderLines().stream()
                        .map(OrderLineDto::createFromEntity) // OrderLine 엔터티를 OrderLineDto로 변환
                        .collect(Collectors.toList()))
                .build();
    }

    public int calculateTotal() {
        int total = 0;

        for (OrderLineDto orderLineDto : orderLines) {
            total += orderLineDto.calculateSubtotal();
        }
        return total;
    }

}
