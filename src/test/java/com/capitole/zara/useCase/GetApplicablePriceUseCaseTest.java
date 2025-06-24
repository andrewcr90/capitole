package com.capitole.zara.useCase;

import com.capitole.zara.application.GetApplicablePriceUseCase;
import com.capitole.zara.domain.Price;
import com.capitole.zara.domain.PriceRepository;
import com.capitole.zara.application.dto.PriceResponse;
import com.capitole.zara.domain.exception.PriceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@ExtendWith(MockitoExtension.class)
public class GetApplicablePriceUseCaseTest  {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private GetApplicablePriceUseCase useCase;

    private final Integer brandId = 1;
    private final Integer productId = 35455;


    @Test
    void test1_at10AM_day14() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 10, 0, 0);

        Price expected = new Price(1L, 1, applicationDate.minusHours(1),applicationDate.plusHours(2),
                35455, 1, 0, new BigDecimal("35.50"), "EUR");
        PriceResponse expectedReponse = new PriceResponse(1, 1,
                35455, applicationDate.minusHours(1), applicationDate.plusHours(2), new BigDecimal("35.50"));

        Mockito.when(priceRepository.findFirstApplicablePrice(brandId, productId, applicationDate))
                .thenReturn(Optional.of(expected));

        Optional<PriceResponse> result = useCase.getFirstPrice(brandId, productId, applicationDate);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(expectedReponse, result.get());
    }


    @Test
    void test2_at16PM_day14() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 16, 0, 0);
        List<Price> expectedList = List
                .of(  new Price(1L, 1, applicationDate.minusHours(5),applicationDate.plusHours(3),
                                35455, 1, 0, new BigDecimal("25.45"), "EUR"),

                        new Price(1L, 1, applicationDate.minusHours(1),applicationDate.plusHours(4),
                                35455, 1, 0, new BigDecimal("30.00"), "EUR")
        );

        Mockito.when(priceRepository.findAllApplicablePrices(brandId, productId, applicationDate))
                .thenReturn(expectedList);

        List<PriceResponse> result = useCase.getPrice(brandId, productId, applicationDate);
        Assertions.assertEquals(2, result.size());
    }

    @Test
    void test3_at21PM_day14() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 14, 21, 0, 0);
        Price expected =
        new Price(1L, 1, applicationDate.minusHours(2),applicationDate.plusHours(2),
                35455, 1, 0, new BigDecimal("38.95"), "EUR");

        Mockito.when(priceRepository.findFirstApplicablePrice(brandId, productId, applicationDate))
                .thenReturn(Optional.of(expected));

        Optional<PriceResponse> result = useCase.getFirstPrice(brandId, productId, applicationDate);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(expected.getPrice(), result.get().getPrice());
    }

    @Test
    void test4_at10AM_day15() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 15, 10, 0, 0);
        Price expected =   new Price(1L, 1, applicationDate.minusHours(3),applicationDate.plusHours(3),
                35455, 1, 0, new BigDecimal("35.50"), "EUR");

        Mockito.when(priceRepository.findFirstApplicablePrice(brandId, productId, applicationDate))
                .thenReturn(Optional.of(expected));

        Optional<PriceResponse> result = useCase.getFirstPrice(brandId, productId, applicationDate);
        Assertions.assertTrue(result.isPresent());
    }

    @Test
    void test5_at21PM_day16() {
        LocalDateTime applicationDate = LocalDateTime.of(2020, 6, 16, 21, 0, 0);
        Price expected =         new Price(1L, 1, applicationDate.minusHours(1),applicationDate.plusHours(1),
                1, 35455, 0, new BigDecimal("30.50"), "EUR");

        Mockito.when(priceRepository.findFirstApplicablePrice(brandId, productId, applicationDate))
                .thenReturn(Optional.of(expected));

        Optional<PriceResponse> result = useCase.getFirstPrice(brandId, productId, applicationDate);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(35455, result.get().getProductId());
    }
    @Test
    void shouldThrowException_WhenNoPriceApplies() {
        LocalDateTime date = LocalDateTime.of(2020, 7, 1, 0, 0);
        Mockito.when(priceRepository.findFirstApplicablePrice(brandId, productId, date))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(PriceNotFoundException.class, () ->
                useCase.getFirstPrice(brandId, productId, date));
    }


}
