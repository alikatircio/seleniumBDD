Feature: Login

  @sanity
  Scenario: Successful login with valid credentials
    Given user launch chrome browser
    When user open url "https://admin-demo.nopcommerce.com/login"
    And user enters email as "admin@yourstore.com" and password as "admin"
    And click on login
    Then Page Title should be "Dashboard / nopCommerce administration"
    When User click on Log out link
    Then Page Title should be "Your store. Login"
    And close browser

  @regression #tekli koşumlar için tag kullanılabilir.
  Scenario Outline: Login Data Driven
    Given user launch chrome browser
    When user open url "https://admin-demo.nopcommerce.com/login"
    And user enters email as "<email>" and password as "<password>"
    And click on login
    Then Page Title should be "Dashboard / nopCommerce administration"
    When User click on Log out link
    Then Page Title should be "Your store. Login"
    And close browser

    Examples:
      | email                | password |
      | admin@yourstore.com  | admin    |
      | admin1@yourstore.com | admin123 |