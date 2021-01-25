package com.mercadolibre.coupon.networking.client;

import com.mercadolibre.coupon.model.response.ItemResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class PriceClientTest {

    private PriceClient priceClient;

    @Before
    public void setUp() {
        priceClient = new PriceClient();
    }

    @Test
    public void returnValidResponseFromUrl() throws Exception {
        String itemId = "https://api.mercadolibre.com/items/MCO573728251";
        ItemResponse response = priceClient.fetchPriceByItemId(itemId);
        assertTrue(response instanceof ItemResponse);
        assertNotNull(response);
        assertNotNull(response.getPrice());
        assertNotNull(response.getId());
    }

    @Test
    public void returnResponseFromUrlWhenPriceNotPresent() throws Exception {
        String itemId = "https://api.mercadolibre.com/items/MCO573728252";
        ItemResponse response = priceClient.fetchPriceByItemId(itemId);
        assertTrue(response instanceof ItemResponse);
        assertNotNull(response);
        assertNull(response.getPrice());
        assertNotNull(response.getId());
    }

    @Test
    public void returnErrorResponseFromUrl() throws Exception{
        String itemId = "https://api.mercadolibre.com/items/000";
        ItemResponse response = priceClient.fetchPriceByItemId(itemId);
        assertTrue(response instanceof ItemResponse);
        assertNotNull(response);
        assertNull(response.getPrice());
        assertNull(response.getId());
    }

    @Test
    public void returnErrorResponseFromEmptyUrl() throws Exception{
        String itemId = "https://api.mercadolibre.com/items/";
        ItemResponse response = priceClient.fetchPriceByItemId(itemId);
        assertNull(response.getId());
        assertNull(response.getPrice());
    }

    @Test(expected = Exception.class)
    public void returnExceptionFromUrl() throws Exception{
        priceClient.fetchPriceByItemId("");
    }

}
