package com.devesh.gsco.inventory;

import lombok.Data;

@Data
public class InventoryRequest {

    private String location;
    private int capacity;

    private String sku;
    private String name;
    private double weight;
    private double price;

    private Integer quantity;
}
