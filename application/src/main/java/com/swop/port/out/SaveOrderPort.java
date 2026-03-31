package com.swop.port.out;

import com.swop.model.entity.Order;

public interface SaveOrderPort {
    Order save(Order order);
}
