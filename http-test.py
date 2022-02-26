import requests

def get_search_results():
    r = requests.get("https://customsearch.googleapis.com/customsearch/v1?cx=c8129af6c4906773b&q=facebook&key=AIzaSyDsRiDvlwLUNwqaExJoivtP_xE1_BicHio")
    data = r.json()
    print(data)