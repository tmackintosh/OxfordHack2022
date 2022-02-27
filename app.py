from math import log
import json
import requests

# Constants
# API fields are only used for Get requests that are publicly available anyway.
# They have been disabled as of the Oxford Hack 2022 finishing
FINDER_SEARCH_ENGINE_ID = "c8129af6c4906773b"
SOCIAL_SCRAPER_ENGINE_ID = "1825f517354ca3718"
API_KEY = "AIzaSyDsRiDvlwLUNwqaExJoivtP_xE1_BicHio"

def get_place_by_name(name):
    """
    Calls the Google Maps Platform API to search with the query given.

    @input name string of search query
    @returns list decoded JSON response from Google API
    """

    r = requests.get("https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + name + "&key=" + API_KEY)
    return r.json()

def get_social_search(query):
    """
    Calls the Google Custom Search Engine to scrape social media platforms.

    @input query string of search query
    @returns list decoded JSON response from Google API
    """

    r = requests.get("https://customsearch.googleapis.com/customsearch/v1?cx=" + SOCIAL_SCRAPER_ENGINE_ID + "&q=" + query + "&key=" + API_KEY)
    return r.json()

def get_lat_lng_from_place(place):
    """
    Returns longitude and latitude values for the place given.

    @input list place object to find position of
    @returns latitude, longitude float of the long and lat values
    """

    geometry = place["geometry"]
    location = geometry["location"]
    return location["lat"], location["lng"]

def get_city_name(place):
    """
    Returns the city part of an address of the given place.

    @input list place object to find city name of
    @returns string of the city name
    """

    # We don't know how to determine whether part of a JSON file exist without
    # first throwing an exception, so catch it.
    try:
        compound_code = place["plus_code"]["compound_code"]
        first_space = compound_code.find(" ")
    except KeyError:
        return -1
        
    return compound_code[first_space + 1:]

def get_distance_from_centre(place, city):
    """
    Gets the distance between a place and a city.

    @input list place object of the place to compare
    @input list city object of the city to compare
    @returns float of the distance between the place and the city
    """

    place_lat, place_lng = get_lat_lng_from_place(place)
    city_lat, city_lng = get_lat_lng_from_place(city)

    # Pythagoras' Theorem
    return ((place_lat - city_lat) ** 2) + ((place_lng - city_lng) ** 2)

def get_total_social_search_results(place):
    """
    Finds the number of search results that were returned by the place object

    @input list place object that was returned by the search query
    @returns int of how many search results were returned in the query
    """

    search_results_object = get_social_search(place["name"])
    top_search_result = search_results_object["queries"]["request"][0]

    # We don't know how to determine whether part of a JSON file exist without
    # first throwing an exception, so catch it.
    try:
        return top_search_result["totalResults"]
    except:
        return 0

def get_location_score(place, city):
    """
    Calculates the location part of the score.

    @input list place object of the business location
    @input list city object of the city to compare location with
    @returns int of location score
    """

    distance_from_centre = get_distance_from_centre(place, city)
    # Score cap of 2
    score = 3 - ((distance_from_centre * (10 ** 3)) / 3)

    if score < 0:
        score = 0

    return score

def get_social_score(place):
    """
    Calculates the social presence part of the score.

    @input list place object of the business to assess social presence
    @returns int of calculated social score
    """

    number_of_social_search_results = int(get_total_social_search_results(place))

    if number_of_social_search_results == 0:
        order_of_magnitude = 0
    else:
        order_of_magnitude = int(log(number_of_social_search_results, 10))

    score = order_of_magnitude / 3.5

    # Score cap of 3
    if score > 3:
        score = 3

    return score

def get_score(place_name):
    """
    Calculates the score between 1 and 10 for a given business name

    @input place_name string of where the user would like to assess the score of
    @returns string JSON formatted with information about the business that closest matches the place_name input
    """

    places = get_place_by_name(place_name)

    for place in places["results"]:
        city_name = get_city_name(place)
        # It is possible that the search result wasn't indiciated as a business (such as a street or a house)
        # and hence doesn't have enough information to continue.
        if city_name == -1:
            continue

        city = get_place_by_name(city_name)["results"][0]

        location_score = get_location_score(place, city)
        ratings_score = place["rating"]
        social_score = get_social_score(place)

        total_score = ratings_score + location_score + social_score
        total_score = int(total_score * 10) / 10

        result_output = {
            "total_score": total_score,
            "ratings_score": ratings_score,
            "location_score": location_score,
            "social_score": social_score,
            "name": place["name"],
            "address": place["formatted_address"],
            "status": place["business_status"],
            "online_user_ratings": place["user_ratings_total"]
        }

        return json.dumps(result_output)

def get_score_from_json(json_input):
    """
    Calculates and finds information for all queries contained in the JSON file, and returns a newly constructed JSON.

    @input json_input custom SaltPay JSON-formatted string
    @returns custom JSON-formatted string
    """
    readable = json.loads(json_input)
    output = []

    for business in readable:
        # We've found that utilizing web APIs means that the exact business can be pinpointed by
        # exclusively knowing the name.
        # It is possible to cross-check the information returned with the information given
        # in other elements of the JSON input, however we didn't have enough time to implement this.
        name = business["name"]

        print("Assessing", name)
        output.append(get_score(name))

    return output

business = input("Enter name: ")
print(get_score(business))
go = input("Press Enter to run the SaltPay JSON input analysis. The output will be a printed JSON format.")
print(get_score_from_json(open("input.json", encoding="UTF-8").read()))