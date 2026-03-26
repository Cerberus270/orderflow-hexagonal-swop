package com.swop.exception;

public class OrderAlreadyPaidException extends OrderDomainException {

    public OrderAlreadyPaidException(String orderId) {
        super("Order with id [" + orderId + "] has already been paid.");
    }

}
