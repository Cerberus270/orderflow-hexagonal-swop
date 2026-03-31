package com.swop.port.in;

import com.swop.command.AddItemToOrderCommand;
import com.swop.model.entity.Order;

/*
 * Interfaz que define el caso de uso para agregar un item a una orden existente.
 */
public interface AddItemToOrderUseCase {
    Order addItem(AddItemToOrderCommand command);
}