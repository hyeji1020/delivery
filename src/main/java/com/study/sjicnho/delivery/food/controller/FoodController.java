package com.study.sjicnho.delivery.food.controller;

import com.study.sjicnho.delivery.food.dto.FoodDto;
import com.study.sjicnho.delivery.food.entity.Food;
import com.study.sjicnho.delivery.food.service.FoodService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
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
        @PreAuthorize("hasAnyAuthority('CUSTOMER', 'OWNER')")
        public ResponseEntity<List<FoodDto>> getFoods(){

            List<FoodDto> dtoList = foodService.getFoods();
            return ResponseEntity.ok(dtoList);

        }

        //음식 상세 조회
        @GetMapping("/{foodId}")
        @PreAuthorize("hasAnyAuthority('CUSTOMER', 'OWNER')")
        public ResponseEntity<FoodDto> getFoodById(@PathVariable Integer foodId){

            FoodDto foodDto = foodService.findById(foodId);
            return ResponseEntity.ok(foodDto);

        }

        //음식 등록
        @PostMapping
        @PreAuthorize("hasAnyAuthority('OWNER')")
        public ResponseEntity<Food> createFood(@Valid @RequestBody FoodDto foodDto){

            Food food = foodService.save(foodDto);
            return ResponseEntity.ok(food);
        }

        //음식 수정
        @PutMapping("/{foodId}")
        @PreAuthorize("hasAnyAuthority('OWNER')")
        public ResponseEntity<Food> updateFood( @PathVariable Integer foodId, @Valid @RequestBody FoodDto foodDto){

            Food updated = foodService.updateFood(foodId, foodDto);
            return ResponseEntity.ok(updated);

        }

        //음식 삭제
        @DeleteMapping("/{foodId}")
        @PreAuthorize("hasAnyAuthority('OWNER')")
        public ResponseEntity<Void> deleteFood(@PathVariable Integer foodId) {

            foodService.deleteFood(foodId);
            return ResponseEntity.ok().build();
        }
    }



