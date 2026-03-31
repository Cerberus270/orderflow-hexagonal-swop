package com.swop.port.out;

import com.swop.model.entity.Order;
import com.swop.model.vo.OrderId;

import java.util.Optional;

/*
 * Interfaz que define el puerto de salida para encontrar una orden por su ID.
 */
public interface FindOrderByIdPort {
    Optional<Order> findById(OrderId orderId);
}
