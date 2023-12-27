package com.study.sjicnho.delivery.food;

import com.study.sjicnho.delivery.store.Store;
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
    private Store store;
    private int foodQuantity;


    //DTO -> Entity
    public Food toEntity(){
        return Food.builder()
                .foodId(foodId)
                .foodName(foodName)
                .foodPrice(foodPrice)
                .foodQuantity(foodQuantity)
                .store(store)
                .build();
    }

    //Entity -> DTO
    public static FoodDto createFromEntity(Food food) {
        return FoodDto.builder()
                .foodId(food.getFoodId())
                .foodName(food.getFoodName())
                .foodPrice(food.getFoodPrice())
                .foodQuantity(food.getFoodQuantity())
                .store(food.getStore())
                .build();
    }


}
