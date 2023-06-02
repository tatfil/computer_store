package org.example.service;

import org.example.model.dto.request.ProductItemDto;
import org.example.model.entity.ProductItem;

public interface ProductItemService {

    ProductItem addItem(ProductItemDto productItemDto);

}
