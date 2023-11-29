package com.study.sjicnho.delivery.order;

import com.study.sjicnho.delivery.food.Food;
import com.study.sjicnho.delivery.food.FoodRepository;
import com.study.sjicnho.delivery.food.FoodService;
import com.study.sjicnho.delivery.user.User;
import com.study.sjicnho.delivery.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final FoodService foodService;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, FoodService foodService, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.foodService = foodService;
        this.userRepository = userRepository;
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
