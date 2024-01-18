package com.study.sjicnho.delivery.food;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.sjicnho.delivery.store.Store;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

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

    private String name;

    private int price;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="store_id")
    private Store store;


    public int calculate(int quantity) {
        return price * quantity;
    }

}
