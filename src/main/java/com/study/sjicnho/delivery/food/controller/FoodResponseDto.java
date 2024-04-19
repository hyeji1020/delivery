package com.study.sjicnho.delivery.food.controller;


import com.study.sjicnho.delivery.food.dto.FoodDto;
import com.study.sjicnho.delivery.food.entity.Food;
import com.study.sjicnho.delivery.store.entity.Store;
import lombok.Builder;
import lombok.Getter;


@Getter
public class FoodResponseDto {

    private Integer foodId;
    private String name;
    private int price;
    private String store;

    @Builder
    public FoodResponseDto(Food food) {
        this.foodId = food.getFoodId();
        this.name = food.getName();
        this.price = food.getPrice();
        this.store = food.getStore().getName();
    }

    //Entity -> DTO
    public static FoodDto FoodReponseDtoFromentity(Food food) {
        return FoodDto.builder()
                .foodId(food.getFoodId())
                .name(food.getName())
                .price(food.getPrice())
                .store(food.getStore())  // storeName 설정
                .build();
    }



}
