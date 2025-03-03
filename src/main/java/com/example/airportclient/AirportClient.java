package src.main.java.com.example.airportclient;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Scanner;

public class AirportClient {
    
    private static final String API_BASE_URL = "http://localhost:8080/api/airports/";

    public String fetchAirportData(String airportCode) throws IOException {
        String urlString = API_BASE_URL + airportCode;
        URI uri = URI.create(urlString);
        URL url = uri.toURL();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() != 200) {
            throw new IOException("Failed to fetch data, HTTP code: " + conn.getResponseCode());
        }

        Scanner scanner = new Scanner(conn.getInputStream());
        StringBuilder response = new StringBuilder();
        while (scanner.hasNext()) {
            response.append(scanner.nextLine());
        }
        scanner.close();
        
        return response.toString();
    }
}
