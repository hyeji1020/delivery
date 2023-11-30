package com.study.sjicnho.delivery.order;

import com.study.sjicnho.delivery.food.FoodService;
import com.study.sjicnho.delivery.store.Store;
import com.study.sjicnho.delivery.store.StoreRepository;
import com.study.sjicnho.delivery.user.User;
import com.study.sjicnho.delivery.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final FoodService foodService;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    public OrderService(OrderRepository orderRepository, FoodService foodService, UserRepository userRepository, StoreRepository storeRepository) {
        this.orderRepository = orderRepository;
        this.foodService = foodService;
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
    }

    public Order findById(Integer id){
       return orderRepository.findById(id).orElse(null);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order save(OrderDto orderDto) {

        log.info("orderDto.getUser()={}", orderDto.getUser());

        //DTO->Entity
        Order order = orderDto.toEntity();

        return orderRepository.save(order);
    }




}
