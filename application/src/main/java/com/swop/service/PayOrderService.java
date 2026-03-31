package com.swop.service;

import com.swop.exception.OrderDomainException;
import com.swop.exception.OrderNotFoundException;
import com.swop.model.entity.Order;
import com.swop.model.vo.OrderId;
import com.swop.port.in.PayOrderUseCase;
import com.swop.port.out.FindOrderByIdPort;
import com.swop.port.out.PaymentGateway;
import com.swop.port.out.SaveOrderPort;

/*
 * Servicio que implementa la lógica para pagar una orden.
 * Este servicio interactúa con la pasarela de pago.
 */
public class PayOrderService implements PayOrderUseCase {

    private final PaymentGateway paymentGateway;
    private final FindOrderByIdPort findOrderByIdPort;
    private final SaveOrderPort saveOrderPort;

    public PayOrderService(PaymentGateway paymentGateway,
                           FindOrderByIdPort findOrderByIdPort,
                           SaveOrderPort saveOrderPort) {
        this.paymentGateway = paymentGateway;
        this.findOrderByIdPort = findOrderByIdPort;
        this.saveOrderPort = saveOrderPort;
    }

    @Override
    public void payOrder(String orderId) {
        Order order = findOrderByIdPort.findById(OrderId.of(orderId))
                .orElseThrow(() -> new OrderNotFoundException(orderId));

        order.calculateTotal();
        boolean success = paymentGateway.processPayment(orderId, order.getTotal());
        if (!success)
            throw new OrderDomainException( "Payment processing failed for order: " + orderId);

        order.pay();
        saveOrderPort.save(order);
    }

}
