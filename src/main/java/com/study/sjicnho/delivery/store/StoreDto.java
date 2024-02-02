package com.study.sjicnho.delivery.store;

import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreDto {

    private Integer storeId;

    @NotBlank(message = "가게명은 필수 입력 값입니다.")
    private String name;

    @NotNull(message = "주소는 필수 입력 값입니다.")
    private String address;

    @NotBlank(message = "전화번호는 필수 입력 값입니다.")
    private String phoneNumber;


    //DTO->Entity
    public Store toEntity(){
        return Store.builder()
                .storeId(storeId)
                .name(name)
                .address(address)
                .phoneNumber(phoneNumber)
                .build();
    }

    //Entity->DTO
    public static StoreDto createFromEntity(Store store){
        return StoreDto.builder()
                .storeId(store.getStoreId())
                .name(store.getName())
                .address(store.getAddress())
                .phoneNumber(store.getPhoneNumber())
                .build();
    }
}
