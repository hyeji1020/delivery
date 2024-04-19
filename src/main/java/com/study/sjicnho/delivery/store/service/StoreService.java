package com.study.sjicnho.delivery.store.service;

import com.study.sjicnho.delivery.ErrorCode;
import com.study.sjicnho.delivery.store.dto.StoreDto;
import com.study.sjicnho.delivery.store.entity.Store;
import com.study.sjicnho.delivery.store.exception.NoSuchStore;
import com.study.sjicnho.delivery.store.repository.StoreRepository;
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
        return storeRepository.findById(id)
                .orElseThrow(() -> new NoSuchStore(ErrorCode.STORE_NOT_FOUND));
    }

    public Store updateStore(Integer id, StoreDto storeDto) {

        storeDto.setStoreId(id);

        Store store = storeDto.toEntity();

        Store target = storeRepository.findById(id)
                .orElseThrow(() -> new NoSuchStore(ErrorCode.STORE_NOT_FOUND));

        if(store != null) {
            storeRepository.save(store);
        }
        return store;
    }

    public void deleteStore(Integer id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new NoSuchStore(ErrorCode.STORE_NOT_FOUND));
        if(store != null){
            storeRepository.delete(store);
        }
    }

}
