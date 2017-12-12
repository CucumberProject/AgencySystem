Feature: use_coupon

@useCoupon_createcoupn
Scenario:
A New system has been released to book hotels, car and tours.
Given I am on admin login home page
When I provide username and password
	| admin@phptravels.com | demoadmin |
And User go to the coupon page
	| 75 | 2 | Test | 
Then User add a new coupon


@useCoupon_maxLimit
Scenario: 
Successful use the coupon
	Given User is on home Page
	When User enter to login page
	And user enters username and password
    | user@phptravels.com | demouser |
    When user book a new hotel with a coupon
    | Test |
	And user wants to use a coup over of its max limit
	| Test |
	Then message of invalid coupon is successufully displayed
