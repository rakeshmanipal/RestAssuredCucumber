Feature: DeletePosts
  Test the Delete Operation

  @smoke
  Scenario: Verify Delete Operation after Post
    Given I ensure to Perform POST operation for "/posts" with body as
    |id|title|author|
    |5 |API Testing Course|ExecuteAutomation|
    And I Perform Delete Operation for "/posts/{postid}/"
    |postid|
    |    5|
    And I perform GET operation with path parameter for "/posts/{postid}"
    |postid|
    |   5  |
    Then I should not see the body with title as "API Testing Course"