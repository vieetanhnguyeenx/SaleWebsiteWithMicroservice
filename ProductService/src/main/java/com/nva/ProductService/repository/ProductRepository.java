package com.nva.ProductService.repository;

import com.nva.ProductService.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
