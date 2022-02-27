import requests
import json

ENGINE_ID = "c8129af6c4906773b"
API_KEY = "AIzaSyDsRiDvlwLUNwqaExJoivtP_xE1_BicHio"

def get_place_by_name(name):
    r = requests.get("https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + name + "&key=" + API_KEY)
    return r.json()

def get_search_results(query):
    r = requests.get("https://customsearch.googleapis.com/customsearch/v1?cx=" + ENGINE_ID + "&q=" + query + "&key=" + API_KEY)
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
        compound_code = place["formatted_address"]
        first_space = compound_code.find(",")

    return compound_code[first_space + 1:]

def get_distance_from_centre(place, city):
    place_lat, place_lng = get_lat_lng_from_place(place)
    city_lat, city_lng = get_lat_lng_from_place(city)

    # Pythagoras' Theorem
    return ((place_lat - city_lat) ** 2) + ((place_lng - city_lng) ** 2)

places = get_place_by_name(input("Enter place: "))

for place in places["results"]:
    place_id = place["place_id"]
    place_details = get_place_details(place_id)
    place_reviews = get_place_reviews(place_details)

    city_name = get_city_name(place)
    city = get_place_by_name(city_name)["results"][0]

    distance_from_centre = get_distance_from_centre(place, city)