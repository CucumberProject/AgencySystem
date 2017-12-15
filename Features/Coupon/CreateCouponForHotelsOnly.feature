@createCouponsForHotelsOnly
Feature: Create coupons for hotels only
A coupon could be created for hotels only

Background: User is log in as an admin and create a coupon for hotels only
Given I log in as an admin user
| Login                | Password  |
|admin@phptravels.com  | demoadmin |
When I go to the Coupon Manager screen
And I create a coupon for all hotels only
|Discount | Max Limit | Code             |
| 50      | 5         | testCoupon_hotel |
Then A coupon for all hotels should be created


Scenario: Coupon is valid for hotel booking 
Given I log in as a user
| Login                | Password  |
|user@phptravels.com  | demouser |
When I start booking a hotel
And I use a coupon as "testCoupon_hotel" for hotel
Then The price should have a discount

Scenario: Coupon is not valid for tours booking
Given I log in as a user
| Login                | Password  |
|user@phptravels.com  | demouser |
When I start booking a tour
And I use a coupon for hotel as "testCoupon_hotel" in a tour
Then The coupon should not be valid for tour

Scenario: Coupon is not valid for cars booking
Given I log in as a user
| Login                | Password  |
|user@phptravels.com  | demouser |
When I start booking a car
And I use a coupon for hotel as "testCoupon_hotel" in a car
Then The coupon should not be valid for car
