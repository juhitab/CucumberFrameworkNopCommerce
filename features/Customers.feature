Feature: Customers
 Background: Common steps for every scenario
		Given User launches browser
    When User opens the site url
    And User enters the Login email "admin@yourstore.com" and password "admin"
    And User clicks log in button
    Then Page should contain "Dashboard"
    
  @sanity  
	Scenario: Add a new Customer
    When User clicks on Customers menu
    And Clicks on Customers menu item
    And Clicks on Add new button
    Then User can view Add new customer page
    And Page Title should be "Add a new customer / nopCommerce administration"
    When User enters customer info
    And User clicks on save button
    Then Validation fails with message "The customer cannot be in both 'Guests' and 'Registered' customer roles" if user enters "Registered" and "Guests" roles
    And For different roles user can view confirmation message "The new customer has been added successfully"
    And Close browser
    
  @searchbyemail @search @sanity
  Scenario: Search Customer by Email
    When User clicks on Customers menu
    And Clicks on Customers menu item
    And Enter customer Email
    And Click search button
    Then User found email in Search table
    And only one record returned for given email
    And Close browser
   
 @searchbyname @search
  Scenario: Search Customer by Name
    When User clicks on Customers menu
    And Clicks on Customers menu item
    And Enter customer firstname "Victoria" and lastname "Terces"
    And Click search button
    Then User found name in Search table
    And validate data consistency if mutliple records found with same name
    And Close browser
   