import requests
import json

ENGINE_ID = "c8129af6c4906773b"
API_KEY = "AIzaSyDsRiDvlwLUNwqaExJoivtP_xE1_BicHio"

def get_search_results(query):
    r = requests.get("https://customsearch.googleapis.com/customsearch/v1?cx=" + ENGINE_ID + "&q=" + query + "&key=" + API_KEY)
    return r.json()

def get_place_by_name(name):
    r = requests.get("https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + name + "&key=" + API_KEY)
    return r.json()