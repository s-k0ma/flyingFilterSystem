package com.gridnine.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private List<Flight> flights;
    @BeforeEach
    public void setUp(){
        flights = FlightBuilder.createFlights();
    }

    @Test
    public void testBeforeCurrenntDepartureFilter(){
        FlightFilter flightBeforeCurrentTime = new FlightBeforeCurrentTime();
        List<Flight> filteredFlights = flightBeforeCurrentTime.filter(flights);
        assertEquals(5,filteredFlights.size());
        assertEquals(flights.get(0), filteredFlights.get(0));
        assertEquals(flights.get(1), filteredFlights.get(1));
        assertEquals(flights.get(3), filteredFlights.get(2));
        assertEquals(flights.get(4), filteredFlights.get(3));
        assertEquals(flights.get(5), filteredFlights.get(4));
    }

    @Test
    public void testArrivalBeforeDepartureFilter(){
        FlightFilter flightArrivalBeforeDeparture = new ArrivalBeforeDeparture();
        List<Flight> filteredFlights = flightArrivalBeforeDeparture.filter(flights);
        assertEquals(5,filteredFlights.size());
        assertEquals(flights.get(0), filteredFlights.get(0));
        assertEquals(flights.get(1), filteredFlights.get(1));
        assertEquals(flights.get(2), filteredFlights.get(2));
        assertEquals(flights.get(4), filteredFlights.get(3));
        assertEquals(flights.get(5), filteredFlights.get(4));
    }

    @Test
    public void testLandingTimeMoreThenTwoHours(){
        FlightFilter totalLandingTime = new TotalLandingTime();
        List<Flight> filteredFlights = totalLandingTime.filter(flights);
        assertEquals(4,filteredFlights.size());
        assertEquals(flights.get(0), filteredFlights.get(0));
        assertEquals(flights.get(1), filteredFlights.get(1));
        assertEquals(flights.get(2), filteredFlights.get(2));
        assertEquals(flights.get(3), filteredFlights.get(3));
    }
}
