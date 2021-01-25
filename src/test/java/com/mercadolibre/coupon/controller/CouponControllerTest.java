package com.mercadolibre.coupon.controller;

import com.mercadolibre.coupon.model.Coupon;
import com.mercadolibre.coupon.networking.client.PriceClient;
import com.mercadolibre.coupon.networking.service.PriceService;
import com.mercadolibre.coupon.networking.service.PriceServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
public class CouponControllerTest {

    @InjectMocks
    private CouponController couponController;

    @Mock
    private PriceService priceService = new PriceServiceImpl();

    @Mock
    private PriceClient priceClient;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getCoupon() {
        ResponseEntity<Coupon> response = couponController.coupon(new Coupon(List.of("MCO573728251"), 1000000f));
        assertTrue(response.getStatusCode().equals(HttpStatus.NOT_FOUND));
    }
}
