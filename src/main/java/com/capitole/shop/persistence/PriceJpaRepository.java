package com.capitole.shop.persistence;

import com.capitole.shop.infraestructure.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {

    @Query("""
        SELECT p FROM PriceEntity p
        WHERE p.brandId = :brandId
          AND p.productId = :productId
          AND :applicationDate BETWEEN p.startDate AND p.endDate
        ORDER BY p.priority DESC
    """)
    List<PriceEntity> findAllMatchingPrices(@Param("brandId") Integer brandId,
                                            @Param("productId") Integer productId,
                                            @Param("applicationDate") LocalDateTime applicationDate);
}
