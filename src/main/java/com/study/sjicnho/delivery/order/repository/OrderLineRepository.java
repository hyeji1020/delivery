package com.study.sjicnho.delivery.order.repository;

import com.study.sjicnho.delivery.order.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
}
