package com.devesh.gsco.order;


import com.devesh.gsco.orderitem.OrderItemDto;

import java.util.List;

public record CreateOrderRequest(
        String customerName,
        String status,
        List<OrderItemDto> items
) {}
