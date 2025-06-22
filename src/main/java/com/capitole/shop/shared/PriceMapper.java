package com.capitole.shop.shared;

import com.capitole.shop.domain.Price;
import com.capitole.shop.infraestructure.PriceEntity;

public final class PriceMapper {

    private PriceMapper() {}

    public static Price toDomain(PriceEntity entity) {
        if (entity == null) return null;

        return Price.builder().id(entity.getId())
        .brandId(entity.getBrandId())
        .productId(entity.getProductId())
        .priceList(entity.getPriceList())
        .priority(entity.getPriority())
        .price(entity.getPrice())
        .currency(entity.getCurrency())
        .startDate(entity.getStartDate())
        .endDate(entity.getEndDate())
                .build();

    }

    public static PriceEntity toEntity(Price price) {
        if (price == null) return null;
    return PriceEntity.builder().id(price.getId())
        .brandId(price.getBrandId())
                .productId(price.getProductId())
                .priceList(price.getPriceList())
                .priority(price.getPriority())
                .price(price.getPrice())
                .currency(price.getCurrency())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .build();

    }
}