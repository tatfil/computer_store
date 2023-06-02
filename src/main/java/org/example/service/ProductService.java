package org.example.service;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.example.model.dto.request.ProductCreationDto;
import org.example.model.dto.request.ProductDto;
import org.example.model.entity.Product;

import java.util.List;

public interface ProductService {
    Product findById(Long id);

    Product addProduct(ProductCreationDto product);

    Product updateProduct(ProductDto productDto) throws JsonMappingException;

    List<Product> getByCategory(String category);

}
