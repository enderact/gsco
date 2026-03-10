package com.devesh.gsco.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Double getPrice(Integer id){
        return productRepository.findPriceById(id);
    }
}
