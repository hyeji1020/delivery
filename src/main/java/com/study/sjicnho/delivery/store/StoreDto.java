package com.study.sjicnho.delivery.store;


import lombok.*;

import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreDto {

    private Integer storeId;
    private String name;
    private String address;
    private String phoneNumber;
    private String review;

    //DTO->Entity
    public Store toEntity(){
        return Store.builder()
                .storeId(storeId)
                .name(name)
                .address(address)
                .phoneNumber(phoneNumber)
                .review(review)
                .build();
    }

    //Entity->DTO
    public static StoreDto createFromEntity(Store store){
        return StoreDto.builder()
                .storeId(store.getStoreId())
                .name(store.getName())
                .address(store.getAddress())
                .phoneNumber(store.getPhoneNumber())
                .review(store.getReview())
                .build();
    }
}
