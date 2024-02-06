package com.study.sjicnho.delivery.order.dto;

import com.study.sjicnho.delivery.food.entity.Food;
import com.study.sjicnho.delivery.order.entity.Order;
import com.study.sjicnho.delivery.order.entity.OrderLine;
import com.study.sjicnho.delivery.payment.PaymentOption;
import lombok.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class OrderLineDto {

    private Integer foodId;

    @Min(value = 1, message = "주문 수량은 최소 1개 이상 이어야 합니다.")
    private int quantity;

    private int unitPrice;
    private int subTotal;

    // DTO -> Entity
    public OrderLine toEntity() {
        return OrderLine.builder()
                .food(Food.builder().foodId(foodId).build())
                .quantity(quantity)
                .unitPrice(unitPrice)
                .subTotal(subTotal)
                .build();
    }

    //Entity -> DTO
    public static OrderLineDto createFromEntity(OrderLine orderLine){
        return OrderLineDto.builder()
                .foodId(orderLine.getFood().getFoodId())
                .quantity(orderLine.getQuantity())
                .unitPrice(orderLine.getUnitPrice())
                .subTotal(orderLine.getSubTotal())
                .build();
    }

    // 소계 계산 메서드
    public int calculateSubtotal() {
        return this.quantity * this.unitPrice;
    }
}
