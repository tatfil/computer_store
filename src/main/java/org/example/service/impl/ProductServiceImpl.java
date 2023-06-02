package org.example.service.impl;

import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.RequiredArgsConstructor;
import org.example.model.dto.request.ProductCreationDto;
import org.example.model.dto.request.ProductDto;
import org.example.model.entity.Category;
import org.example.model.entity.Product;
import org.example.repository.ProductRepository;
import org.example.service.ProductService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Cannot find merch with id = %d ", id)));
    }

    @Override
    public Product addProduct(ProductCreationDto productCreationDto) {
        Product product = Product.builder()
                        .productFields(productCreationDto.getProductFields())
                        .price(productCreationDto.getPrice())
                        .brand(productCreationDto.getBrand())
                        .category(Category.valueOf(productCreationDto.getProductCategoryName()))
                        .name(productCreationDto.getName())
                        .build();

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(ProductDto productDto) throws JsonMappingException {

        Product product = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Incorrect product position id = %d transmitted", productDto.getId())));
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        product.setProductFields(productDto.getProductFields());
        product.setBrand(productDto.getBrand());
        product.setCategory(Category.valueOf(productDto.getProductCategoryName()));
        return productRepository.save(product);
    }

    @Override
    public List<Product> getByCategory(String category) {
        return productRepository.findByCategory(category);
    }
}
