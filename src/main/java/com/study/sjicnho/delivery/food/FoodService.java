package com.study.sjicnho.delivery.food;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class FoodService {

    private final MapRepository mapRepository;

    public FoodService(MapRepository mapRepository){
        this.mapRepository = mapRepository;
    }

    // 음식 목록 조회
    public List<FoodDto> getFoods(Food food) {

        List<FoodDto> dtos = new ArrayList<FoodDto>();

        //전체 데이터 가져오기
        List<Food> foods = mapRepository.getFoods(food);

        //Entity->DTO
        for(int i = 0; i< foods.size(); i++){
            Food target = foods.get(i);
            FoodDto dto = FoodDto.createFromEntity(target);
            dtos.add(dto);
        }

        return dtos;
    }

    // 음식 상세 조회
    public FoodDto getFoodById(Integer foodId) {

        //데이터 상세 조회
        Food food = mapRepository.getById(foodId);
        log.info("food={}", food);

        //Entity -> DTO
        return FoodDto.createFromEntity(food);

    }


    // 음식 등록
    public Food createFood(FoodDto dto) {

        //DTO->Entity
        Food food = dto.toEntity();
        log.info("food={}", food);

        //repository에 저장
        Food saved = mapRepository.create(food);

        return saved;
    }


    // 음식 수정
    public Food updateFood(Integer foodId, FoodDto dto) {

        //수정할 데이터 Entity 변환
        dto.setFoodId(foodId);
        Food data = dto.toEntity();
        log.info("data={}", data);

        //해당아이디 게시글 확인 후 수정
        Food target = mapRepository.getById(foodId);
        log.info("target={}", target);

        if(target != null){
            mapRepository.update(foodId, target);
        }else{

        }
        return data;
    }

    // 음식 삭제
    public boolean deleteFood(Integer foodId) {

        //해당아이디 게시글 확인 후 삭제
        Food target = mapRepository.getById(foodId);

        if (target != null) {
            mapRepository.delete(foodId);
            return true;
        }else{
           return false;
        }

    }


}
