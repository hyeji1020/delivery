package com.study.sjicnho.delivery.food.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.study.sjicnho.delivery.order.entity.OrderLine;
import com.study.sjicnho.delivery.store.entity.Store;
import lombok.*;
import javax.persistence.*;
import java.util.List;

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
    @JoinColumn(name="store_id")
    @JsonIgnore
    private Store store;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrderLine> orderLines;

}
