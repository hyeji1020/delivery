package com.study.sjicnho.delivery.food.repository;

import com.study.sjicnho.delivery.food.entity.Food;
import lombok.extern.slf4j.Slf4j;

import java.util.*;



@Slf4j
public class MapRepository implements FoodRepository{

    private Map<Integer, Food> db;
    private Integer id;

    public MapRepository() {
        this.db = new HashMap<>();
        this.id = 0;
    }


    public List<Food> getFoods(Food food) {

        List<Food> foodList = new ArrayList<>();

        for (Food item : db.values()) {
            if (item.getName().equals(food.getName())) {
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
