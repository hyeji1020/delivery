package com.study.sjicnho.delivery.order.dto;

import com.study.sjicnho.delivery.order.entity.OrderStatus;
import com.study.sjicnho.delivery.order.entity.Order;
import com.study.sjicnho.delivery.payment.PaymentOption;
import com.study.sjicnho.delivery.store.entity.Store;
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
    private Store store;

    @NotNull
    private int totalAmount;

    private PaymentOption paymentOption;

    private OrderStatus orderStatus;

    @NotNull(message = "주소는 필수 입력 값입니다.")
    private String deliveryAddress;

    private String orderDate;


//    public Order toEntity() {
//        return new Order(
//                user,
//                orderLines.stream().map(OrderLineDto::toEntity).collect(Collectors.toList()),
//                store,
//                totalAmount,
//                paymentOption,
//                orderStatus,
//                deliveryAddress,
//                orderDate
//        );
//    }

    public Order toEntity(){
        return Order.builder()
                .user(user)
                .orderLines(orderLines.stream().map(OrderLineDto::toEntity).collect(Collectors.toList()))
                .store(store)
                .totalAmount(totalAmount)
                .paymentOption(paymentOption)
                .orderStatus(orderStatus)
                .deliveryAddress(deliveryAddress)
                .orderDate(orderDate)
                .build();
    };

    // Entity -> DTO
    public static OrderDto createFromEntity(Order order) {
        return OrderDto.builder()
                .user(order.getUser())
                .totalAmount(order.getTotalAmount())
                .paymentOption(order.getPaymentOption())
                .orderStatus(order.getOrderStatus())
                .deliveryAddress(order.getDeliveryAddress())
                .orderDate(order.getOrderDate())
                .store(order.getStore())
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
