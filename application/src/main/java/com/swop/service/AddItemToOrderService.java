package com.swop.service;

import com.swop.command.AddItemToOrderCommand;
import com.swop.exception.OrderDomainException;
import com.swop.exception.OrderNotFoundException;
import com.swop.model.entity.Order;
import com.swop.model.entity.OrderItem;
import com.swop.model.vo.Money;
import com.swop.model.vo.OrderId;
import com.swop.port.in.AddItemToOrderUseCase;
import com.swop.port.out.FindOrderByIdPort;
import com.swop.port.out.InventoryService;
import com.swop.port.out.SaveOrderPort;

/*
 * Servicio que maneja la lógica para agregar un ítem a una orden.
 * Este servicio se encarga de validar la disponibilidad del producto en el inventario
 * y de actualizar la orden con el nuevo ítem.
 */
public class AddItemToOrderService implements AddItemToOrderUseCase {

    private final FindOrderByIdPort findOrderByIdPort;
    private final InventoryService inventoryService;
    private final SaveOrderPort saveOrderPort;

    public AddItemToOrderService(FindOrderByIdPort findOrderByIdPort,
                                 InventoryService inventoryService,
                                 SaveOrderPort saveOrderPort) {
        this.findOrderByIdPort = findOrderByIdPort;
        this.inventoryService = inventoryService;
        this.saveOrderPort = saveOrderPort;
    }

    @Override
    public Order addItem(AddItemToOrderCommand command) {
        Order order = findOrderByIdPort.findById(OrderId.of(command.orderId()))
                .orElseThrow(() -> new OrderNotFoundException(command.orderId()));

        if (!inventoryService.isAvailable(command.productId(), command.quantity()))
            throw new OrderDomainException(
                    "Product [" + command.productId() + "] is not available "+
                            "in quantity: " + command.quantity());

        Money unitPrice = Money.of(command.unitPrice(), command.currency());
        OrderItem item  = new OrderItem(
                command.productId(), command.productName(),
                command.quantity(),  unitPrice);
        order.addItem(item);
        order.calculateTotal();
        return saveOrderPort.save(order);
    }

}
