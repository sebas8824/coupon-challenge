# Coupon service

This service enables the user to pick his favorite items and lets him know how many items can he buy with the coupon spending the max possible.

The service exposes a POST endpoint `/coupon` which receives the following header

` { "item_ids": ["MCO573728251","MCO573728252","MCO573728255","MCO573728253"
,"MCO573728255","MCO573728254","MCO573728257","MAR573728258"],
"amount": 1000000
}` 

Where `item_ids` is an array of product ids of **Mercado Libre** page and the `amount` is the value of the coupon where the items will be selected spending the max amount possible.

To run locally this application, please clone this repository and import it as a maven project, then run it at 
http://localhost:8080/coupon

Remember, is a POST method.

## Application explanation

Basically this API is built with spring-boot where the `CouponController`
exposes the `coupon` endpoint and this sends the request to the `PriceService` component.
In this `PriceService` component the application takes the array and creates a `stream`, applies 
a `distinct` operation and sequentially makes a http call using a web client built with `OkHttp`, which 
transforms the response to an object that has two fields `id` and `price`.

Then, back to the `PriceService`, you will find the `calculate` method that uses the signature:

`List<String> calculate(Map<String, Float> items, Float amount)` and basically this sorts the `items` per price in a descending
way using the stream sort method. After that it sums the item price and ensures that it doesn't go past the `amount`.

In this solution I added the use of Hazelcast in order to cache the response, and this could increase the performance, even though
the http calls are really slow, so I couldn't reach the goal of 10k requests per second.
