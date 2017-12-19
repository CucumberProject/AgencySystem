Feature: hotelfacilities

@newFacilities
Scenario: User should be able to see the Facilities of the New Hotel
Given I am Admin User and Log in
When I create a New Hotel
| Fields | Values |
|Hotel Status| Enabled|
| Hotel Name| Cozumel Resort|
| Hotel Desciption| Cozumel Resort is located in the principal pier of cozumel|
| HotelStars| 4|
| Type| Hotel|
| Location| Cozumel|
And add Facilities information for the New Hotel
| Fields | Values |
| Facilities1| Airport Transport|
| Facilities2| Restaurant|
| Facilities3| Wi-Fi Internet|
| Facilities4| Fitness Center|
| Facilities5| Air Conditioner|
| Facilities6| Cards Accepted|
Then the User should be able to see the Facilites information
| Hotel| Cozumel Resort|
| Facilities1| Airport Transport|
| Facilities2| Restaurant|
| Facilities3| Wi-Fi Internet|
| Facilities4| Fitness Center|
| Facilities5| Air Conditioner|
| Facilities6| Cards Accepted|

@otherFacilities
Scenario Outline: User Should be able to see the Facilites hotels that already exist
Given I am User and Log in the Travel Agency page 
When I want to see information about this "<hotel>"
Then I should be able to see the Facilities from the "<hotel>"
Examples:
|hotel|
|Rendezvous Hotels|

