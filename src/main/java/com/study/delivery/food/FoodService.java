package com.study.delivery.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodService {

    @Autowired
    MapRepository mapRepository;
    
    // 음식 목록 조회
    public List<FoodDto> getFoods() {

        //전체 데이터 가져오기
        List<Food> foods = mapRepository.getAll();
        List<FoodDto> dtos = new ArrayList<FoodDto>();

        //Entity->DTO
        for(int i = 0; i< foods.size(); i++){
            Food target = foods.get(i);
            FoodDto dto = FoodDto.createFoodDto(target);
            dtos.add(dto);
        }

        return dtos;
    }

    // 음식 상세 조회
    public FoodDto getFoodById(Integer foodId) {

        //데이터 상세 조회
        Food food = mapRepository.getById(foodId);

        //Entity -> DTO
        FoodDto dto = FoodDto.createFoodDto(food);

        return dto;
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
    public void updateFood(Integer foodId, FoodDto dto) {

        //수정할 데이터 Entity 변환
        Food data = dto.toEntity();

        //해당아이디 게시글 확인 후 수정
        Food target = mapRepository.getById(foodId);
        if(target != null){
            mapRepository.update(data);
        }
        
    }

    // 음식 삭제
    public void deleteFood(Integer foodId) {
        
        //해당아이디 게시글 확인 후 삭제
        Food target = mapRepository.getById(foodId);
        if (target != null) {
            mapRepository.delete(foodId);
        }
      
    }


}
