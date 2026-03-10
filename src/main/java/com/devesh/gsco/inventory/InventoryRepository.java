package com.devesh.gsco.inventory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    @Query("""
            SELECT i.quantity
            FROM Inventory i
            WHERE i.product.productId = :productId
            AND i.warehouse.warehouseId = :warehouseId
            """)
    Integer findStock(Integer productId, Integer warehouseId);


    Inventory findByProduct_ProductIdAndWarehouse_WarehouseId(Integer productId, Integer warehouseId);
}
