Feature: Customers

  Background: below are the common steps for each scenario
    Given user launch chrome browser
    When user open url "https://admin-demo.nopcommerce.com/login"
    And user enters email as "admin@yourstore.com" and password as "admin"
    And click on login
    Then user can view dashboard

  @sanity
  Scenario: Add New Customer
    When user click on customers menu
    And click on customers menu item
    And click on add new button
    Then user can view add new customer page
    When user enters customer info
    And click on save button
    Then user can view confirmation message "The new customer has been added successfully."
    And close browser

  @regression
  Scenario: Search Customer by EMailId
    When user click on customers menu
    And click on customers menu item
    And enter customer email
    When click on search button
    Then user should found email in the search table
    And close browser

  @regression
  Scenario: Search Customer by Name
    When user click on customers menu
    And click on customers menu item
    And enter customer firstname
    And enter customer lastname
    When click on search button
    Then user should found name in the search table
    And close browser