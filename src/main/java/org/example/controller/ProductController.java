package org.example.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.example.model.dto.request.ProductCreationDto;
import org.example.model.dto.request.ProductDto;
import org.example.model.entity.Product;
import org.example.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @Operation(summary = "Add new product card")
    @ApiResponse(responseCode = "200", description = "Default product found correctly")
    @ApiResponse(responseCode = "403", description = "Your role doesn't have access")
    @ApiResponse(responseCode = "400", description = "Incorrect data transmitted")
    @PostMapping
    @Transactional
    public ResponseEntity<Product> addProduct(@Valid @RequestBody ProductCreationDto productDto) {

        Product product = productService.addProduct(productDto);
        productService.addProduct(productDto);
        return ResponseEntity.ok(product);
    }

    @Operation(summary = "Update existing product card", description = "Id parameter is required, other will be changed if there is not null fields")
    @ApiResponse(responseCode = "200", description = "Default product updated correctly")
    @ApiResponse(responseCode = "403", description = "Your role doesn't have access")
    @ApiResponse(responseCode = "400", description = "Incorrect data transmitted")
    @PutMapping
    @Transactional
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody ProductDto productDto) throws JsonMappingException {
        Product updatedProduct = productService.updateProduct(productDto);
        return ResponseEntity.ok(updatedProduct);
    }

    @Operation(summary = "Get product card by id")
    @ApiResponse(responseCode = "200", description = "Product found correctly")
    @ApiResponse(responseCode = "403", description = "Your role doesn't have access")
    @ApiResponse(responseCode = "400", description = "Incorrect data transmitted")
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId
    ) {
        return ResponseEntity.ok(productService.findById(productId));
    }

    @Operation(summary = "Get product cards by category")
    @ApiResponse(responseCode = "200", description = "Product found correctly")
    @ApiResponse(responseCode = "400", description = "Incorrect data transmitted")
    @ApiResponse(responseCode = "403", description = "Your role doesn't have access")
    @GetMapping
    public ResponseEntity<List<Product>> getProductByCategory(@RequestParam("category") String category) {
        return ResponseEntity.ok(productService.getByCategory(category));
    }
}
