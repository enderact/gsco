package com.devesh.gsco.warehouse;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    private final WarehouseService warehouseService;

    @GetMapping("/{id}/location")
    public String getLocation(@PathVariable Integer id) {
        return warehouseService.getWarehouseLocation(id);
    }

    @GetMapping("/{id}/capacity")
    public Integer getCapacity(@PathVariable Integer id) {
        return warehouseService.getCapacity(id);
    }
}
