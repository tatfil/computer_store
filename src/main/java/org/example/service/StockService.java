package org.example.service;

public interface StockService {
    void setQuantity(Long id, Long quantity);
    void addItems(Long itemId, Long quantity);
}
