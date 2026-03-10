package com.devesh.gsco.inventory;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/stock")
    public Integer checkStock(
            @RequestParam Integer productId,
            @RequestParam Integer warehouseId
    ){
        return inventoryService.checkStock(productId, warehouseId);
    }

    @PostMapping("/create")
    public InventoryDto createInventory(@RequestBody InventoryRequest request){
        return inventoryService.createInventory(request);
    }

//    @PostMapping("/dispatch")
//    public InventoryDto dispatchItem(
//            @RequestParam Integer productId,
//            @RequestParam Integer warehouseId,
//            @RequestParam Integer quantity
//    ){
//        return inventoryService.dispatchItem(productId, warehouseId, quantity);
//    }


}
