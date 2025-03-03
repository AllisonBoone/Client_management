package com.example.airportclient;

// Added imports.
import java.io.IOException;
import java.util.Scanner;

// Created main class for client cli.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AirportClient airportClient = new AirportClient();

        System.out.println("\nWelcome to the Airport Client Manager!"); 
        
        while (true) {
            // Display menu options
            System.out.println("\n Select an option:");
            System.out.println("1️. What airports are in what cities?");
            System.out.println("2️. List all aircraft a passenger has traveled on.");
            System.out.println("3️. Which airports can an aircraft take off from and land at?");
            System.out.println("4️. What airports have passengers used?");
            System.out.println("0️0 Exit");

            System.out.print("👉 Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            if (choice == 0) {
                System.out.println("Goodbye!");
                break;
            }

            try {
                switch (choice) {
                    case 1:
                        System.out.print("\nEnter the city name: ");
                        String cityName = scanner.nextLine().trim();
                        System.out.println("\nAirports in " + cityName + ":");
                        System.out.println(airportClient.getAirportsByCity(cityName));
                        break;

                    case 2:
                        System.out.print("\nEnter a passenger ID: ");
                        Long passengerId = scanner.nextLong();
                        scanner.nextLine();
                        System.out.println("\nAircraft passenger " + passengerId + " has traveled on:");
                        System.out.println(airportClient.getAircraftByPassenger(passengerId));
                        break;

                    case 3:
                        System.out.print("\nEnter an aircraft ID: ");
                        Long aircraftId = scanner.nextLong();
                        scanner.nextLine(); // Consume the newline
                        System.out.println("\nAirports aircraft " + aircraftId + " can operate from:");
                        System.out.println(airportClient.getAirportsByAircraft(aircraftId));
                        break;

                    case 4:
                        System.out.print("\nEnter a passenger ID: ");
                        Long passengerId2 = scanner.nextLong();
                        scanner.nextLine();
                        System.out.println("\nAirports passenger " + passengerId2 + " has used:");
                        System.out.println(airportClient.getAirportsUsedByPassenger(passengerId2));
                        break;

                    default:
                        System.out.println("Invalid option. Try again.");
                        break;
                }
            } catch (IOException e) {
                System.err.println("Error, Unable to connect to the API. Is the server running?");
            }
        }

        scanner.close();
    }
}
