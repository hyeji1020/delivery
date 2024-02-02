package com.study.sjicnho.delivery.store;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
        Store store = storeDto.toEntity();
        storeRepository.save(store);
    }

    //가게 리스트 조회
    public List<Store> getStores(){
        return storeRepository.findAll();
    }

    //가게 상세 조회
    public Store findById(Integer id){
        return storeRepository.findById(id).orElse(null);
    }

    public Store updateStore(Integer id, StoreDto storeDto) {

        storeDto.setStoreId(id);

        Store store = storeDto.toEntity();

        Store target = storeRepository.findById(id).orElse(null);

        if(store != null) {
            storeRepository.save(store);
        }
        return store;
    }

    public void deleteStore(Integer id) {
        Store store = storeRepository.findById(id).orElse(null);
        if(store != null){
            storeRepository.delete(store);
        }
    }
}
