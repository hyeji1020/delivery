package com.study.sjicnho.delivery.order.service;

import com.study.sjicnho.delivery.food.entity.Food;
import com.study.sjicnho.delivery.food.repository.FoodJpaRepository;
import com.study.sjicnho.delivery.order.dto.OrderDto;
import com.study.sjicnho.delivery.order.dto.OrderLineDto;
import com.study.sjicnho.delivery.order.entity.Order;
import com.study.sjicnho.delivery.order.entity.OrderLine;
import com.study.sjicnho.delivery.order.entity.OrderStatus;
import com.study.sjicnho.delivery.order.repository.OrderRepository;
import com.study.sjicnho.delivery.store.entity.Store;
import com.study.sjicnho.delivery.store.repository.StoreRepository;
import com.study.sjicnho.delivery.user.entity.User;
import com.study.sjicnho.delivery.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import static com.study.sjicnho.delivery.order.entity.OrderStatus.ACCEPTING;

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

    public void save(OrderDto orderDto){

        // 해당 가게가 있을 때
        Store store  = storeRepository.findById(orderDto.getStore().getStoreId()).orElseThrow(()
                -> new NoSuchElementException("해당 가게가 없습니다."));

        if(store != null){
            List<OrderLine> orderLines = new ArrayList<>();

            for (OrderLineDto orderLineDto : orderDto.getOrderLines()) {
                Integer foodId = orderLineDto.getFoodId();
                log.info("foodId: {}", foodId);

                Food food = foodRepository.findById(foodId).orElseThrow(()
                        -> new NoSuchElementException("해당 음식이 없습니다."));

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

        }else{
            log.error("Store not found for storeId: {}", store);
        }

        int total = orderDto.calculateTotal();

        orderDto.setTotalAmount(total);

        orderDto.setOrderStatus(ACCEPTING);

        Order order = orderDto.toEntity();

        orderRepository.save(order);
    }

    public OrderStatus acceptOrder(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(()
                -> new NoSuchElementException("요청하신 주문을 찾을 수 없습니다."));

        order.approve();

        orderRepository.save(order);

        return order.getOrderStatus();
    }

    public OrderStatus cancelOrder(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(()
                -> new NoSuchElementException("요청하신 주문을 찾을 수 없습니다."));

        order.cancel();

        orderRepository.save(order);

        return order.getOrderStatus();
    }

    public OrderStatus rejectOrder(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(()
                -> new NoSuchElementException("요청하신 주문을 찾을 수 없습니다."));

        order.reject();

        orderRepository.save(order);

        return order.getOrderStatus();
    }
}
