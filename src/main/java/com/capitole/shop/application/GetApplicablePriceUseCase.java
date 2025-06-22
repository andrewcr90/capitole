package com.capitole.shop.application;

import com.capitole.shop.domain.Price;
import com.capitole.shop.domain.PriceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GetApplicablePriceUseCase {
    private final PriceRepository repo;

    public GetApplicablePriceUseCase(PriceRepository repo) {
        this.repo = repo;
    }

    public List<Price> getPrice(Integer brandId, Integer productId, LocalDateTime applicationDate) {
        return repo.findAllApplicablePrices(brandId, productId, applicationDate);
    }

    public Optional<Price> getFirstPrice(Integer brandId, Integer productId, LocalDateTime date) {
        return repo.findFirstApplicablePrice(brandId, productId, date);
    }


}

