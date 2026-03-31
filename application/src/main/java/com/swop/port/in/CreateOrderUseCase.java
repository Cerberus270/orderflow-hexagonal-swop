package com.swop.port.in;

import com.swop.command.CreateOrderCommand;
import com.swop.model.entity.Order;

/**
 * Interfaz que define el caso de uso para crear una nueva orden.
 */
public interface CreateOrderUseCase {
    Order createOrder(CreateOrderCommand command);
}
