package com.study.sjicnho.delivery.order;


import com.study.sjicnho.delivery.food.FoodService;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable Integer id){
        Order order = orderService.findById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getOrders(){
        List<Order> orders = orderService.getOrders();
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<Order> save(@RequestBody OrderDto orderDto){
        Order saved = orderService.save(orderDto);
        return ResponseEntity.ok(saved);
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
