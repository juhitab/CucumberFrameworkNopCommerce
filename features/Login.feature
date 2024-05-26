Feature: Login to nopcommerce site
	@sanity
  Scenario: Successful login with valid credentials
    Given User launches browser
    When User opens the site url
    And User enters the Login email "admin@yourstore.com" and password "admin"
    And User clicks log in button
    Then Page Title should be "Dashboard / nopCommerce administration"
    And Page should contain "Dashboard"
    When User clickslogout link
    Then Page Title should be "Your store. Login"
    And Close browser

 
	Scenario Outline: Data Driven Login Test
		Given User launches browser
    When User opens the site url
    And User enters the Login email "<email>" and password "<password>"
    And User clicks log in button
    Then Page Title should be "Dashboard / nopCommerce administration"
    And Page should contain "Dashboard"
    When User clickslogout link
    Then Page Title should be "Your store. Login"
    And Close browser
    
    Examples:
    | email | password |
    | admin@yourstore.com | admin |
    | admin1@yourstore.com | admin |
    | admin@yourstore.com | admin@1 |
    