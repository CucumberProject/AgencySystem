Feature: checkinfo


Scenario: User should be able to see the Check In information
Given I am Admin and Create a new hotel
When The user enter to the New hotel information
Then User should be able to see Check In Information

Scenario: User should be able to see any update information
Given I made a modification in the Check in Information
When The User Log in and Enter to the hotel information
Then User should be able to see new Check In Information
