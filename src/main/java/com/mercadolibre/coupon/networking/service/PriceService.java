package com.mercadolibre.coupon.networking.service;

import com.mercadolibre.coupon.model.Coupon;

public interface PriceService {
    Coupon getOptimizedCoupon(Coupon coupon);
}
