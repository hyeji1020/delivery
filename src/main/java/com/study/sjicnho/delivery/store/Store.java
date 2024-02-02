package com.study.sjicnho.delivery.store;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.sjicnho.delivery.food.entity.Food;
import com.study.sjicnho.delivery.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Integer storeId;

    @OneToMany(mappedBy = "store")
    @JsonIgnore
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    @JsonIgnore
    private List<Food> foods= new ArrayList<>();

    @NotBlank
    private String name;

    private String address;
    private String phoneNumber;


}
