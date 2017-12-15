Feature: Create coupons for Cars only
A coupon could be created for cars only

Background: User is log in as and admin and start creating a new coupon
Given I login in as an admin user
| Login                | Password  |
|admin@phptravels.com  | demoadmin |
When I go to the Coupon Manager screen
Then I create a coupon for all cars only

Scenario: Coupon is valid for car booking 
Given I start booking a car
When I use a coupon for car
Then The price should have a discount

Scenario: Coupon is not valid for hotel booking
Given I start booking a hotel
When I use a coupon for car
Then The coupon should not be valid

Scenario: Coupon is not valid for tour booking
Given I start booking a tour
When I use a coupon for car
Then The coupon should not be valid