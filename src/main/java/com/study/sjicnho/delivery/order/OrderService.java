package com.study.sjicnho.delivery.order;

import com.study.sjicnho.delivery.food.Food;
import com.study.sjicnho.delivery.food.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final FoodRepository foodRepository;

    public OrderService(OrderRepository orderRepository, FoodRepository foodRepository) {
        this.orderRepository = orderRepository;
        this.foodRepository = foodRepository;
    }

    public Order findById(Integer id){
       return orderRepository.findById(id).orElse(null);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order save(OrderDto orderDto) {

        //DTO->Entity
        Order order = orderDto.toEntity();

        return orderRepository.save(order);
    }


}
