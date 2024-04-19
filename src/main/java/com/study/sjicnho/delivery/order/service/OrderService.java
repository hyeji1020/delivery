package com.study.sjicnho.delivery.order.service;

import com.study.sjicnho.delivery.ErrorCode;
import com.study.sjicnho.delivery.food.entity.Food;
import com.study.sjicnho.delivery.food.exception.NoSuchFoodException;
import com.study.sjicnho.delivery.food.repository.FoodJpaRepository;
import com.study.sjicnho.delivery.order.dto.OrderDto;
import com.study.sjicnho.delivery.order.dto.OrderLineDto;
import com.study.sjicnho.delivery.order.entity.Order;
import com.study.sjicnho.delivery.order.entity.OrderLine;
import com.study.sjicnho.delivery.order.entity.OrderStatus;
import com.study.sjicnho.delivery.order.exception.NoSuchOrderException;
import com.study.sjicnho.delivery.order.repository.OrderRepository;
import com.study.sjicnho.delivery.store.entity.Store;
import com.study.sjicnho.delivery.store.exception.NoSuchStoreException;
import com.study.sjicnho.delivery.store.repository.StoreRepository;
import com.study.sjicnho.delivery.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
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

    public OrderDto findById(Integer id){
       Order order = orderRepository.findById(id)
               .orElseThrow(() -> new NoSuchOrderException(ErrorCode.ORDER_NOT_FOUND));

       return OrderDto.createFromEntity(order);
    }

    public List<OrderDto> getOrders() {
        //전체 데이터 가져오기
        List<Order> orders = orderRepository.findAll();

        List<OrderDto> dtos = new ArrayList<OrderDto>();

        //Entity->DTO
        for(int i = 0; i< orders.size(); i++){
            Order target = orders.get(i);
            OrderDto dto = OrderDto.createFromEntity(target);
            dtos.add(dto);
        }

        if(dtos != null){
            return dtos;
        }else{
            new NoSuchOrderException(ErrorCode.ORDER_NOT_FOUND);
        }
        return dtos;
    }

    public void save(OrderDto orderDto){

        // 해당 가게가 있을 때
        Store store  = storeRepository.findById(orderDto.getStore().getStoreId()).orElseThrow(()
                -> new NoSuchStoreException(ErrorCode.STORE_NOT_FOUND));

        if(store != null){
            List<OrderLine> orderLines = new ArrayList<>();

            for (OrderLineDto orderLineDto : orderDto.getOrderLines()) {
                Integer foodId = orderLineDto.getFoodId();

                Food food = foodRepository.findById(foodId).orElseThrow(()
                        -> new NoSuchFoodException(ErrorCode.FOOD_NOT_FOUND));

                // 가격 확인
                int unitPrice = food.getPrice();
                orderLineDto.setUnitPrice(unitPrice);

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
                -> new NoSuchOrderException(ErrorCode.ORDER_NOT_FOUND));

        order.approve();
        orderRepository.save(order);

        return order.getOrderStatus();
    }

    public OrderStatus cancelOrder(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(()
                -> new NoSuchOrderException(ErrorCode.ORDER_NOT_FOUND));

        order.cancel();
        orderRepository.save(order);

        return order.getOrderStatus();
    }

    public OrderStatus rejectOrder(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(()
                -> new NoSuchOrderException(ErrorCode.ORDER_NOT_FOUND));

        order.reject();
        orderRepository.save(order);

        return order.getOrderStatus();
    }
}
