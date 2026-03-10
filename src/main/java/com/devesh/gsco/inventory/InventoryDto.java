package com.devesh.gsco.inventory;

public record InventoryDto(
        Integer id,
        int quantity,
        int warehouseId,
        int productId
) {
}
