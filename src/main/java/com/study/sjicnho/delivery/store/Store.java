package com.study.sjicnho.delivery.store;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.sjicnho.delivery.food.Food;
import com.study.sjicnho.delivery.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity(name="store")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Store {

    @Id
    @Column(name="store_id")
    private Integer storeId;

    @OneToMany(mappedBy = "store")
    @JsonIgnore
    private List<Order> orders = new ArrayList<Order>();

    @OneToMany(mappedBy = "store")
    @JsonIgnore
    private List<Food> foods= new ArrayList<>();

    private String name;
    private String address;
    private String phoneNumber;
    private String review;
}
