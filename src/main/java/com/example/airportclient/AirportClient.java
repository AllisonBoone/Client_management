package com.example.airportclient;

// Added imports.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
// Created class to handle api requests with error handling.
public class AirportClient {
    
    private static final String API_BASE_URL = "http://localhost:8080/api/";

    private String sendGetRequest(String endpoint) throws IOException {
        String urlString = API_BASE_URL + endpoint; 
        URI uri = URI.create(urlString); 
        URL url = uri.toURL(); 

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET"); 

        // Get response code from the server
        int responseCode = conn.getResponseCode();
        if (responseCode != 200) {
            return "Error, failed to fetch data. HTTP code: " + responseCode;
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return response.toString();
    }

    public String getAirportsByCity(String cityName) throws IOException {
        return sendGetRequest("airports/city/" + cityName);
    }

    public String getAircraftByPassenger(Long passengerId) throws IOException {
        return sendGetRequest("airports/passenger/" + passengerId + "/aircraft");
    }

    public String getAirportsByAircraft(Long aircraftId) throws IOException {
        return sendGetRequest("airports/aircraft/" + aircraftId + "/airports");
    }

    public String getAirportsUsedByPassenger(Long passengerId) throws IOException {
        return sendGetRequest("airports/passenger/" + passengerId + "/airports");
    }
}
