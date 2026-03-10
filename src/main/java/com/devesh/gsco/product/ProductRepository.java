package com.devesh.gsco.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p.price FROM Product p WHERE p.productId = :id")
    Double findPriceById(Integer id);
}
