Feature: amenitiesroom
@amenities
Scenario: User should be able to see all the amenities assigned to the room

Given I am Admin user 
When I Create a New Hotel
| Fields | Values |
|Hotel Status| Enabled|
| Hotel Name| Cozumel Resort|
| Hotel Desciption| Cozumel Resort is located in the principal pier of cozumel|
| HotelStars| 4|
| Type| Hotel|
| Location| Cozumel|
And Create a New room for the hotel
| Fields | Values |
| Room Status| Enabled|
| Room Type| Presidential Suite|
| Room Hotel| Cozumel Resort|
| Room Desciption| The presidential suite is the best room we ever had|
| Price | 300|
| Quantity| 2|
| Min Stay| 1|
| Max Adults| 2|
| Max children| 2|
| Extra Beds| 5|
| Price Beds| 50|
And The new room will have the following amenities
| Fields | Values |
| Amenitie1| Climate control|
| Amenitie2| Free Wi-Fi|
| Amenitie3| LCD TV|
Then the user should be able to see all the amenities
| Fields | Values |
| Hotel Name| Cozumel Resort|
| Amenitie1| Climate control|
| Amenitie2| Free Wi-Fi|
| Amenitie3| LCD TV|