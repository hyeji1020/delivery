package com.study.sjicnho.delivery.food;

import lombok.*;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FoodDto {

    private Integer foodId;
    private String foodName;
    private int foodPrice;


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
