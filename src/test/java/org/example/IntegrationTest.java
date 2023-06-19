package org.example;

import org.example.model.dto.request.ProductCreationDto;
import org.example.model.dto.request.ProductDto;
import org.example.model.entity.Category;
import org.example.model.entity.Product;
import org.example.repository.ProductRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import org.springframework.web.client.RestTemplate;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

    @LocalServerPort
    private int port;
    private  static RestTemplate restTemplate;

    @Autowired
    ProductRepository productRepository;

    private String baseUrl = "http://localhost";
    private Product product;

    @BeforeAll
    public static void init(){
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setup(){
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/products/");

        product = Product.builder()
                .id(1L)
                .name("computer")
                .brand("Acer")
                .category(Category.COMPUTER)
                .price(100)
                .build();
    }

    @Test
    @Sql(statements = "INSERT INTO product (product_id, name, brand, price) VALUES (1, 'computer', 'Acer', 100)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM product WHERE product_id=1", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void findProductById() {
        ResponseEntity<Product> response = restTemplate.getForEntity(baseUrl + "/{productId}", Product.class, 1L);
        assertEquals(product.getBrand(), response.getBody().getBrand());
        assertEquals(1, productRepository.findAll().size());
    }

    @Test
    @Sql(statements = "INSERT INTO product (product_id, name, brand, price) VALUES (1, 'computer', 'Acer', 100)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM product WHERE product_id=1", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testFindProductById() {
        Product response = restTemplate.getForObject(baseUrl + "/{productId}", Product.class, 1);
        assertAll(
                () -> assertNotNull(response),
                () -> assertEquals(1, response.getId()),
                () -> assertEquals(product.getName(), response.getName())
        );
        assertEquals(1, productRepository.findAll().size());
    }

    @Test
    public void testAddProduct() {
        ProductCreationDto productCreationDto = ProductCreationDto.builder()
                .name(product.getName())
                .brand(product.getBrand())
                .price(product.getPrice())
                .productCategoryName(product.getCategory().name())
                .build();

        ResponseEntity<Product> response = restTemplate.postForEntity(baseUrl, productCreationDto, Product.class);
        assertEquals(product.getName(), response.getBody().getName());
        assertNotNull( productRepository.findById(response.getBody().getId()));
    }

    @Test
    @Sql(statements = "INSERT INTO product (product_id, name, brand, price) VALUES (1, 'computer', 'Acer', 100)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM product WHERE product_id=1", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testUpdateProduct(){
        ProductDto productDto = ProductDto.builder()
                .id(1)
                .name("monitor")
                .brand("Sony")
                .productCategoryName("MONITOR")
                .price(50)
                .build();
        restTemplate.put(baseUrl, productDto);
        Product productFromDB = productRepository.findById(1L).get();
        assertAll(
                () -> assertNotNull(productFromDB),
                () -> assertEquals(productDto.getPrice(), productFromDB.getPrice())
        );
    }

    @Test
    @Disabled
    @Sql(statements = "INSERT INTO product (product_id, name, brand, price) VALUES (1, 'computer', 'Acer', 100)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    public void testDeleteProduct(){
        int recordCount=productRepository.findAll().size();
        assertEquals(1, recordCount);
        restTemplate.delete(baseUrl, 1);
        assertEquals(0, productRepository.findAll().size());

    }

}
