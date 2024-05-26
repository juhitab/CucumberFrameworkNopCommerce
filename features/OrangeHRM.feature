@sanity
Feature: OrangeHRM Login

Scenario: Logo presence on Orange HRM homepge

Given I launch chrome browser
When I open Orange HRM homepage
Then I verify that the logo is present on page
And close the browser
