Feature: PostProfile
  Test Post operation Rest Assured Libraries

Scenario: Verify Post operation for Profile
Given I Perform POST operation for "/posts/{profileNo}/profile" with body
|name |profile |
|Sams |2      |
Then i should see the body has the name as "Sam"