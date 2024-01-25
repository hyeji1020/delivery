package com.study.sjicnho.delivery.order.service;

import com.study.sjicnho.delivery.food.entity.Food;
import com.study.sjicnho.delivery.food.repository.FoodJpaRepository;
import com.study.sjicnho.delivery.order.dto.OrderDto;
import com.study.sjicnho.delivery.order.entity.Order;
import com.study.sjicnho.delivery.order.repository.OrderRepository;
import com.study.sjicnho.delivery.store.Store;
import com.study.sjicnho.delivery.store.StoreRepository;
import com.study.sjicnho.delivery.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService{
    private final OrderRepository orderRepository;
    private final FoodJpaRepository foodRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    public OrderService(OrderRepository orderRepository, FoodJpaRepository foodRepository, UserRepository userRepository, StoreRepository storeRepository) {
        this.orderRepository = orderRepository;
        this.foodRepository = foodRepository;
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
    }

    public Order findById(Integer id){
       return orderRepository.findById(id).orElse(null);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public void save(OrderDto orderDto) {

        Integer storeId = orderDto.getStore().getStoreId();
        Store store = storeRepository.findById(storeId).orElse(null);

        Integer foodId = orderDto.getFood().getFoodId();
        Food food = foodRepository.findById(foodId).orElse(null);
        log.info("food:{}", food);

        // 사용자가 입력한 가게가 존재할 때
        if(store != null){
            //가게에 해당하는 음식 아이디 리스트
            List<Integer> foodIdList  = store.getFoods().stream()
                    .map(Food::getFoodId).collect(Collectors.toList());

            // 사용자가 입력한 음식아이디가 입력한 가게에 포함될 때
            if(foodIdList.contains(foodId) == true){
                orderDto.setFood(food);
                //DTO->Entity
                Order order = orderDto.toEntity();
                orderRepository.save(order);
            }else{
                System.out.println("입력한 음식이 가게에 없습니다.");
            }
        }
    }

}
