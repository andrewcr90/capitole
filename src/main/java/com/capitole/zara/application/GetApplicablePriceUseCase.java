package com.capitole.zara.application;

import com.capitole.zara.domain.Price;
import com.capitole.zara.domain.PriceRepository;
import com.capitole.zara.application.dto.PriceResponse;
import com.capitole.zara.domain.exception.PriceNotFoundException;
import com.capitole.zara.shared.AppConstants;
import com.capitole.zara.shared.PriceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class GetApplicablePriceUseCase {
    @Autowired
    private final PriceRepository repo;

    public GetApplicablePriceUseCase(PriceRepository repo) {
        this.repo = repo;
    }

    public List<PriceResponse> getPrice(Integer brandId, Integer productId, LocalDateTime applicationDate) {
        List<Price> prices =  repo.findAllApplicablePrices(brandId, productId, applicationDate);
        log.info("GetPirce UseCase" + prices.stream().findFirst());
        return prices.stream()
                .map(PriceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<PriceResponse> getFirstPrice(Integer brandId, Integer productId, LocalDateTime date) {
        log.info("GetPirce UseCase getFirst" +productId);
        return Optional.ofNullable(repo.findFirstApplicablePrice(brandId, productId, date).map(PriceMapper::toDTO)
                .orElseThrow(() -> new PriceNotFoundException(AppConstants.ERROR_PRICE_NOT_FOUND)));


    }


}

