Feature: coupon_discount

Scenario:
A new coupon is created
Given I am on admin home page
When I provide username as "admin@phptravels.com" And I provide password as "demoadmin" to login
When user select coupon page
And user add two new coupons with a discount
Then coupons should are visible

Scenario:
Coupon discount is added to the total
Given user enter account initial page
And coupon created are used
Then the discount of both coupons are available
