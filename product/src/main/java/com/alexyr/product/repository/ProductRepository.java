package com.alexyr.product.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.alexyr.product.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Long>{
    
}
