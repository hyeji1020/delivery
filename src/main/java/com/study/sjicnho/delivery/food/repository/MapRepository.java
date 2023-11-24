package com.study.sjicnho.delivery.food.repository;

import com.study.sjicnho.delivery.food.domain.Food;
import com.study.sjicnho.delivery.food.dto.FoodDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MapRepository {

    private Map<Integer, Food> db = new HashMap<>();

    public List<Food> getFoods(Food food) {

        List<Food> foodList = new ArrayList<>();

        for (Food item : db.values()) {
            if (item.getFoodName().equals(food.getFoodName())) {
                foodList.add(item);
            }
        }
        return foodList;
    }

    public Food create(Food food) {
        return db.put(food.getFoodId(), food);
    }

    public Food getById(int id) {
        return db.get(id);
    }

    public Food update(Food food) {
       return db.put(food.getFoodId(), food);
    }

    public void delete(int id) {
        db.remove(id);
    }

}
