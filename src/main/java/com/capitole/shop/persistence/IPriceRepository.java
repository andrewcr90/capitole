package com.capitole.shop.persistence;

import com.capitole.shop.infraestructure.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPriceRepository extends JpaRepository<PriceEntity, Long> {
}