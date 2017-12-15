Feature: Create coupons for Tours only
A coupon could be created for tours only

Background: User is log in as and admin and start creating a new coupon
Given I login in as an admin user
| Login                | Password  |
|admin@phptravels.com  | demoadmin |
When I go to the Coupon Manager screen
Then I create a coupon for all tours only

Scenario: Coupon is valid for tour booking 
Given I start booking a tour
When I use a coupon for tour
Then The price should have a discount

Scenario: Coupon is not valid for hotel booking
Given I start booking a hotel
When I use a coupon for tour
Then The coupon should not be valid

Scenario: Coupon is not valid for cars booking
Given I start booking a car
When I use a coupon for tour
Then The coupon should not be valid