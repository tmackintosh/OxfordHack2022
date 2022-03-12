# Oxford hack mini challenge

An application to score and rank any business in the world given the following metrics:
* How present they are online
* Customer ratings
* How accessible they are physically

## How it works

I assessed how present a business is onlnie by creating a Google Custom Search Engine to scrape popular social media websites including Facebook and LinkedIn for how present the company is by calculating how many search results appear on each website.

I saw how well customers rated a business by using Google Maps API to view public ratings for the business and calculated an average score.

I assessed how physically accessible a business is by using Google Maps API to find the longitude and latitude of both a business and the centre of the town where the business is and calculated how far they were.

## Getting Started

Having cloned this repository, simply executing the file in a terminal will allow you to interact with it.
`python app.py`

## Utilizing Functions

The app allows other services to execute commands with simple text based inputs (either a custom-formatted JSON or a simple string used as a search query).

`get_score_from_json(JSON input)`

    input    Already read JSON file in custom SaltPay format.
    returns  Custom formatted JSON file will results from all the queries invoked in the JSON.

`get_score(str search_query)`

    input    Search query, such as "McDonalds" or "Jimmy's Kebab in Oxford" or "Restaurants near me"
    returns  A list with the calculated scores, as well as information about the business that has been found and assessed.