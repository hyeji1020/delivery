package com.study.sjicnho.service;

import com.study.sjicnho.delivery.food.entity.Food;
import com.study.sjicnho.delivery.food.repository.FoodJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FoodServiceTest {

    @Autowired
    private FoodJpaRepository foodJpaRepository;

    @Test
    @DisplayName("음식, 가게 등록하기")
    void saveTest() {

        // Given
        Integer id = 1;
        String name = "마라엽떡";
        int price = 18000;


        // When
        Food food = Food.builder()
                .foodId(id)
                .name(name)
                .price(price)
                .build();

        // Save the entity using the repository
        Food savedFood = foodJpaRepository.save(food);

        // Then
        assertEquals(name, savedFood.getName());
        assertEquals(price, savedFood.getPrice());

    }
}
