package com.study.sjicnho.delivery.user.entity;

import lombok.Getter;

@Getter
public enum UserRole {

    CUSTOMER("customer"),
    OWNER("owner");

    private final String name;

    UserRole(String name) {
        this.name = name;
    }

}
