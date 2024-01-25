package com.study.sjicnho.delivery.food.controller;

import com.study.sjicnho.delivery.food.dto.FoodDto;
import com.study.sjicnho.delivery.food.entity.Food;
import com.study.sjicnho.delivery.food.service.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/foods")
public class FoodController {

        private final FoodService foodService;

        public FoodController(FoodService foodService) {
            this.foodService = foodService;
        }

        //음식 목록 조회
        @GetMapping
        public ResponseEntity<List<FoodDto>> getFoods(Food food){

            List<FoodDto> dtoList = foodService.getFoods(food);

            if (dtoList != null) {
                return ResponseEntity.ok().body(dtoList);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            }
        }


        //음식 상세 조회
        @GetMapping("/{foodId}")
        public ResponseEntity<FoodDto> getFoodById(@PathVariable Integer foodId){

            FoodDto foodDto = foodService.findById(foodId);
            log.info("foodDto={}", foodDto);

            if (foodDto != null) {
                return ResponseEntity.ok(foodDto);
            } else {
                return ResponseEntity.notFound().build();
            }

        }

        //음식 등록
        @PostMapping
        public ResponseEntity<Food> createFood(@RequestBody FoodDto foodDto){
            Food food = foodService.save(foodDto);

            return ResponseEntity.ok(food);
        }

        //음식 수정
        @PutMapping("/{foodId}")
        public ResponseEntity<Food> updateFood(@PathVariable Integer foodId, @RequestBody FoodDto foodDto){

            Food updated = foodService.updateFood(foodId, foodDto);

            return ResponseEntity.ok(updated);

        }

        //음식 삭제
        @DeleteMapping("/{foodId}")
        public ResponseEntity<Void> deleteFood(@PathVariable Integer foodId) {

            foodService.deleteFood(foodId);

            return new ResponseEntity<>(HttpStatus.OK);
        }
    }



