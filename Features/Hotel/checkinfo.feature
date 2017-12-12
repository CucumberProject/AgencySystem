Feature: checkinfo

@set
Scenario: User should be able to see the Check In information
Given I am Admin and Create a new hotel
| Fields | Values |
| HotelName| Cozumel Resort|
| Description|Cozumel Resort is located in the principal pier of cozumel|
| Location| Cozumel|
| HotelStars| 4|
| Type| Hotel|
| CheckIn| 10:00 AM|
| CheckOut| 05:00 PM|
| PolicyText| "You cant bring pets"|
When The user enter to the New hotel information
| Fields | Values |
| HotelName| Cozumel Resort|
Then User should be able to see Check In Information

@update
Scenario: User should be able to see any update information
Given I made a modification in the Check in Information
| Fields | Values |
| HotelName| Rendezvous Hotels|
| CheckIn| 08:00 AM|
| CheckOut| 08:00 PM|
When The User Log in and Enter to the hotel information
| Fields | Values |
| HotelName| Rendezvous Hotels|
Then User should be able to see new Check In Information
