package com.study.sjicnho.delivery.food;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.sjicnho.delivery.store.Store;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Getter
@Entity
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer foodId;

    private String foodName;
    
    private int foodPrice;

    private int foodQuantity;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="store_id")
    private Store store;

//    public void calculateFood(){
//        this.foodPrice = foodPrice * foodQuantity;
//    }



}
