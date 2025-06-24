package com.capitole.zara.shared;

import com.capitole.zara.domain.Price;
import com.capitole.zara.application.dto.PriceResponse;
import com.capitole.zara.infraestructure.BrandEntity;
import com.capitole.zara.infraestructure.PriceEntity;

public final class PriceMapper {

    private PriceMapper() {}

    public static Price toDomain(PriceEntity entity) {
        if (entity == null) return null;

        return Price.builder().id(entity.getId())
        .brandId(entity.getBrand().getId())
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
        .brand(BrandEntity.builder().id(price.getBrandId()).build())
                .productId(price.getProductId())
                .priceList(price.getPriceList())
                .priority(price.getPriority())
                .price(price.getPrice())
                .currency(price.getCurrency())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .build();

    }

        public static PriceResponse toDTO(Price price) {
            return PriceResponse.builder().brandId(price.getBrandId())
                .productId(price.getProductId())
                .priceList(price.getPriceList())
                .price(price.getPrice())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .build();
        }

}