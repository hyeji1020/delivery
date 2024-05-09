package com.study.sjicnho.delivery.food.dto;

import com.study.sjicnho.delivery.food.entity.Food;
import com.study.sjicnho.delivery.store.dto.StoreDto;
import com.study.sjicnho.delivery.store.entity.Store;
import lombok.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class FoodDto {

    private Integer foodId;

    @NotBlank(message = "음식명은 필수 입력 값입니다.")
    private String name;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    @Min(0)
    private int price;

    private Store store;

    //Entity -> DTO
    public static FoodDto createFromEntity(Food food) {
        return FoodDto.builder()
                .foodId(food.getFoodId())
                .name(food.getName())
                .price(food.getPrice())
                .store(food.getStore())
                .build();
    }

    //DTO -> Entity
    public Food toEntity(){
        return Food.builder()
                .foodId(foodId)
                .name(name)
                .price(price)
                .store(store)
                .build();
    }

}
