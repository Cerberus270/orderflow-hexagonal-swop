package com.swop.command;

/**
 * Comando para crear una nueva orden de compra.
 *
 */
public record CreateOrderCommand(String customerId) {
        public CreateOrderCommand {
            if (customerId == null || customerId.isBlank())
                throw new IllegalArgumentException("customerId must not be blank");
        }
}
