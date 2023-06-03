package org.example.repository;

import org.example.model.entity.Category;
import org.example.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where p.category = ?1")
    List<Product> findByCategory(Category category);

}
