package com.study.sjicnho.service;

import com.study.sjicnho.delivery.store.entity.Store;
import com.study.sjicnho.delivery.store.dto.StoreDto;
import com.study.sjicnho.delivery.store.service.StoreService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class StoreServiceTest {

    @Autowired
    private StoreService storeService;

    StoreDto storeDto = null;

    @BeforeEach
    void beforeEach() {
        // 테스트를 시작 할 때 마다 가게를 새로 생성
        // id 자동 생성
        storeDto = new StoreDto();
        storeDto.setName("엽떡");
        storeDto.setAddress("서울시 금천구 독산동");
        storeDto.setPhoneNumber("02-111-1111");
    }

    @AfterEach
    void afterEach() {
        // 테스트가 끝날 때 마다 가게 삭제
        storeService.deleteStore(1);
    }


    @Test
    @DisplayName("1. id로 가게 보기")
    void getStore(){

        //given
        storeService.save(storeDto);

        //when
        StoreDto savedStore = storeService.findById(1);

        //then
        assertThat(savedStore.getName()).isEqualTo(storeDto.getName());
        assertThat(savedStore.getAddress()).isEqualTo(storeDto.getAddress());
        assertThat(savedStore.getPhoneNumber()).isEqualTo(storeDto.getPhoneNumber());
    }

    @Test
    @DisplayName("2. 가게 수정 테스트")
    void editTest() {

        storeService.save(storeDto);

        StoreDto updateData = new StoreDto(1, "수정", "서울시 금천구", "222-222");

        storeService.updateStore(1, storeDto);
        assertThat(storeService.findById(1).getName()).isEqualTo("엽떡");

        storeService.updateStore(1, updateData);
        assertThat(storeService.findById(1).getName()).isEqualTo("수정");
    }

}
