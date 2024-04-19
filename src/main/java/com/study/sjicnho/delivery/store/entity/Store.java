package com.study.sjicnho.delivery.store.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.study.sjicnho.delivery.food.entity.Food;
import com.study.sjicnho.delivery.order.entity.Order;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity(name="store")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("storeId")
    private Integer storeId;

    @OneToMany(mappedBy = "store")
    @JsonIgnore
    private List<Food> foods= new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Order> orders= new ArrayList<>();

    @JsonProperty("storeName")
    private String name;

    @JsonProperty("storeAddress")
    private String address;

    @JsonProperty("storeNumber")
    private String phoneNumber;

    public Store(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}
