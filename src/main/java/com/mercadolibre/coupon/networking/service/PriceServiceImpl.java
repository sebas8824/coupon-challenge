package com.mercadolibre.coupon.networking.service;

import com.mercadolibre.coupon.model.Coupon;
import com.mercadolibre.coupon.model.response.ItemResponse;
import com.mercadolibre.coupon.networking.client.PriceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PriceServiceImpl implements PriceService {

    @Autowired
    private PriceClient priceClient;

    @Override
    @Cacheable(value = "coupons")
    public Coupon getOptimizedCoupon(Coupon coupon) {
        String url = "https://api.mercadolibre.com/items/";
        Map<String, Float> items = new HashMap<>();

        coupon.getItem_ids().stream()
                .distinct()
                .sequential()
                .forEach(item -> {
                    try {
                        ItemResponse itemResponse = priceClient.fetchPriceByItemId(url + item);
                        if(itemResponse.getPrice() != null)
                            items.put(itemResponse.getId(), itemResponse.getPrice());
                    } catch (Exception e) {
                        System.out.println("Error getting item: " + item + " .Exception: " + e.getCause());
                    }
                });
        return new Coupon(calculate(items, coupon.getAmount()), coupon.getAmount());
    }

    List<String> calculate(Map<String, Float> items, Float amount) {
        float cumulativeAmount = 0f;
        List<String> result = new ArrayList<>();
        List<Map.Entry<String, Float>> entryList = items.entrySet()
                .stream()
                .sorted((item1, item2) -> item2.getValue().compareTo(item1.getValue()))
                .collect(Collectors.toList());

        for(int i=0; i < entryList.size(); i++) {
            cumulativeAmount += entryList.get(i).getValue();
            if(cumulativeAmount < amount ) {
                result.add(entryList.get(i).getKey());
            } else {
                cumulativeAmount -= entryList.get(i).getValue();
            }
        }

        return result;
    }
}
