# Oxford hack mini challenge

See the full challenge brief, prizes and deadline at https://saltpay.co/oxfordhack.

Fork this repostory and fill out your details below:

## Your details

- Name: Tom Mackintosh
- Email: tm953@bath.ac.uk
- LinkedIn: https://www.linkedin.com/in/tom-mackintosh-7a731511a/

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