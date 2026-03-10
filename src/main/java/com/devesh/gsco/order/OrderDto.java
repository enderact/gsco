package com.devesh.gsco.order;

import java.util.Date;

public record OrderDto(
        Integer orderId,
        Date orderDate,
        String customerName,
        String status,
        Integer userId
) {
}
