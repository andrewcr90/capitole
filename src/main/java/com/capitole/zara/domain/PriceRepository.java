package com.capitole.zara.domain;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

public interface PriceRepository {

    /**
     * Retorna todas las tarifas aplicables ordenadas por prioridad descendente.
     */
    List<Price> findAllApplicablePrices(Integer brandId, Integer productId, LocalDateTime applicationDate);

    Optional<Price> findFirstApplicablePrice(Integer brandId, Integer productId, LocalDateTime applicationDate);

}
