package com.swop.exception;

public class OrderAlreadyCancelledException extends OrderDomainException {

    public OrderAlreadyCancelledException(String orderId) {
        super("Order with id [" + orderId + "] has already been cancelled.");
    }

}
