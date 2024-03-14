package com.gridnine.testing;

import java.util.List;

/**
 * Main method for manual testing of filter capabilities
 */
public class Main {
    public static void main(String[] args) {
        Main.testBeforeCurrenntDepartureFilter();
        Main.testArrivalBeforeDepartureFilter();
        Main.testLandingTimeMoreThenTwoHours();
    }

    /**
     * The method call factory of list of the flights
     * @see FlightBuilder#createFlights()
     * Then initialize filter
     * Original and final lists prints to terminal
     */
    public static void testBeforeCurrenntDepartureFilter(){
        List<Flight> flights = FlightBuilder.createFlights();
        System.out.println("Checking dates before current:");

        FlightFilter flightBeforeCurrentTime = new FlightBeforeCurrentTime();
        List<Flight> filteredFlights = flightBeforeCurrentTime.filter(flights);
        filteredFlights.stream().forEach(flight -> System.out.println(flight));
    }

    /**
     * The method call factory of list of the flights
     * @see FlightBuilder#createFlights()
     * Then initialize filter
     * Original and final lists prints to terminal
     */
    public static void testArrivalBeforeDepartureFilter(){
        List<Flight> flights = FlightBuilder.createFlights();
        System.out.println("Checking where arrival earlier then departure:");

        FlightFilter flightArrivalBeforeDeparture = new ArrivalBeforeDeparture();
        List<Flight> filteredFlights = flightArrivalBeforeDeparture.filter(flights);
        filteredFlights.stream().forEach(flight -> System.out.println(flight));
    }

    /**
     * The method call factory of list of the flights
     * @see FlightBuilder#createFlights()
     * Then initialize filter
     * Original and final lists prints to terminal
     */
    public static void testLandingTimeMoreThenTwoHours(){
        List<Flight> flights = FlightBuilder.createFlights();
        System.out.println("Checking where total landing time more then 2 hours:");

        FlightFilter totalLandingTime = new TotalLandingTime();
        List<Flight> filteredFlights = totalLandingTime.filter(flights);
        filteredFlights.stream().forEach(flight -> System.out.println(flight));
    }
}