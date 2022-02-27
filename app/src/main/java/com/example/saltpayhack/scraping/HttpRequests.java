package com.example.saltpayhack.scraping;

import com.example.saltpayhack.MainActivity;
import com.example.saltpayhack.MainFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.stream.Collectors;

public class HttpRequests {

    private String FINDER_SEARCH_ENGINE_ID = "c8129af6c4906773b";
    private String SOCIAL_SCRAPER_ENGINE_ID = "1825f517354ca3718";
    private String API_KEY = "AIzaSyDsRiDvlwLUNwqaExJoivtP_xE1_BicHio";

    public void getPlaceByName(String name) throws IOException, JSONException {
        String urlString = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + name + "&key=" + API_KEY;
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("accept", "application/json");
        new Thread(new Runnable() {
            public void run() {
                try {
                    InputStream inputStream = con.getInputStream();
                    String jsonString = new BufferedReader(
                            new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                            .lines()
                            .collect(Collectors.joining("\n"));

                    JSONObject mainObject = new JSONObject(jsonString);
                    JSONArray JsonArray = mainObject.getJSONArray("results");
                    System.out.println(JsonArray);
                } catch (JSONException | IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

//    // Now it's "open", we can set the request method, headers etc.
//connection.setRequestProperty("accept", "application/json");
//
//    // This line makes the request
//    InputStream responseStream = connection.getInputStream();
//
//    // Manually converting the response body InputStream to APOD using Jackson
//    ObjectMapper mapper = new ObjectMapper();
//    APOD apod = mapper.readValue(responseStream, APOD.class);
//
//// Finally we have the response
//System.out.println(apod.title);
//
//    def get_place_by_name(name):
//    r = requests.get("https://maps.googleapis.com/maps/api/place/textsearch/json?query=" + name + "&key=" + API_KEY)
//            return r.json()
//
//    def get_social_search(query):
//    r = requests.get("https://customsearch.googleapis.com/customsearch/v1?cx=" + SOCIAL_SCRAPER_ENGINE_ID + "&q=" + query + "&key=" + API_KEY)
//            return r.json()
//
    }
}
