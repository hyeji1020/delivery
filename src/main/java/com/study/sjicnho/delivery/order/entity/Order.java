package com.study.sjicnho.delivery.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.sjicnho.delivery.food.entity.Food;
import com.study.sjicnho.delivery.order.OrderStatus;
import com.study.sjicnho.delivery.store.Store;
import com.study.sjicnho.delivery.payment.PaymentOption;
import com.study.sjicnho.delivery.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Table(name="orders")
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name="store_id")
    @JsonIgnore
    private Store store;

    //결제 총 금액
    private int paymentAmount;

    //주문 수량
    private int quantity;

    @Enumerated(EnumType.STRING)
    private PaymentOption paymentOption;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String deliveryAddress;

//    @Temporal(TemporalType.DATE)
    @CreatedDate
    private String orderDate;

    @PrePersist
    public void onPrePersist(){
        String orderFormatDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.orderDate = orderFormatDate;
    }

}
