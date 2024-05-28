package com.study.sjicnho.delivery.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.sjicnho.delivery.food.entity.Food;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orderline")
@AllArgsConstructor
public class OrderLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderLineId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food; // 주문한 음식

    private int quantity;

    //Food.price가 변동되어도 Order.subTotal에 영향을 주지 않기 위해
    private int unitPrice;

    private int subTotal;

}
