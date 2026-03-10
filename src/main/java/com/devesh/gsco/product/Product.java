package com.devesh.gsco.product;

import com.devesh.gsco.inventory.Inventory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(
            name = "product_seq",
            sequenceName = "product_seq",
            allocationSize = 1
    )
    private int productId;
    private String sku;
    private String name;
    private double weight;
    private double price;

    @OneToMany(mappedBy = "product")
    private List<Inventory> inventories;
}
