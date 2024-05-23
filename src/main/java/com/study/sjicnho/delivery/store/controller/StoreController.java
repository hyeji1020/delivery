package com.study.sjicnho.delivery.store.controller;


import com.study.sjicnho.delivery.store.service.StoreService;
import com.study.sjicnho.delivery.store.dto.StoreDto;
import com.study.sjicnho.delivery.store.entity.Store;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    //가게 등록
    @PostMapping
    @PreAuthorize("hasAnyAuthority('OWNER')")
    public ResponseEntity<Store> save(@Valid @RequestBody StoreDto storeDto){
        storeService.save(storeDto);
        return ResponseEntity.ok().build();
    }

    //가게 리스트 조회
    @GetMapping
    //@PreAuthorize("hasAnyAuthority('CUSTOMER', 'OWNER')")
    public ResponseEntity<List<StoreDto>> getStores(){
        List<StoreDto> stores = storeService.getStores();
        return ResponseEntity.ok(stores);
    }

    //가게 상세 조회
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('CUSTOMER', 'OWNER')")
    public ResponseEntity<StoreDto> findById(@PathVariable Integer id){
        StoreDto dto = storeService.findById(id);
        return ResponseEntity.ok(dto);
    }

    //가게 수정
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('OWNER')")
    public ResponseEntity<Store> updateStore(@PathVariable Integer id, @Valid @RequestBody StoreDto storeDto){
        Store updated = storeService.updateStore(id, storeDto);
        return ResponseEntity.ok(updated);
    }

    //가게 삭제
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('OWNER')")
    public ResponseEntity<Void> deleteStore(@PathVariable Integer id){
        storeService.deleteStore(id);
        return ResponseEntity.ok().build();
    }
}
