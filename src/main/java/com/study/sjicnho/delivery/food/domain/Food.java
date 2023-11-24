package com.study.sjicnho.delivery.food.domain;

import com.study.sjicnho.delivery.food.dto.FoodDto;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor

@Entity
@Table(name = "food")
public class Food {

    @Id
    @Column(name="food_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer foodId;
    @Column
    private String foodName;
    @Column
    private int foodPrice;

    @Builder
    public Food(Integer foodId, String foodName, int foodPrice) {
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodPrice = foodPrice;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getFoodPrice() {
        return foodPrice;
    }

}
