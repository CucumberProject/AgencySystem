Feature: bookinghotel


Scenario: The Hotel is enable, the user should be able to book
Given The user log in the Travel Agency Page
When The user want to book in a hotel 
And The hotel is enable
| Fields | Values |
| HotelName1| Rendezvous Hotels|
| HotelName2| Swissotel Le Plaza Basel|
Then The user should be able to book
| Fields | Values |
| HotelName1| Rendezvous Hotels|
| HotelName2| Swissotel Le Plaza Basel|

Scenario: The Hotel is disable, The user shouldnt be able to book
Given The user log in the Travel Agency Page
When The user want to book in a hotel 
And The hotel is disable
| Fields | Values |
| HotelName1| Rendezvous Hotels|
| HotelName2| Swissotel Le Plaza Basel|
Then The user shouldnt be able to book
| Fields | Values |
| HotelName1| Rendezvous Hotels|
| HotelName2| Swissotel Le Plaza Basel|