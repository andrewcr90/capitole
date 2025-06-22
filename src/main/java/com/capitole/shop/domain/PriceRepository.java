package com.capitole.shop.domain;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

public interface PriceRepository {

    /**
     * Busca la tarifa aplicable para una marca, producto y fecha específica.
     *
     * @param brandId ID de la cadena/marca (ej. ZARA = 1)
     * @param productId ID del producto
     * @param applicationDate Fecha y hora en la que se aplica la tarifa
     * @return Precio aplicable (si existe), envuelto en Optional
     */
    List<Price> findAllApplicablePrices(Integer brandId, Integer productId, LocalDateTime applicationDate);

    Optional<Price> findFirstApplicablePrice(Integer brandId, Integer productId, LocalDateTime applicationDate);


}
