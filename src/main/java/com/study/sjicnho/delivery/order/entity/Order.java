package com.study.sjicnho.delivery.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.sjicnho.delivery.payment.PaymentOption;
import com.study.sjicnho.delivery.store.entity.Store;
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

    @ManyToOne
    @JoinColumn(name = "store_id")
    @JsonIgnore
    private Store store;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "order_id")
//    @JsonIgnore
//    private List<OrderLine> orderLines;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderLine> orderLines;

    //총액
    private int totalAmount;

    @Enumerated(EnumType.STRING)
    private PaymentOption paymentOption;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private String deliveryAddress;

    @CreatedDate
    private String orderDate;

    public void approve() {
        changeOrderStatus(OrderStatus.ACCEPTED);
    }

    public void reject() {
        changeOrderStatus(OrderStatus.REJECTED);
    }

    public void cancel() {
        changeOrderStatus(OrderStatus.CANCELED);
    }

    private boolean isAccepting() {
        return orderStatus == OrderStatus.ACCEPTING;
    }

    private void changeOrderStatus(OrderStatus status) {
        if (isAccepting()) {
            orderStatus = status;
            return;
        }
        throw new IllegalStateException();
    }

    @PrePersist
    public void onPrePersist() {
        String orderFormatDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        this.orderDate = orderFormatDate;
    }

}

