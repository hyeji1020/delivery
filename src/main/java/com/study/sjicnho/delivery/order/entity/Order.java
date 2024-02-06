package com.study.sjicnho.delivery.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.sjicnho.delivery.order.OrderStatus;
import com.study.sjicnho.delivery.payment.PaymentOption;
import com.study.sjicnho.delivery.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
//    private List<OrderLine> orderLines = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")  // OrderLine 엔터티의 order_id 외래 키 지정
    private List<OrderLine> orderLines;

    //총액
    private int totalAmount;

    @Enumerated(EnumType.STRING)
    private PaymentOption paymentOption;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String deliveryAddress;

    //    @Temporal(TemporalType.DATE)
    @CreatedDate
    private String orderDate;


    // 생성자
    public Order(User user, List<OrderLine> orderLines, int totalAmount, PaymentOption paymentOption,
                 OrderStatus status, String deliveryAddress, String orderDate) {
        this.user = user;
        this.orderLines = orderLines;
        this.totalAmount = totalAmount;
        this.paymentOption = paymentOption;
        this.status = status;
        this.deliveryAddress = deliveryAddress;
        this.orderDate = orderDate;
    }

    @PrePersist
    public void onPrePersist() {
        String orderFormatDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.orderDate = orderFormatDate;
    }

}

