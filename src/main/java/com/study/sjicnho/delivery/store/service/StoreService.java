package com.study.sjicnho.delivery.store.service;

import com.study.sjicnho.delivery.common.exception.ErrorCode;
import com.study.sjicnho.delivery.store.dto.StoreDto;
import com.study.sjicnho.delivery.store.entity.Store;
import com.study.sjicnho.delivery.store.exception.NoSuchStoreException;
import com.study.sjicnho.delivery.store.repository.StoreRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Store> stores = storeRepository.findAll();
        if (stores.isEmpty()) {
            throw new NoSuchStoreException(ErrorCode.STORE_NOT_FOUND);
        }
        return stores.stream()
                .map(StoreDto::createFromEntity)
                .collect(Collectors.toList());
    }

    //가게 상세 조회
    public StoreDto findById(Integer id){
        Store store =  storeRepository.findById(id)
                .orElseThrow(() -> new NoSuchStoreException(ErrorCode.STORE_NOT_FOUND));
        return StoreDto.createFromEntity(store);
    }

    public Store updateStore(Integer id, StoreDto storeDto) {
        storeRepository.findById(id)
                .orElseThrow(() -> new NoSuchStoreException(ErrorCode.STORE_NOT_FOUND));

        storeDto.setStoreId(id);
        return storeRepository.save(storeDto.toEntity());
    }

    public void deleteStore(Integer id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new NoSuchStoreException(ErrorCode.STORE_NOT_FOUND));

        storeRepository.delete(store);
    }
}
