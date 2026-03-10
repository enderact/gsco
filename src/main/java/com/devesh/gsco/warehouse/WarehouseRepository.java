package com.devesh.gsco.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehouse,Integer> {
    @Query("SELECT w.location FROM Warehouse w WHERE w.warehouseId = :id")
    String findLocationById(Integer id);
    @Query("SELECT w.capacity FROM Warehouse w WHERE w.warehouseId = :id")
    Integer findCapacityById(Integer id);
}
