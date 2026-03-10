package com.devesh.gsco.inventory;

import com.devesh.gsco.product.Product;
import com.devesh.gsco.warehouse.Warehouse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_seq")
    @SequenceGenerator(
            name = "inventory_seq",
            sequenceName = "inventory_seq",
            allocationSize = 1
    )
    private Integer id;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
