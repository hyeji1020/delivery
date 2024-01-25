package com.study.sjicnho.delivery.food.repository;

import com.study.sjicnho.delivery.food.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodJpaRepository extends JpaRepository<Food, Integer>{

}