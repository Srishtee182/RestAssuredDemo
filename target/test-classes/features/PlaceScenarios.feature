Feature: Place API's

@AddPlace
Scenario Outline: Verify if place is been successfully added using add place API
	Given Add place payload with "<name>" "<language>" "<address>"
	When user calls "addPlaceAPI" with "Post" Http Request
	Then the API call is success with status code 200
	And "status" code should be "OK"
	And "scope" code should be "APP"
	And verify the place id created maps to "<name>" using "getPlaceAPI"
	
Examples:
	|name		|language	| address		|
	|john		|English	|paradise streat|
	|alexa		|German		|Hamburg germany|
@DeletePlace
Scenario: Verify if place is been successfully deleted successfully
	Given User have delete payload
	When user calls "deletePlaceAPI" with "Delete" Http Request
	Then the API call is success with status code 200
	And "status" code should be "OK"