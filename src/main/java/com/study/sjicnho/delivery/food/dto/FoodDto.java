package com.study.sjicnho.delivery.food.dto;

import com.study.sjicnho.delivery.food.domain.Food;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class FoodDto {

    private Integer foodId;
    private String foodName;
    private int foodPrice;

    @Builder
    public FoodDto(Integer foodId, String foodName, int foodPrice) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
    }

    //DTO -> Entity
    public Food toEntity(){
        return Food.builder()
                .foodId(foodId)
                .foodName(foodName)
                .foodPrice(foodPrice)
                .build();
    }

    //Entity -> DTO
    public static FoodDto createFromEntity(Food food) {
        return FoodDto.builder()
                .foodId(food.getFoodId())
                .foodName(food.getFoodName())
                .foodPrice(food.getFoodPrice())
                .build();
    }


}
