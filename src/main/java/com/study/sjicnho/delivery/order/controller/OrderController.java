package com.study.sjicnho.delivery.order.controller;

import com.study.sjicnho.delivery.food.service.FoodService;
import com.study.sjicnho.delivery.order.entity.Order;
import com.study.sjicnho.delivery.order.dto.OrderDto;
import com.study.sjicnho.delivery.order.service.OrderService;
import org.springframework.http.ResponseEntity;
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

    //주문 조회
    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable Integer id){
        Order order = orderService.findById(id);
        return ResponseEntity.ok(order);
    }

    //주문 리스트 조회
    @GetMapping
    public ResponseEntity<List<Order>> getOrders(){
        List<Order> orders = orderService.getOrders();
        return ResponseEntity.ok(orders);
    }

    //주문 등록
    @PostMapping
    public ResponseEntity<Order> save(@Valid @RequestBody OrderDto orderDto){
        orderService.save(orderDto);
        return ResponseEntity.ok().build();
    }

//    @PatchMapping("/{id}/accept")
//    public ResponseEntity<Void> acceptOrder(@PathVariable Integer id){
//        orderService.acceptOrder();
//
//    }
//
//    @PatchMapping("/{id}/cancel")
//    public ResponseEntity<Void> cancelOrder(@PathVariable Integer id){
//        orderService.cancelOrder();
//    }
//
//    @PatchMapping("/{id}/reject")
//    public ResponseEntity<Void> rejectOrder(@PathVariable Integer id){
//        orderService.rejectOrder();
//    }

}
