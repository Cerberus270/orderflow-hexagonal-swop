package com.swop.port.out;

import com.swop.model.entity.Order;

import java.util.Optional;

public interface FindOrderByIdPort {
    Optional<Order> findOrderById(String orderId);
}
