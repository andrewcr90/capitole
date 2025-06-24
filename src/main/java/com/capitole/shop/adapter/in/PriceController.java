package com.capitole.shop.adapter.in;

import com.capitole.shop.application.GetApplicablePriceUseCase;
import com.capitole.shop.application.dto.PriceResponse;
import com.capitole.shop.domain.exception.PriceNotFoundException;
import com.capitole.shop.shared.AppConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/prices")
public class PriceController {

    private final GetApplicablePriceUseCase useCase;

    public PriceController(GetApplicablePriceUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public ResponseEntity<List<PriceResponse>> getPrice(
            @RequestParam Integer brandId,
            @RequestParam Integer productId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {
        List<PriceResponse> prices = useCase.getPrice(brandId, productId, applicationDate);
        if (prices.isEmpty()) {
            throw new PriceNotFoundException(AppConstants.ERROR_PRICE_NOT_FOUND);

        }
        return ResponseEntity.ok(prices);
    }

    @GetMapping("/getPrice")
    public ResponseEntity<PriceResponse> getFirstPrice(
            @RequestParam Integer brandId,

            @RequestParam Integer productId,

            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {
        log.info("GetFirst Price");
        return useCase.getFirstPrice(brandId, productId, applicationDate)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new PriceNotFoundException(AppConstants.ERROR_PRICE_NOT_FOUND));

    }


}

