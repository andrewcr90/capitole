package com.capitole.zara.adapter.in;

import com.capitole.zara.application.GetApplicablePriceUseCase;
import com.capitole.zara.application.dto.PriceResponse;
import com.capitole.zara.domain.exception.PriceNotFoundException;
import com.capitole.zara.shared.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Price", description = "Endpoints for price queries")
public class PriceController {

    private final GetApplicablePriceUseCase useCase;

    public PriceController(GetApplicablePriceUseCase useCase) {
        this.useCase = useCase;
    }

    @Operation(summary = "Get ALl the applicable price", description = "Returns the list of applicable price for a given brand, product, and application date")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Applicable price found"),
            @ApiResponse(responseCode = "404", description = "No applicable price found"),
            @ApiResponse(responseCode = "500", description = "Unexpected error")
    }) @GetMapping
    public ResponseEntity<List<PriceResponse>> getPrice(
            @Parameter(name = "brandId", description = "Brand ID", example = "1", required = true)
            @RequestParam Integer brandId,

            @Parameter(name = "productId", description = "Product ID", example = "35455", required = true)
            @RequestParam Integer productId,

            @Parameter(name = "applicationDate", description = "Date and time for price application", example = "2020-06-14T16:00:00", required = true)
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate) {
        List<PriceResponse> prices = useCase.getPrice(brandId, productId, applicationDate);
        if (prices.isEmpty()) {
            throw new PriceNotFoundException(AppConstants.ERROR_PRICE_NOT_FOUND);

        }
        return ResponseEntity.ok(prices);
    }

    @Operation(summary = "Get the applicable price", description = "Returns the applicable price for a given brand, product, and application date")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Applicable price found"),
            @ApiResponse(responseCode = "404", description = "No applicable price found"),
            @ApiResponse(responseCode = "500", description = "Unexpected error")
    })

    @GetMapping("/getPrice")
    public ResponseEntity<PriceResponse> getFirstPrice(
            @Parameter(name = "brandId", description = "Brand ID", example = "1", required = true)
            @RequestParam Integer brandId,

            @Parameter(name = "productId", description = "Product ID", example = "35455", required = true)
            @RequestParam Integer productId,

            @Parameter(name = "applicationDate", description = "Date and time for price application", example = "2020-06-14T16:00:00", required = true)
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime applicationDate)  {
        log.info("GetFirst Price");
        return useCase.getFirstPrice(brandId, productId, applicationDate)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new PriceNotFoundException(AppConstants.ERROR_PRICE_NOT_FOUND));

    }


}

