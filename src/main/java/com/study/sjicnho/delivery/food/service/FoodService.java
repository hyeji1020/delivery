package com.study.sjicnho.delivery.food.service;

import com.study.sjicnho.delivery.common.exception.ErrorCode;
import com.study.sjicnho.delivery.food.entity.Food;
import com.study.sjicnho.delivery.food.exception.NoSuchFoodException;
import com.study.sjicnho.delivery.food.repository.FoodJpaRepository;
import com.study.sjicnho.delivery.food.dto.FoodDto;
import com.study.sjicnho.delivery.order.repository.OrderLineRepository;
import com.study.sjicnho.delivery.store.entity.Store;
import com.study.sjicnho.delivery.store.exception.NoSuchStoreException;
import com.study.sjicnho.delivery.store.repository.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class FoodService {

    private final FoodJpaRepository foodJpaRepository;
    private final StoreRepository storeRepository;
    private final OrderLineRepository orderLineRepository;

    public FoodService(FoodJpaRepository foodJpaRepository, StoreRepository storeRepository, OrderLineRepository orderLineRepository){
        this.foodJpaRepository = foodJpaRepository;
        this.storeRepository = storeRepository;
        this.orderLineRepository = orderLineRepository;
    }


    // 음식 목록 조회
    public List<FoodDto> getFoods() {

        //전체 데이터 가져오기
        List<Food> foods = foodJpaRepository.findAll();

        List<FoodDto> dtos = new ArrayList<FoodDto>();

        //Entity->DTO
        for(int i = 0; i< foods.size(); i++){
            Food target = foods.get(i);
            FoodDto dto = FoodDto.createFromEntity(target);
            dtos.add(dto);
        }

        if(dtos != null){
            return dtos;
        }else{
            new NoSuchFoodException(ErrorCode.FOOD_NOT_FOUND);
        }
        return dtos;
    }

    // 음식 상세 조회
    public FoodDto findById(Integer foodId) {

        Food food = foodJpaRepository.findById(foodId).orElseThrow(() ->
                new NoSuchFoodException(ErrorCode.FOOD_NOT_FOUND));

        FoodDto dto = FoodDto.createFromEntity(food);

        return dto;

    }

    private Food findFoodById(Integer foodId) {
        return foodJpaRepository.findById(foodId)
                .orElseThrow(() -> new NoSuchFoodException(ErrorCode.FOOD_NOT_FOUND));
    }

    // 음식 등록
    public Food save(FoodDto dto) {
        //존재하는 음식 가게인지 검사
        Store store = storeRepository.findById(dto.getStore().getStoreId())
                .orElseThrow(() -> new NoSuchStoreException(ErrorCode.STORE_NOT_FOUND));

        //음식 중복 검사
        List<Food> food_list = new ArrayList(store.getFoods());
        for(int i = 0; i<food_list.size(); i++){
            Food food = food_list.get(i);
            if(Objects.equals(food.getName(), dto.getName())){
                throw new RuntimeException("이미 등록 되어있는 음식입니다.");
            }
        }

        //DTO->Entity 변환
        Food target = dto.toEntity();

        //가게 설정
        dto.setStore(store);

        //repository에 저장
        return foodJpaRepository.save(target);
    }


    // 음식 수정
    public Food updateFood(Integer foodId, FoodDto dto) {

        dto.setFoodId(foodId);

        //DTO->Entity
        Food data = dto.toEntity();

        //음식아이디 확인 후 수정
        Food target = foodJpaRepository.findById(foodId)
                .orElseThrow(() -> new NoSuchFoodException(ErrorCode.FOOD_NOT_FOUND));

        foodJpaRepository.save(data);

        return data;
    }

    // 음식 삭제
    public void deleteFood(Integer foodId) {

        //음식아이디 확인 후 삭제
        Food target = foodJpaRepository.findById(foodId)
                .orElseThrow(() -> new NoSuchFoodException(ErrorCode.FOOD_NOT_FOUND));

        foodJpaRepository.deleteById(foodId);
    }
}
