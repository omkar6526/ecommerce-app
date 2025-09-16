package com.omkar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omkar.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {


}
