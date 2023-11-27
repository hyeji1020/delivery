package com.study.sjicnho.delivery.food;

import com.study.sjicnho.delivery.food.Food;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class MapRepository {

    private Map<Integer, Food> db;
    private Integer id;

    public MapRepository() {
        this.db = new HashMap<>();
        this.id = 0;
    }



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
        db.put(food.getFoodId(), food);
        return food;
    }

    public Food getById(int id) {
        return db.get(id);
    }

    public Food update(int id, Food food) {

       return db.put(id, food);
    }

    public void delete(int id) {
        db.remove(id);
    }

}
