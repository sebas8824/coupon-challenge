# Coupon service

This service enables the user to pick his favorite items and lets him know how many items can he buy with the coupon spending the max possible.

The service exposes a POST endpoint `/coupon` which receives the following header

` { "item_ids": ["MCO573728251","MCO573728252","MCO573728255","MCO573728253"
,"MCO573728255","MCO573728254","MCO573728257","MAR573728258"],
"amount": 1000000
}` 

Where `item_ids` is an array of product ids of **Mercado Libre** page and the `amount` is the value of the coupon where the items will be selected spending the max amount possible.

To run locally this application, please clone this repository and import it as a maven project, then run it.

Docker image available at https://hub.docker.com/r/sebas8824/coupon

The service is running in AWS in the following url 
http://ec2-15-228-36-37.sa-east-1.compute.amazonaws.com:8080/coupon