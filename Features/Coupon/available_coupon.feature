@AvailableCoupon
Feature: available_coupon

Scenario:
Create a new available coupon
Given user is on admin page
And  username as "admin@phptravels.com" And I provide password as "demoadmin" to login
When user enter coupon page
And user add a coupon available
| 50 | 4 | Trip | 
Scenario:
Book a room with the coupon available
Given user log on initial page
| user@phptravels.com | demouser |
When user book a room
| Trip | 
Then user see the reserve booked

Scenario:
Disable coupon from admin page
Given user disable the coupon used
	| admin@phptravels.com | demoadmin |  Disable  |

Scenario:
Coupon is not available to use
Given user book a new room with the coupon disable
| user@phptravels.com | demouser | Trip | 
Then invalid coupon message is displaying
