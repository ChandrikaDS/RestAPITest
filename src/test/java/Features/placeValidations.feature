Feature: Validating place API's


  Scenario Outline: Verify if Place is being Successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language" "<address>"
    When user calls "AddPlaceAPI" UPI with "post" http request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And Verify placeId created maps to "<name>" using "getPlaceAPI"


    Examples: 
      | name      | language | address |
      | Chandrika | Serbian  | Beograd |
  #    | hari | beogr  | zelatibor |
     # | ishanvi | Serb  | mokra gora |

     
     Scenario: Verify if Delete place functionality is working
     
     Given DeletePlace Payload
     When user calls "DeletePlaceAPI" UPI with "POST" http request
     Then the API call is success with status code 200
     And "status" in response body is "OK"