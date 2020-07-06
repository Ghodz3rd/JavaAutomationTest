@Login
Feature: Login functionality of Newspapers.com.
  In order to check the functionality of Login
  As a user
  I need to be able to test different scenarios
  
@registeredAccount
  Scenario: User successfully login registered account
    Given I am on homepage "https://www.newspapers.com/"
    When I click "Sign-in"
    	And I fill in "Username" with "lourdes100@test.com"
      And I fill in "Password" with "Test123!"
      And I click "Sign_in_with_Newspaper.com"
    Then I should see "lourdes100"
    
@invalidEntryLogin
  Scenario Outline: User should not be able to login unregistered account
    Given I am on homepage "https://www.newspapers.com/"
    When I click "Sign-in"
      And I fill in "Username" with "<user>"
      And I fill in "Password" with "<pass>"
      And I click "Sign_in_with_Newspaper.com"
    Then I should see "There is a problem with your email/password. "

    Examples:
      |user                 |pass    |
      |lourdes100@test.com  |Test    |
      |Test@test.com        |Test123!|

@missingFields
  Scenario: User should not be able to login with missing field
    Given I am on homepage "https://www.newspapers.com/"
    When I click "Sign-in"
      And I fill in "Username" with ""
      And I fill in "Password" with ""
      And I click "Sign_in_with_Newspaper.com"
    Then I should see "Missing email or password "