Feature: use_coupon

Background: 
Successful log in with valid credential
	Given User is on home Page
	When User enter to login page
	And user enters username and password

Scenario: 
User should be able to booked hotels a max limit with a coupon
	Given user book a new hotel with a coupon
	When user wants to use a coup over of its max limit
	Then message of invalid coupon is successufully displayed