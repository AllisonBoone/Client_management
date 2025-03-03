package src.main.java.com.example.airportclient;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AirportClient airportClient = new AirportClient();

        System.out.println("Welcome to the Airport Client Manager!");
        while (true) {
            System.out.print("Enter an airport code (or 'exit' to quit): ");
            String airportCode = scanner.nextLine().trim().toUpperCase();

            if (airportCode.equals("EXIT")) {
                System.out.println("Goodbye!");
                break;
            }

            try {
                String response = airportClient.fetchAirportData(airportCode);
                System.out.println("Airport Data: " + response);
            } catch (IOException e) {
                System.err.println("Error fetching data: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
