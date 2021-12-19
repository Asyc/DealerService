# Dealer Service

## API Documentation

### Create Deck
Functionality: Creates a new deck with an assigned numeric identifier.

Request Type: POST

Path: `dealer/v1/decks/`

Errors: None

Success: 200

Sample Output:
```json
{
  "id": 0
}
```
The `id` field is represented as a 64-bit signed integer.

### Delete Deck
Functionality: Invalidates the provided deck id. The id will no longer be able
to be used in any further requests.

Request Type: DELETE

Path: `dealer/v1/decks/{id}/`

Errors: 400 - ID Provided is invalid, 404 - Deck Not Found

Success: 204

### Deck Info
Functionality: Returns a JSON output containing all the cards in the deck,
along with the deck's own id.

Request Type: GET

Path: `dealer/v1/decks/{id}/`

Errors: 400 - ID Provided is Invalid, 404 - Deck Not Found

Success: 200

Sample Output:
```json
{
  "deckId": 0,
  "cards": [
    {
      "suite": "SPADE",
      "rank": "ACE"
    }
  ]
}
```

<hr>

### Shuffle Deck
Functionality: Uses the Fisher-Yates algorithm to mix the contents of the card stack
in a randomized order.

Request Type: PATCH

Path: `dealer/v1/decks/{id}/cards`

Parameters:
* action: \[what action to perform, currently only accepts `SHUFFLE`\]

Errors: 400 - ID Provided is Invalid / Action Provided is Invalid, 404 - Deck Not Found

Success: 200

### Remove Card
Functionality: Removes the card at the top of the card stack. Outputs the removed card.

Request Type: DELETE

Path: `dealer/v1/decks/{id}/cards`

Errors: 400 - ID Provided is Invalid, 404 - Deck Not Found, 410 - No cards to remove

Success: 200

Sample Output:
```json
{
  "card": {
    "suite": "SPADE",
    "rank": "KING"
  }
}
```

### Return Card
Functionality: Returns a removed card to the bottom of the card stack.

Request Type: POST

Path: `dealer/v1/decks/{id}/cards`

Parameters:
 * suite: \[suite of the card being returned\]
 * rank: \[ranking/value of the card being returned\]

Errors: 400 - ID Provided is Invalid / Suite or Rank provided is Invalid, 404 - Deck Not Found, 409 - Card already exists in deck

Success: 200

### Card Suite/Ranking:
The card rankings and suites are predefined, and all possible
values are defined below.

Card Suite:
```
HEART,
DIAMOND,
CLOVE,
SPADE
```

Card Rank:
```
ACE,
TWO,
THREE,
FOUR,
FIVE,
SIX,
SEVEN,
EIGHT,
NINE,
TEN,
JACK,
QUEEN,
KING
```

## Build Instructions

This project uses thorntail to run.

To run the application execute

```
mvn thorntail:run
```

or

```
mvn package && java -jar target/thorntail-starter-1.0-thorntail.jar
```

Then use `curl` or go to [http://localhost:8080/demo](http://localhost:8080/demo) in a web browser.