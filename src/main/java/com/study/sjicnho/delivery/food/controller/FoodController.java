package com.study.sjicnho.delivery.food.controller;

import com.study.sjicnho.delivery.food.domain.Food;
import com.study.sjicnho.delivery.food.dto.FoodDto;
import com.study.sjicnho.delivery.food.service.FoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
                return new ResponseEntity<>(dtoList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }


        //음식 상세 조회
        @GetMapping("/{foodId}")
        public ResponseEntity<FoodDto> getFoodById(@PathVariable Integer foodId){

            FoodDto foodDto = foodService.getFoodById(foodId);

            if (foodDto != null) {
                return new ResponseEntity<>(foodDto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }

        //음식 등록
        @PostMapping
        public ResponseEntity<Food> createFood(@RequestBody FoodDto foodDto){
            Food createdFood = foodService.createFood(foodDto);
            return new ResponseEntity<>(createdFood, HttpStatus.CREATED);
        }

        //음식 수정
        @PutMapping("/{foodId}")
        public ResponseEntity<Food> updateFood(@PathVariable Integer foodId, @RequestBody FoodDto foodDto){
            Food updated = foodService.updateFood(foodId, foodDto);

            if (updated != null) {
                return new ResponseEntity<>(updated, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        //음식 삭제
        @DeleteMapping("/{foodId}")
        public ResponseEntity<Void> deleteFood(@PathVariable Integer foodId){

            boolean deleted = foodService.deleteFood(foodId);

            if (deleted) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

    }



