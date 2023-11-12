package com.study.delivery.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FoodController {

    @Autowired
    private FoodService foodService;

    // 음식 목록 조회
    @GetMapping("/foods")
    public void getFoods(){
        foodService.getFoods();
    }
    
    
    // 음식 상세 조회
    @GetMapping("/foods/{foodId}")
    public Food getFoodById(@PathVariable Integer foodId){
        return foodService.getFoodById(foodId);
    }
    
    // 음식 등록
    @PostMapping("/api/foods")
    public void createFood(Food food){
        foodService.createFood(food);
    }

    // 음식 수정
    @PutMapping("/api/foods/{foodId}")
    public void updateFood(@PathVariable Integer foodId, Food food){
        foodService.updateFood(food);
    }

    // 음식 삭제
    @DeleteMapping("/api/foods/{foodId}")
    public void deleteFood(@PathVariable Integer foodId){
        foodService.deleteFood(foodId);
    }

}
