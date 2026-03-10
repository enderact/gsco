package com.devesh.gsco.order;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;

    @GetMapping("/{order-id}")
    public OrderDto findOrderByOrderId(@PathVariable("order-id") int orderId) {
        return orderService.findOrderByOrderId(orderId);
    }

    @PostMapping("/create")
    public OrderDto createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @DeleteMapping("/delete/{order-id}")
    public void deleteOrderByOrderId(@PathVariable("order-id") int orderId) {
        orderService.deleteOrderByOrderId(orderId);
    }

    @PostMapping("/users/{user-id}/create")
    public OrderDto createOrder(
            @PathVariable("user-id") int userId,
            @RequestBody CreateOrderRequest request) {

        return orderService.createOrder(userId, request);
    }

    @PatchMapping("/{order-id}/confirm")
    public OrderDto confirmOrder(@PathVariable("order-id") int orderId) {
        return orderService.confirmOrder(orderId);
    }
}
