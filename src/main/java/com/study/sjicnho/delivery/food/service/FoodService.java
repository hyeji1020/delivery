package com.study.sjicnho.delivery.food.service;

import com.study.sjicnho.delivery.food.entity.Food;
import com.study.sjicnho.delivery.food.repository.FoodJpaRepository;
import com.study.sjicnho.delivery.food.dto.FoodDto;
import com.study.sjicnho.delivery.store.Store;
import com.study.sjicnho.delivery.store.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class FoodService {

    private final FoodJpaRepository foodJpaRepository;
    private final StoreRepository storeRepository;

    public FoodService(FoodJpaRepository foodJpaRepository, StoreRepository storeRepository){
        this.foodJpaRepository = foodJpaRepository;
        this.storeRepository = storeRepository;
    }


    // 음식 목록 조회
    public List<FoodDto> getFoods(Food food) {

        List<FoodDto> dtos = new ArrayList<FoodDto>();

        //전체 데이터 가져오기
        List<Food> foods = foodJpaRepository.findAll();

        //Entity->DTO
        for(int i = 0; i< foods.size(); i++){
            Food target = foods.get(i);
            FoodDto dto = FoodDto.createFromEntity(target);
            dtos.add(dto);
        }

        return dtos;
    }

    // 음식 상세 조회
    public FoodDto findById(Integer foodId) {
        Food food = foodJpaRepository.findById(foodId).orElse(null);
        FoodDto dto = FoodDto.createFromEntity(food);
        return dto;

    }

    // 음식 등록
    public Food save(FoodDto dto) {

        Store store = storeRepository.findById(dto.getStore().getStoreId()).get();
        dto.setStore(store);
        log.info("storeDto={}", store);

        //DTO->Entity
        Food food = dto.toEntity();
        log.info("foodservice:food={}", food);

        //repository에 저장
        return foodJpaRepository.save(food);
    }


    // 음식 수정
    public Food updateFood(Integer foodId, FoodDto dto) {

        dto.setFoodId(foodId);

        //DTO->Entity
        Food data = dto.toEntity();
        log.info("data={}", data);

        //해당아이디 게시글 확인 후 수정
        Food target = foodJpaRepository.findById(foodId).orElseThrow(null);
        log.info("target={}", target);

        if(target != null){
            foodJpaRepository.save(data);
        }
        return data;
    }

    // 음식 삭제
    public void deleteFood(Integer foodId) {

        //해당아이디 게시글 확인 후 삭제
        Food target = foodJpaRepository.getById(foodId);

        if (target != null) {
             foodJpaRepository.deleteById(foodId);
        }else{

        }
    }


}
