package com.mercadolibre.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = { "com.mercadolibre.coupon.*" })
@EnableCaching
@EnableAutoConfiguration
public class CouponApplication {

	public static void main(String[] args) { SpringApplication.run(CouponApplication.class, args); }

}
