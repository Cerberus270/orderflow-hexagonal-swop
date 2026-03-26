package com.swop.port.in;

import com.swop.command.CreateOrderCommand;
import com.swop.model.entity.Order;

/**
 * Create the Order Use Case
 */
public interface CreateOrderUseCase {
    Order createOrder(CreateOrderCommand command);
}
