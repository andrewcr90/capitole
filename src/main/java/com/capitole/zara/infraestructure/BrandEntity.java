package com.capitole.zara.infraestructure;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "brand")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandEntity {

    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;
}
