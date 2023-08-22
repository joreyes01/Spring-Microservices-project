package com.dizstance.orderservice.repository;

import com.dizstance.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository <Order , Long> {
}
