package com.devesh.gsco.orderitem;

import com.devesh.gsco.order.Order;
import com.devesh.gsco.product.Product;
import com.devesh.gsco.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    public List<OrderItem> createOrderItems(
            Order order,
            List<OrderItemDto> itemRequests
    ) {

        List<OrderItem> items = new ArrayList<>();

        for (OrderItemDto request : itemRequests) {

            Product product =
                    productRepository.findById(request.productId()).orElse(null);

            if (product == null) continue;

            OrderItem item = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(request.quantity())
                    .price(product.getPrice())
                    .build();

            items.add(orderItemRepository.save(item));
        }

        return items;
    }
}