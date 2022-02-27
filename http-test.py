from math import log
from numpy import number
import requests

FINDER_SEARCH_ENGINE_ID = "c8129af6c4906773b"
SOCIAL_SCRAPER_ENGINE_ID = "1825f517354ca3718"
API_KEY = "AIzaSyDsRiDvlwLUNwqaExJoivtP_xE1_BicHio"

def get_place_by_name(name):
    r = requests.get("https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + name + "&key=" + API_KEY)
    return r.json()

def get_social_search(query):
    r = requests.get("https://customsearch.googleapis.com/customsearch/v1?cx=" + SOCIAL_SCRAPER_ENGINE_ID + "&q=" + query + "&key=" + API_KEY)
    return r.json()

def get_place_details(place_id):
    r = requests.get("https://maps.googleapis.com/maps/api/place/details/json?placeid=" + place_id + "&key=" + API_KEY)
    return r.json()

def get_place_reviews(place_details):
    try:
        return place_details["result"]["reviews"]
    except:
        return ["No reviews available."]

def get_lat_lng_from_place(place):
    geometry = place["geometry"]
    location = geometry["location"]
    return location["lat"], location["lng"]

def get_city_name(place):
    try:
        compound_code = place["plus_code"]["compound_code"]
        first_space = compound_code.find(" ")
    except KeyError:
        return -1
        
    return compound_code[first_space + 1:]

def get_distance_from_centre(place, city):
    place_lat, place_lng = get_lat_lng_from_place(place)
    city_lat, city_lng = get_lat_lng_from_place(city)

    # Pythagoras' Theorem
    return ((place_lat - city_lat) ** 2) + ((place_lng - city_lng) ** 2)

def get_total_social_search_results(place):
    search_results_object = get_social_search(place["name"])
    top_search_result = search_results_object["queries"]["request"][0]
    return top_search_result["totalResults"]

def get_location_score(place, city):
    distance_from_centre = get_distance_from_centre(place, city)
    print(distance_from_centre)
    print(city["name"])
    score = 2 - (distance_from_centre * (10 ** 3))

    if score < 0:
        score = 0

    return score

def get_social_score(place):
    number_of_social_search_results = int(get_total_social_search_results(place))
    order_of_magnitude = int(log(number_of_social_search_results, 10))
    score = order_of_magnitude / 3.5

    if score > 3:
        score = 3

    return score

place_name = input("Enter place: ")

places = get_place_by_name(place_name)
print(places["status"])

for place in places["results"]:
    place_id = place["place_id"]
    place_details = get_place_details(place_id)
    place_reviews = get_place_reviews(place_details)

    city_name = get_city_name(place)
    if city_name == -1:
        continue

    city = get_place_by_name(city_name)["results"][0]

    location_score = get_location_score(place, city)
    ratings_score = place["rating"]
    social_score = get_social_score(place)

    total_score = ratings_score + location_score + social_score
    print("Score:", int(total_score))
    print("Ratings:", ratings_score)
    print("Location:", location_score)
    print("Social:", social_score)