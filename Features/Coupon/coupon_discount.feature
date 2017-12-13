Feature: coupon_discount

Scenario:
A new coupon is created
Given I am on admin home page
When I provide username as "admin@phptravels.com" And I provide password as "demoadmin" to login
When user select coupon page
And user add two new coupons with a discount
	| 40 | 2 | Promo1 |
	| 70 | 5 | Promo2 |
Then coupons should are visible

Scenario:
Coupon discount is added to the total
Given user enter account initial page
	| user@phptravels.com | demouser |
And coupon promo1 is used
	|Promo1|
And coupon promo2 is used
	|Promo2|
Then both coupon were used correctly
