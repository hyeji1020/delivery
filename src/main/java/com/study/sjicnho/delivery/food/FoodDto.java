package com.study.sjicnho.delivery.food;

import com.study.sjicnho.delivery.store.Store;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FoodDto {

    private Integer foodId;

    @NotNull
    private String name;

    @NotNull(message = "전화번호는 필수 입력 값입니다.")
    @Min(0)
    private int price;

    private Store store;


    //DTO -> Entity
    public Food toEntity(){
        return Food.builder()
                .foodId(foodId)
                .name(name)
                .price(price)
                .store(store)
                .build();
    }

    //Entity -> DTO
    public static FoodDto createFromEntity(Food food) {
        return FoodDto.builder()
                .foodId(food.getFoodId())
                .name(food.getName())
                .price(food.getPrice())
                .store(food.getStore())
                .build();
    }
    // BeanUtils.copyProperties()
}
