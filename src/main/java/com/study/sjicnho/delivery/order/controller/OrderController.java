package com.study.sjicnho.delivery.order.controller;

import com.study.sjicnho.delivery.food.service.FoodService;
import com.study.sjicnho.delivery.order.entity.Order;
import com.study.sjicnho.delivery.order.dto.OrderDto;
import com.study.sjicnho.delivery.order.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final FoodService foodService;

    public OrderController(OrderService orderService, FoodService foodService) {
        this.orderService = orderService;
        this.foodService = foodService;
    }

    // 주문 조회
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'OWNER')")
    public ResponseEntity<Order> findById(@PathVariable Integer id){
        Order order = orderService.findById(id);
        return ResponseEntity.ok().body(order);
    }

    // 주문 리스트 조회
    @GetMapping
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'OWNER')")
    public ResponseEntity<List<Order>> getOrders(){
        List<Order> orders = orderService.getOrders();
        return ResponseEntity.ok().body(orders);
    }

    // 주문 등록
    @PostMapping
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public ResponseEntity<Order> save(@Valid @RequestBody OrderDto orderDto){
        orderService.save(orderDto);
        return ResponseEntity.ok().build();
    }

    //주문 수락
    @PatchMapping("/{id}/accept")
    @PreAuthorize("hasAnyAuthority('OWNER')")
    public ResponseEntity<Void> acceptOrder(@PathVariable Integer id){
        orderService.acceptOrder(id);
        return ResponseEntity.ok().build();
    }

    //주문 취소
    @PatchMapping("/{id}/cancel")
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public ResponseEntity<Void> cancelOrder(@PathVariable Integer id){
        orderService.cancelOrder(id);
        return ResponseEntity.ok().build();
    }

    //주문 거절
    @PatchMapping("/{id}/reject")
    @PreAuthorize("hasAnyAuthority('OWNER')")
    public ResponseEntity<Void> rejectOrder(@PathVariable Integer id){
        orderService.rejectOrder(id);
        return ResponseEntity.ok().build();
    }

}
