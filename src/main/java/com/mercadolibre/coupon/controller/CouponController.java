package com.mercadolibre.coupon.controller;

import com.mercadolibre.coupon.model.Coupon;
import com.mercadolibre.coupon.networking.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CouponController {

    @Autowired
    private PriceService priceService;

    @PostMapping("/coupon")
    public ResponseEntity<Coupon> coupon(@RequestBody Coupon couponData) {
        Coupon response = priceService.getOptimizedCoupon(couponData);
        HttpStatus status = response != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(response, status);
    }

}
