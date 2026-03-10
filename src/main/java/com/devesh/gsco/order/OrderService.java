package com.devesh.gsco.order;

import com.devesh.gsco.orderitem.OrderItemService;
import com.devesh.gsco.user.User;
import com.devesh.gsco.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@AllArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderItemService orderItemService;

    public OrderDto findOrderByOrderId(int orderId) {
        return convertToDto(orderRepository.findOrderByOrderId(orderId));
    }

    public OrderDto createOrder(Order order) {
        return convertToDto(orderRepository.save(order));
    }

    public void deleteOrderByOrderId(int orderId) {
        orderRepository.deleteById(orderId);
    }

    public OrderDto createOrder(int userId, CreateOrderRequest request) {

        User user = userRepository.findById(userId).orElse(null);

        Order order = Order.builder()
                .customerName(request.customerName())
                .status(request.status())
                .orderDate(new Date())
                .user(user)
                .build();

        Order savedOrder = orderRepository.save(order);

        orderItemService.createOrderItems(savedOrder, request.items());

        return convertToDto(savedOrder);
    }

    private OrderDto convertToDto(Order order) {
        return new OrderDto(
                order.getOrderId(),
                order.getOrderDate(),
                order.getCustomerName(),
                order.getStatus(),
                order.getUser() != null ? order.getUser().getUserId() : null
        );
    }

    public OrderDto confirmOrder(int orderId) {

        Order order = orderRepository.findById(orderId).orElse(null);

        if (order != null) {
            order.setStatus("confirmed");
            Order saved = orderRepository.save(order);
            return convertToDto(saved);
        }

        return null;
    }
}
