package com.mercadolibre.coupon.networking.client;

import com.google.gson.Gson;
import com.mercadolibre.coupon.model.response.ItemResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.stereotype.Component;

@Component
public class PriceClient {

    public ItemResponse fetchPriceByItemId(String itemUrlId) throws Exception {
        try {
            Request request = new Request.Builder()
                    .url(itemUrlId)
                    .build();
            return new Gson().fromJson(
                    new OkHttpClient().newCall(request).execute().body().string()
                    ,ItemResponse.class);
        }
        catch (Exception e) {
            throw new Exception("The service is not returning any response");
        }
    }
}
