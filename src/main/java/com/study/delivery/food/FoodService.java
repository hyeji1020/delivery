package com.study.delivery.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    @Autowired
    MapRepository mapRepository;
    
    // 음식 목록 조회
    public List<Food> getFoods() {

        return mapRepository.getAll();
    }

    // 음식 상세 조회
    public Food getFoodById(Integer foodId) {
        return mapRepository.getById(foodId);
    }


    // 음식 등록
    public Food createFood(FoodDto dto) {
        
        //DTO->Entity
        Food food = dto.toEntity();
        
        //repository에 저장
        Food saved = mapRepository.create(food);

        return saved;
    }


    // 음식 수정
    public void updateFood(Food food) {
        mapRepository.update(food);
    }

    // 음식 삭제
    public void deleteFood(Integer foodId) {
        mapRepository.delete(foodId);
    }


}
