package com.devesh.gsco.order;

import com.devesh.gsco.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @SequenceGenerator(
            name = "order_seq",
            sequenceName = "order_seq",
            allocationSize = 1
    )
    private Integer orderId;
    private Date orderDate;
    private String customerName;
    private double totalAmount;
    private String status; // created, confirmed, shipping, shipped

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
