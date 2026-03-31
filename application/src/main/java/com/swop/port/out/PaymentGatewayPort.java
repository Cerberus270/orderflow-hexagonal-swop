package com.swop.port.out;

import com.swop.model.vo.Money;

public interface PaymentGatewayPort {
    boolean processPayment(String orderId, Money amount);
}
