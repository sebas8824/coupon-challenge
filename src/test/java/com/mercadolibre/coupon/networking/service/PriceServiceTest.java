package com.mercadolibre.coupon.networking.service;

import com.mercadolibre.coupon.model.Coupon;
import com.mercadolibre.coupon.networking.client.PriceClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class PriceServiceTest {

    @InjectMocks
    private PriceService priceService = new PriceServiceImpl();

    @Spy
    private PriceClient priceClient;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testValidRequestResponse() throws Exception {

        Coupon request = new Coupon(List.of("MCO573728251",
                "MCO573728252",
                "MCO573728255",
                "MCO573728253",
                "MCO573728255",
                "MCO573728254",
                "MCO573728257",
                "MAR573728258"), 1000000f);
        Coupon response = priceService.getOptimizedCoupon(request);
        assertEquals(4, response.getItem_ids().size());
        assertTrue(response.getItem_ids().contains("MCO573728251"));
        assertFalse(response.getItem_ids().contains("MCO573728252"));
    }

    @Test
    public void testValidRequestEmptyResponseWhenAmountAndItemsAreZero() {
        Coupon request = new Coupon(List.of(), 0f);
        Coupon response = priceService.getOptimizedCoupon(request);
        assertEquals(0, response.getItem_ids().size());
        assertTrue(response.getAmount() == 0f);
    }

    @Test
    public void testMalformedParameters() {
        Coupon request = new Coupon(List.of("MCO573728251", "&http://www.MCO5%7372/825:2"), 1000000f);
        Coupon response = priceService.getOptimizedCoupon(request);
        assertEquals(1, response.getItem_ids().size());
        assertFalse(response.getItem_ids().contains("&http://www.MCO5%7372/825:2"));
    }
}
