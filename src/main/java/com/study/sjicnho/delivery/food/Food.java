package com.study.sjicnho.delivery.food;


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

    @ManyToOne
    @JoinColumn(name="store_id")
    private Store store;


}
