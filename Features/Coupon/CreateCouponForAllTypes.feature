Feature: Create coupons for all types
A coupon could be created for all types only

Background: User is log in as and admin and start creating a new coupon
Given I login in as an admin user
| Login                | Password  |
|admin@phptravels.com  | demoadmin |
When I go to the Coupon Manager screen
Then I create a coupon for all types

Scenario: Coupon is valid for hotel booking 
Given I start booking a hotel
When I use a coupon for all types
Then The price should have a discount

Scenario: Coupon is valid for tours booking
Given I start booking a tour
When I use a coupon for all types
Then The price should have a discount

Scenario: Coupon is valid for cars booking
Given I start booking a car
When I use a coupon for all types
Then The price should have a discount