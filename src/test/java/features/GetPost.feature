Feature:
  Verify different get operation using REST-assured

  Scenario: Verify one author of the post
    Given I perform GET operation for "/posts"
     Then I should see the author name as "Karthik KK"

  Scenario: Verify collection of authors in the post
    Given I perform GET operation for "/post"
    Then I should see the author names

  Scenario: Verify parameter of GET
    Given I perform GET operation for "/post"
    Then I should see Verify GET parameter

  Scenario: Verify Query parameter of GET
    Given I perform GET operation for "/post"
    Then I should see Verify Query GET parameter

  Scenario: Verify Post Operation
    Given I perform POST operation for "/posts"

    @smoke
    Scenario: Verify GET Operation with bearer Authentication token
      Given I perform Authentication operation for "/auth/login" with body
      |email|password|
      |bruno@email.com|bruno|
      Given I perform GET operation for "/posts/1"
      Then I should see the author name as "Karthik KK"

  @smoke
  Scenario: Verify GET Operation with bearer Authentication token
    Given I perform Authentication operation for "/auth/login" with body
      |email|password|
      |bruno@email.com|bruno|
    Given I perform GET operation for "/posts/1"
    Then I should see the author name as "Karthik KK" with json validation


