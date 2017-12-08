Feature: available_coupon

Scenario:
Create a new available coupon
Given user is on admin page
And  username as "admin@phptravels.com" And I provide password as "demoadmin" to login
When user enter coupon page
And user add a coupon available
Scenario:
Book a room with the coupon available
Given user log on initial page
When user book a room
Then user see the reserve booked

Scenario:
Disable coupon from admin page
When user disable the coupon used

Scenario:
Coupon is not available to use
And user book a new room with the coupon disable
Then invalid coupon message is displaying
