package com.swop.service;

import com.swop.exception.OrderNotFoundException;
import com.swop.model.entity.Order;
import com.swop.model.vo.OrderId;
import com.swop.port.in.CancelOrderUseCase;
import com.swop.port.out.FindOrderByIdPort;
import com.swop.port.out.SaveOrderPort;

/*
 * Servicio que implementa la lógica para cancelar una orden.
 * Este servicio se encarga de actualizarlo a "cancelada".
 */
public class CancelOrderService implements CancelOrderUseCase {

    private final FindOrderByIdPort findOrderByIdPort;
    private final SaveOrderPort saveOrderPort;

    public CancelOrderService(FindOrderByIdPort findOrderByIdPort,
                              SaveOrderPort saveOrderPort) {
        this.findOrderByIdPort = findOrderByIdPort;
        this.saveOrderPort = saveOrderPort;
    }

    @Override
    public void cancelOrder(String orderId) {
        Order order = findOrderByIdPort.findById(OrderId.of(orderId))
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        order.cancel();
        saveOrderPort.save(order);
    }

}
