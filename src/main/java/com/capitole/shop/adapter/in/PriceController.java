package com.capitole.shop.adapter.in;

import com.capitole.shop.application.GetApplicablePriceUseCase;
import com.capitole.shop.domain.Price;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/prices")
public class PriceController {

    private final GetApplicablePriceUseCase useCase;

    public PriceController(GetApplicablePriceUseCase useCase) {
        this.useCase = useCase;
    }


    @GetMapping("/getPrice")
    public ResponseEntity<List<Price>> getPrice(@RequestParam Integer brandId,
                                                    @RequestParam Integer productId,
                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {
        List<Price> prices = useCase.getPrice(brandId, productId, applicationDate);
        return ResponseEntity.ok(prices);
    }

    @GetMapping
    public ResponseEntity<Price> getFirstPrice(
            @RequestParam Integer brandId,
            @RequestParam Integer productId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate
    ) {
        return useCase.getFirstPrice(brandId, productId, applicationDate)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}

