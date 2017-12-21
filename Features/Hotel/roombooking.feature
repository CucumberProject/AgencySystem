Feature: roombooking

@available
Scenario: The user should be able to book a room

Given Im user and Login in the Travel Agency Page
When When I want to book a room in the Hotels page 
And the room capacity is available 
|hotel|Swissotel Le Plaza Basel|
|room|Studio Premier| 
Then I should be able to book a room
|hotel|Swissotel Le Plaza Basel|
|room|Studio Premier|
@unavailable
Scenario: The user shouldnt be able to book a room
Given Im user and Login in the Travel Agency Page
When When I want to book a room in the Hotels page 
And the room capacity is unavailable
|hotel|Swissotel Le Plaza Basel|
|room|Studio Premier| 
Then I shouldnt be able to book a room
|hotel|Swissotel Le Plaza Basel|
|room|Studio Premier| 
  