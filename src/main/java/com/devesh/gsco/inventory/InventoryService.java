package com.devesh.gsco.inventory;

import com.devesh.gsco.product.Product;
import com.devesh.gsco.product.ProductRepository;
import com.devesh.gsco.warehouse.Warehouse;
import com.devesh.gsco.warehouse.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final WarehouseRepository warehouseRepository;
    private final ProductRepository productRepository;

    public Integer checkStock(Integer productId, Integer warehouseId) {
        return inventoryRepository.findStock(productId, warehouseId);
    }
//
//    public InventoryDto dispatchItem(Integer productId, Integer warehouseId, Integer quantity) {
//
//        Inventory inventory = inventoryRepository
//                .findByProduct_ProductIdAndWarehouse_WarehouseId(productId, warehouseId);
//
//        if(inventory != null && inventory.getQuantity() >= quantity){
//            inventory.setQuantity(inventory.getQuantity() - quantity);
//            return convertToDto(inventoryRepository.save(inventory));
//        }
//
//        return null;
//    }

    public InventoryDto createInventory(InventoryRequest request) {

        Warehouse warehouse = Warehouse.builder()
                .location(request.getLocation())
                .capacity(request.getCapacity())
                .build();

        warehouse = warehouseRepository.save(warehouse);

        Product product = Product.builder()
                .sku(request.getSku())
                .name(request.getName())
                .weight(request.getWeight())
                .price(request.getPrice())
                .build();

        product = productRepository.save(product);

        Inventory inventory = Inventory.builder()
                .warehouse(warehouse)
                .product(product)
                .quantity(request.getQuantity())
                .build();

        return convertToDto(inventoryRepository.save(inventory));
    }

    private InventoryDto convertToDto(Inventory inventory) {
        return new InventoryDto(
                inventory.getId(),
                inventory.getQuantity(),
                inventory.getWarehouse().getWarehouseId(),
                inventory.getProduct().getProductId()
        );
    }

}
