package com.study.sjicnho.delivery.order.service;

import com.study.sjicnho.delivery.food.entity.Food;
import com.study.sjicnho.delivery.food.repository.FoodJpaRepository;
import com.study.sjicnho.delivery.order.dto.OrderDto;
import com.study.sjicnho.delivery.order.dto.OrderLineDto;
import com.study.sjicnho.delivery.order.entity.Order;
import com.study.sjicnho.delivery.order.entity.OrderLine;
import com.study.sjicnho.delivery.order.repository.OrderLineRepository;
import com.study.sjicnho.delivery.order.repository.OrderRepository;
import com.study.sjicnho.delivery.store.repository.StoreRepository;
import com.study.sjicnho.delivery.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OrderService{
    private final OrderRepository orderRepository;
    private final FoodJpaRepository foodRepository;
    private final UserRepository userRepository;
    private final OrderLineRepository orderLineRepository;

    public OrderService(OrderRepository orderRepository, FoodJpaRepository foodRepository, UserRepository userRepository, OrderLineRepository orderLineRepository) {
        this.orderRepository = orderRepository;
        this.foodRepository = foodRepository;
        this.userRepository = userRepository;
        this.orderLineRepository = orderLineRepository;
    }


    public Order findById(Integer id){
       return orderRepository.findById(id).orElse(null);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public void save(OrderDto orderDto){
        List<OrderLine> orderLines = new ArrayList<>();

        for (OrderLineDto orderLineDto : orderDto.getOrderLines()) {
            Integer foodId = orderLineDto.getFoodId();
            log.info("foodId: {}", foodId);

            Food food = foodRepository.findById(foodId).orElse(null);

            if (food == null) {
                log.error("Food not found for foodId: {}", foodId);
                //throw new FoodNotFoundException("Food not found for foodId: " + foodId);
            }

            log.info("food: {}", food);

            // 가격 확인
            int unitPrice = food.getPrice();
            orderLineDto.setUnitPrice(unitPrice);
            log.info("unitPrice: {}", unitPrice);

            // 주문 항목 소계 계산
            int subtotal = orderLineDto.calculateSubtotal();
            orderLineDto.setSubTotal(subtotal);

            // OrderLine 엔터티 생성 및 리스트에 추가
            orderLines.add(orderLineDto.toEntity());
        }

        int total = orderDto.calculateTotal();
        orderDto.setTotalAmount(total);
        orderRepository.save(orderDto.toEntity());
    }

}
