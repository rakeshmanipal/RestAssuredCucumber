Feature: ComplexDataSet
  Verify Complex Location data

 @smoke
 Scenario: Verify get operation for complex location data
   Given I perform Authentication operation for "/auth/login" with body
   |email           |password|
   |nilson@email.com|nilson  |
   And I perform GET operation with path parameter for address "/location"
   |id|
   | 1|
   Then I should see the street name as "1st street" for the "primary" address