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

places = get_place_by_name(input("Enter place: "))

for place in places["results"]:
    place_id = place["place_id"]
    place_details = get_place_details(place_id)
    place_reviews = get_place_reviews(place_details)