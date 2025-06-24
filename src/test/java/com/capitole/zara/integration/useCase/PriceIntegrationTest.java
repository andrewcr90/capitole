package com.capitole.zara.integration.useCase;

import com.capitole.zara.infraestructure.BrandEntity;
import com.capitole.zara.infraestructure.PriceEntity;
import com.capitole.zara.persistence.PriceJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PriceIntegrationMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PriceJpaRepository priceJpaRepository;

    @BeforeEach
    void setUp() {
        priceJpaRepository.deleteAll();

        // Precio con menor prioridad
        PriceEntity low = new PriceEntity();
        low.setBrand(BrandEntity.builder().id(1).build());
        low.setProductId(35455);
        low.setPriceList(1);
        low.setStartDate(LocalDateTime.parse("2020-06-14T00:00:00"));
        low.setEndDate(LocalDateTime.parse("2020-12-31T23:59:59"));
        low.setPriority(1);
        low.setPrice(BigDecimal.valueOf(35.50));
        low.setCurrency("EUR");
        priceJpaRepository.save(low);

        // Precio con mayor prioridad
        PriceEntity high = new PriceEntity();
        high.setBrand(BrandEntity.builder().id(1).build());
        high.setProductId(35455);
        high.setPriceList(2);
        high.setStartDate(LocalDateTime.parse("2020-06-14T00:00:00"));
        high.setEndDate(LocalDateTime.parse("2020-12-31T23:59:59"));
        high.setPriority(2);
        high.setPrice(BigDecimal.valueOf(60.00));
        high.setCurrency("EUR");
        priceJpaRepository.save(high);
    }

    @Test
    void shouldReturnPriceWithHighestPriority() throws Exception {
        mockMvc.perform(get("/api/v1/prices/getPrice")
                        .param("brandId", "1")
                        .param("productId", "35455")
                        .param("applicationDate", "2020-06-14T16:00:00")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value(35455))
                .andExpect(jsonPath("$.brandId").value(1))
                .andExpect(jsonPath("$.priceList").value(2))
                .andExpect(jsonPath("$.price").value(60.00))
                .andExpect(jsonPath("$.startDate").exists())
                .andExpect(jsonPath("$.endDate").exists());
    }
}
