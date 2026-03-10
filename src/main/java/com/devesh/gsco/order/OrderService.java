package com.devesh.gsco.order;

import com.devesh.gsco.user.User;
import com.devesh.gsco.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderDto findOrderByOrderId(int orderId) {
        return convertToDto(orderRepository.findOrderByOrderId(orderId));
    }

    public OrderDto createOrder(Order order) {
        return convertToDto(orderRepository.save(order));
    }

    public void deleteOrderByOrderId(int orderId) {
        orderRepository.deleteById(orderId);
    }

    public OrderDto createOrder(int userId, Order order) {

        User user = userRepository.findById(userId).orElse(null);

        order.setUser(user);
        Order savedOrder = orderRepository.save(order);

        return convertToDto(savedOrder);
    }

    private OrderDto convertToDto(Order order) {
        return new OrderDto(
                order.getOrderId(),
                order.getOrderDate(),
                order.getCustomerName(),
                order.getTotalAmount(),
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
