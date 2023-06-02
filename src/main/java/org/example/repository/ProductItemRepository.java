package org.example.repository;

import org.example.model.entity.ProductItem;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductItemRepository extends JpaRepository<ProductItem, Long> {
}
