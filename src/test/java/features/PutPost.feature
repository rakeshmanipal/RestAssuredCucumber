Feature: Put Posts
  Test the Put Operation

  @smoke
  Scenario: Verify PUT Operation after Post
    Given I ensure to Perform POST operation for "/posts" with body as
      |id|title|author|
      |6 |API Testing Course|ExecuteAutomation|
    And I Perform PUT Operation for "/posts/{postid}/"
      |id|title|author|
      |6 |API Testing |ExecuteAutomation|
    And I perform GET operation with path parameter for "/posts/{postid}"
      |postid|
      |   6  |
    Then I should see the body with title as "API Testing"