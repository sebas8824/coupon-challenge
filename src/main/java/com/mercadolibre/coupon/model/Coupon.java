package com.mercadolibre.coupon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Coupon {
    private List<String> item_ids;
    private Float amount;
}
