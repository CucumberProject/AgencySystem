@newHotelCoupon
Feature: newHotel_Coupon

@NewHoteladnCoupon
Scenario:
Create a new hotel and coupon
Given user enter to admin page
| admin@phptravels.com | demoadmin |
And user add a new hotel
| Fields | Values |
|Hotel Status| Enabled|
| Hotel Name| Cozumel Resort|
| Hotel Desciption| Cozumel Resort is located in the principal pier of cozumel|
| HotelStars| 4|
| Type| Hotel|
| Location| Cozumel|
And  Create a New Room for the hotel
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
Then a new coupon is added
| 50 | 9 | Newroom |


@UseHotelCoupon
Scenario:
user can use the new coupon in the new hotel room
Given user enter to the home page
 	| user@phptravels.com | demouser |
When user select de new hotel and book a room
And new coupon is used
	| Newroom |
Then the book is listed on the account