package com.study.delivery.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodController {

    @Autowired
    private FoodService foodService;

    // 음식 목록 조회
    @GetMapping("/foods")
    public List<FoodDto> getFoods(){
        return foodService.getFoods();
    }
    
    
    // 음식 상세 조회
    @GetMapping("/foods/{foodId}")
    public FoodDto getFoodById(@PathVariable Integer foodId){
        return foodService.getFoodById(foodId);
    }
    
    // 음식 등록
    @PostMapping("/api/foods")
    public Food createFood(@RequestBody FoodDto foodDto){
        return foodService.createFood(foodDto);
    }

    // 음식 수정
    @PutMapping("/api/foods/{foodId}")
    public void updateFood(@PathVariable Integer foodId, @RequestBody FoodDto foodDto){

        foodService.updateFood(foodId, foodDto);

    }

    // 음식 삭제
    @DeleteMapping("/api/foods/{foodId}")
    public void deleteFood(@PathVariable Integer foodId){
        foodService.deleteFood(foodId);
    }

}
