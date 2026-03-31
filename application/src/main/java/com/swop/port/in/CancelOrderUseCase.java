package com.swop.port.in;

public interface CancelOrderUseCase {
    // TODO Si tienen uno o 2 parametros es recomendable el Command
    void cancelOrder(String orderId);
}
