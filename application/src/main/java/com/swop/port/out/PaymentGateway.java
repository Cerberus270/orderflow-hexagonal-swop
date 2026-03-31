package com.swop.port.out;

import com.swop.model.vo.Money;

/*
 * Interfaz que define el contrato para la pasarela de pago.
 */
public interface PaymentGateway {
    boolean processPayment(String orderId, Money amount);
}
