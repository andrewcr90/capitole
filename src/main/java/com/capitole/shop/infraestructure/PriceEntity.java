package com.capitole.shop.infraestructure;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "prices")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PriceEntity {

    @Id
    private Long id;

    private Integer brandId;
    private Integer productId;
    private Integer priceList;
    private Integer priority;
    private BigDecimal price;
    private String currency;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
