package com.swop.command;

import java.math.BigDecimal;

public record AddItemToOrderCommand(
        String orderId,
        String productId,
        String productName,
        int quatity,
        BigDecimal price,
        String currency
) {
    public AddItemToOrderCommand{
        if(orderId == null || orderId.isBlank())
            throw new IllegalArgumentException("orderId must not be blank");
    }
}
