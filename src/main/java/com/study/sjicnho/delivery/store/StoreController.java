package com.study.sjicnho.delivery.store;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
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
    public ResponseEntity<Store> save(@Valid @RequestBody StoreDto storeDto){
        storeService.save(storeDto);
        return ResponseEntity.ok().build();
    }

    //가게 리스트 조회
    @GetMapping
    public ResponseEntity<List<Store>> getStores(){
        List<Store> stores = storeService.getStores();
        return ResponseEntity.ok().body(stores);
    }

    //가게 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<Store> findById(@PathVariable Integer id){
        Store store = storeService.findById(id);
        return ResponseEntity.ok().body(store);
    }

    //가게 수정
    @PutMapping("/{id}")
    public ResponseEntity<Store> updateStore(@PathVariable Integer id, @Valid @RequestBody StoreDto storeDto){
        Store updated = storeService.updateStore(id, storeDto);
        return ResponseEntity.ok().body(updated);
    }

    //가게 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStore(@PathVariable Integer id){
        storeService.deleteStore(id);
        return ResponseEntity.ok().build();
    }
}
