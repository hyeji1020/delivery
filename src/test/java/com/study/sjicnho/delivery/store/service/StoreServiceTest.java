package com.study.sjicnho.delivery.store.service;

import com.study.sjicnho.delivery.common.exception.ErrorCode;
import com.study.sjicnho.delivery.store.entity.Store;
import com.study.sjicnho.delivery.store.dto.StoreDto;
import com.study.sjicnho.delivery.store.exception.NoSuchStoreException;
import com.study.sjicnho.delivery.store.repository.StoreRepository;
import com.study.sjicnho.delivery.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class StoreServiceTest {

    @Mock
    private StoreRepository storeRepository;

    @InjectMocks
    private StoreService storeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("가게 등록 테스트")
    void testSaveStore() {
        // Given
        StoreDto storeDto = new StoreDto();
        storeDto.setName("엽기떡볶이");

        // When
        storeService.save(storeDto);

        // Then
        verify(storeRepository, times(1)).save(any(Store.class));
    }

    @Test
    @DisplayName("가게 리스트 조회 - 성공")
    void testGetStores_Success() {
        // Given
        List<Store> stores = new ArrayList<>();
        stores.add(new Store(1, "엽기떡볶이", "서울시 강남구", "02-7544-8888"));
        stores.add(new Store(2, "황비홍마라탕", "서울시 도봉구", "02-1544-3554"));
        when(storeRepository.findAll()).thenReturn(stores);

        // When
        List<StoreDto> result = storeService.getStores();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("엽기떡볶이", result.get(0).getName());
        assertEquals("황비홍마라탕", result.get(1).getName());
    }

    @Test
    @DisplayName("가게 리스트 조회 - 실패")
    void testGetStores_Failure() {
        // Given
        when(storeRepository.findAll()).thenReturn(new ArrayList<>());

        // When & Then
        NoSuchStoreException exception = assertThrows(NoSuchStoreException.class, () -> storeService.getStores());
        assertEquals(ErrorCode.STORE_NOT_FOUND, exception.getErrorCode());
    }

    @Test
    @DisplayName("가게 상세 조회 - 성공")
    void testFindById_Success() {
        // Given
        Store store = new Store(1, "엽기떡볶이", "서울시 강남구", "02-7544-8888");
        when(storeRepository.findById(1)).thenReturn(Optional.of(store));

        // When
        StoreDto result = storeService.findById(1);

        // Then
        assertNotNull(result);
        assertEquals("엽기떡볶이", result.getName());
    }

    @Test
    @DisplayName("가게 상세 조회 - 실패")
    void testFindById_Failure() {
        // Given
        when(storeRepository.findById(1)).thenReturn(Optional.empty());

        // When & Then
        NoSuchStoreException exception = assertThrows(NoSuchStoreException.class, () -> storeService.findById(1));
        assertEquals(ErrorCode.STORE_NOT_FOUND, exception.getErrorCode());
    }

    @Test
    @DisplayName("가게 정보 수정 테스트")
    void testUpdateStore() {
        // Given
        StoreDto storeDto = new StoreDto(1, "신전떡볶이", "서울시 강남구", "02-7544-8888");

        Store existingStore = new Store(1, "엽기떡볶이", "서울시 강남구", "02-7544-8888");
        when(storeRepository.findById(1)).thenReturn(Optional.of(existingStore));

        // When
        storeService.updateStore(1, storeDto);

        // Then
        verify(storeRepository, times(1)).findById(1);
        verify(storeRepository, times(1)).save(any(Store.class));
    }

    @Test
    @DisplayName("가게 정보 삭제 테스트")
    void testDeleteStore() {
        // Given
        Store existingStore = new Store(1, "엽기떡볶이", "서울시 강남구", "02-7544-8888");
        when(storeRepository.findById(1)).thenReturn(Optional.of(existingStore));

        // When
        storeService.deleteStore(1);

        // Then
        verify(storeRepository, times(1)).delete(existingStore);
    }

}
