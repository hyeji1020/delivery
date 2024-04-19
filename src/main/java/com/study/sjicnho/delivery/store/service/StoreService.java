package com.study.sjicnho.delivery.store.service;

import com.study.sjicnho.delivery.ErrorCode;
import com.study.sjicnho.delivery.food.dto.FoodDto;
import com.study.sjicnho.delivery.food.entity.Food;
import com.study.sjicnho.delivery.food.exception.NoSuchFoodException;
import com.study.sjicnho.delivery.store.dto.StoreDto;
import com.study.sjicnho.delivery.store.entity.Store;
import com.study.sjicnho.delivery.store.exception.NoSuchStoreException;
import com.study.sjicnho.delivery.store.repository.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    //가게 등록
    public void save(StoreDto storeDto){
        storeRepository.save(storeDto.toEntity());
    }

    //가게 리스트 조회
    public List<StoreDto> getStores(){

        //전체 데이터 가져오기
        List<Store> stores = storeRepository.findAll();

        List<StoreDto> dtos = new ArrayList<StoreDto>();

        //Entity->DTO
        for(int i = 0; i< stores.size(); i++){
            Store target = stores.get(i);
            StoreDto dto = StoreDto.createFromEntity(target);
            dtos.add(dto);
        }

        if(dtos != null){
            return dtos;
        }else{
            new NoSuchStoreException(ErrorCode.STORE_NOT_FOUND);
        }
        return dtos;
    }

    //가게 상세 조회
    public StoreDto findById(Integer id){
        Store store =  storeRepository.findById(id)
                .orElseThrow(() -> new NoSuchStoreException(ErrorCode.STORE_NOT_FOUND));

        StoreDto dto = StoreDto.createFromEntity(store);

        return dto;
    }

    public Store updateStore(Integer id, StoreDto storeDto) {

        storeRepository.findById(id)
                .orElseThrow(() -> new NoSuchStoreException(ErrorCode.STORE_NOT_FOUND));

        storeDto.setStoreId(id);
        storeRepository.save(storeDto.toEntity());

        return storeRepository.save(storeDto.toEntity());
    }

    public void deleteStore(Integer id) {

        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new NoSuchStoreException(ErrorCode.STORE_NOT_FOUND));

        storeRepository.delete(store);
    }
}
