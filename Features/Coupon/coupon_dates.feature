@CouponDates
Feature: coupon_dates

@CreateNewCoupons
Scenario:
Given User entar to admin page
And username and password are provideted
	| admin@phptravels.com | demoadmin |
When user enter to coupon page
And user add two coupos one with valid dates and another with invalid dates
	| 30 | 6 | 1/12/2017 | 30/12/2017 | Coupon1 | 
	| 15 | 5 | 1/01/2018 | 15/01/2018 | Coupon2 | 
Then both coupons are created successfuly

@UseBothCoupons
Scenario:
Given user enter tu user page
	| user@phptravels.com | demouser |
And user book a hotel with the valid coupon
	| Coupon1 |
And user try to use the invalid coupon
	| Coupon2 |
Then user receives a message
