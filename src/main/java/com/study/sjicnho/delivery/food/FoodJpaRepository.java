package com.study.sjicnho.delivery.food;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//상위인터페이스
@Repository
public interface FoodJpaRepository extends JpaRepository<Food, Integer> {


}
