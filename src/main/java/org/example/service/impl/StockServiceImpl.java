package org.example.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.model.entity.Stock;
import org.example.repository.StockRepository;
import org.example.service.StockService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    @Override
    public void setQuantity(Long id, Long quantity) {

    }

    @Override
    public void addItems(Long item_id, Long quantity) {
//        Stock stock = Stock.builder()
//                .
//                .build();
//
//        stockRepository.save(new Stock(item_id, ));

    }
}
