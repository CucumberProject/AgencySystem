@createCouponsForToursOnly
Feature: Create coupons for Tours only
A coupon could be created for tours only

Background: User is log in as and admin and create a coupon for Tours only
Given I login in as an admin user
| Login                | Password  |
|admin@phptravels.com  | demoadmin |
When Open the Coupon Manager screen
And I create a coupon for all tours only
|Discount | Max Limit | Code             |
| 50      | 5         | testCoupon_tour |
Then A coupon for all Tours should be created


Scenario: Coupon is valid for tour booking 
Given I log in as a user
| Login                | Password  |
|user@phptravels.com  | demouser |
When I start booking a tour
And I use a coupon as "testCoupon_tour" for tour
Then The price of tour should have a discount

Scenario: Coupon is not valid for hotel booking
Given I log in as a user
| Login                | Password  |
|user@phptravels.com  | demouser |
When I start booking a hotel
And I use a coupon for tour as "testCoupon_tour" in a tour
Then The coupon should not be valid for hotel

Scenario: Coupon is not valid for cars booking
Given I log in as a user
| Login                | Password  |
|user@phptravels.com  | demouser |
When I start booking a car
And I use a coupon for tour as "testCoupon_tour" in a car
Then The coupon should not be valid for car