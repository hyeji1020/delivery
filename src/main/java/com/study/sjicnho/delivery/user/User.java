package com.study.sjicnho.delivery.user;

import com.study.sjicnho.delivery.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String id;

    private String name;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING) //관리자, 사용자 유저
    private RoleType roleType;

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<Order>();

}
