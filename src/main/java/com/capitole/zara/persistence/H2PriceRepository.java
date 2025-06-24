package com.capitole.zara.persistence;


import com.capitole.zara.domain.Price;
import com.capitole.zara.domain.PriceRepository;
import com.capitole.zara.shared.PriceMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class H2PriceRepository implements PriceRepository {

    private final PriceJpaRepository priceJpaRepository;

    public H2PriceRepository(PriceJpaRepository priceJpaRepository) {
        this.priceJpaRepository = priceJpaRepository;
    }
    @Override
    public List<Price> findAllApplicablePrices(Integer brandId, Integer productId, LocalDateTime date) {
        return priceJpaRepository.findAllMatchingPrices(brandId, productId, date)
                .stream()
                .map(PriceMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Price> findFirstApplicablePrice(Integer brandId, Integer productId, LocalDateTime date) {
        return priceJpaRepository.findAllMatchingPrices(brandId, productId, date)
                .stream()
                .findFirst()
                .map(PriceMapper::toDomain);
    }



}