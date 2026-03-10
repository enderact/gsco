package com.devesh.gsco.warehouse;

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
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "warehouse_seq")
    @SequenceGenerator(
            name = "warehouse_seq",
            sequenceName = "warehouse_seq",
            allocationSize = 1
    )
    private Integer warehouseId;
    private String location;
    private int capacity;

    @OneToMany(mappedBy = "warehouse")
    private List<Inventory> inventories;
}
