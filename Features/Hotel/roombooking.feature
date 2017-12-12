Feature: roombooking

Background:
Given I am a Admin User and login in the Admin Login page.
Then I create a new hotel
And I create rooms for the new hotel

Scenario: While the rooms are availables, hotel can continue booking

Given I am User and login in the Travel agency page
When i ente