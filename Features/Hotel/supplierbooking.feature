Feature: supplierbooking


Scenario: Suppliers should be able to book a hotel in the supplier page

Given I am Supplier User and login in the Supplier login page
When Supplier User want to book a hotel in the Supplier page
Then Supplier should be able to book a hotel.
