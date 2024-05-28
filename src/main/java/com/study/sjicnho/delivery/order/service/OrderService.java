package com.study.sjicnho.delivery.order.service;

import com.study.sjicnho.delivery.common.exception.ErrorCode;
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
import com.study.sjicnho.delivery.user.dto.ResponseUserDto;
import com.study.sjicnho.delivery.user.entity.User;
import com.study.sjicnho.delivery.user.exception.NoSuchUserException;
import com.study.sjicnho.delivery.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public OrderDto findOrderById(Integer id){
       Order order = orderRepository.findById(id)
               .orElseThrow(() -> new NoSuchOrderException(ErrorCode.ORDER_NOT_FOUND));

       return OrderDto.createFromEntity(order);
    }

    public List<OrderDto> getOrders() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(OrderDto::createFromEntity).collect(Collectors.toList());
    }

    public OrderDto save(OrderDto orderDto) {
        Store store = getStoreById(orderDto.getStore().getStoreId());
        User passwordUSer = getUserById(orderDto.getUser().getUserId());
        User user = ResponseUserDto.createFromEntity(passwordUSer).toEntity();
        List<OrderLine> orderLines = createOrderLines(orderDto.getOrderLines());

        int totalAmount = orderDto.calculateTotal();

        Order order = Order.builder()
                .user(user)
                .store(store)
                .orderLines(orderLines)
                .totalAmount(totalAmount)
                .orderStatus(ACCEPTING)
                .paymentOption(orderDto.getPaymentOption())
                .deliveryAddress(orderDto.getDeliveryAddress())
                .build();

        orderRepository.save(order);
        return OrderDto.createFromEntity(order);
    }

    private List<OrderLine> createOrderLines(List<OrderLineDto> orderLineDtos) {
        List<OrderLine> orderLines = new ArrayList<>();
        for (OrderLineDto orderLineDto : orderLineDtos) {
            Food food = getFoodById(orderLineDto.getFoodId());
            int unitPrice = food.getPrice();
            orderLineDto.setUnitPrice(unitPrice);
            int subtotal = orderLineDto.calculateSubtotal();

            orderLineDto.setSubTotal(subtotal);
            orderLines.add(orderLineDto.toEntity());
        }
        return orderLines;
    }

    public OrderStatus acceptOrder(Integer id) {
        Order order = getOrderById(id);
        order.approve();
        orderRepository.save(order);
        return order.getOrderStatus();
    }

    public OrderStatus cancelOrder(Integer id) {
        Order order = getOrderById(id);
        order.cancel();
        orderRepository.save(order);
        return order.getOrderStatus();
    }

    public OrderStatus rejectOrder(Integer id) {
        Order order = getOrderById(id);
        order.reject();
        orderRepository.save(order);
        return order.getOrderStatus();
    }

    private Store getStoreById(Integer id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new NoSuchStoreException(ErrorCode.STORE_NOT_FOUND));
    }

    private Order getOrderById(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchOrderException(ErrorCode.ORDER_NOT_FOUND));
    }

    private Food getFoodById(Integer id) {
        return foodRepository.findById(id)
                .orElseThrow(() -> new NoSuchFoodException(ErrorCode.FOOD_NOT_FOUND));
    }

    private User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchUserException(ErrorCode.USER_NOT_FOUND));
    }
}
