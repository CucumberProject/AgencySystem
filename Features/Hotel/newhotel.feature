Feature: newhotel 

Background:
A New system has been released to book hotels, car and tours.
Given I am on Admin Login Page
Then I enter username as "admin@phptravels.com" And I enter password as "demoadmin" to login

Scenario: Create a New Hotel
Given The User want to create a New Hotel
When The user will enter valid data on General tab
| Fields | Values |
| Hotel Name| Cozumel Resort|
| Hotel Desciption| Cozumel Resort is located in the principal pier of cozumel|
And Select Differents options on Facilities tab 
And enter valid data on Policy tab
And Enter valida information in Contact tab
Then The New Hotel should be created