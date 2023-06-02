package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.example.model.dto.request.ProductItemDto;
import org.example.model.entity.ProductItem;
import org.example.service.ProductItemService;
import org.example.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ProductItemController {

    StockService stockService;
    ProductItemService productItemService;

    @Operation(summary = "Add new product item")
    @ApiResponse(responseCode = "200", description = "Default product found correctly")
    @ApiResponse(responseCode = "403", description = "Your role doesn't have access")
    @ApiResponse(responseCode = "400", description = "Incorrect data transmitted")
    @PostMapping
    @Transactional
    public ResponseEntity<ProductItem> addProductItem(@Valid @RequestBody ProductItemDto productItemDto) {

        ProductItem productItem = productItemService.addItem(productItemDto);
        stockService.addItems(productItem.getId(), productItemDto.getQuantity());
        return ResponseEntity.ok(productItem);
    }
}
