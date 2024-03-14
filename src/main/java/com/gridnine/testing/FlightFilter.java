package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Functional interface for filter of the flights
 * Based on pattern "Strategy"
 * All new conditions simply add as new classes
 */
@FunctionalInterface
public interface FlightFilter {
    List<Flight> filter(List<Flight> flights);
}

/**
 * Simple filter for all flights before current date
 */
class FlightBeforeCurrentTime implements FlightFilter{
    @Override
    public List<Flight> filter(List<Flight> flights){
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getDepartureDate().isAfter(LocalDateTime.now()))).collect(Collectors.toList());
    }
}

/**
 * Simple filter for all flights where arrival sets before departure
 */
class ArrivalBeforeDeparture implements FlightFilter{
    @Override
    public List<Flight> filter(List<Flight> flights){
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getArrivalDate().isAfter(segment.getDepartureDate()))).collect(Collectors.toList());
    }
}

/**
 * Simple filter for all flights
 * where total time spend on the land more than two hours
 */
class TotalLandingTime implements FlightFilter{
    @Override
    public List<Flight> filter(List<Flight> flights){
        return flights.stream().filter(flight -> {
            Duration totalLandingTime = Duration.ZERO;
            List<Segment> segments = flight.getSegments();
            for(int i = 0; i < segments.size() - 1; i++){
                totalLandingTime = totalLandingTime.plus(Duration.between(segments.get(i).getArrivalDate(), segments.get(i+1).getDepartureDate()));
            }
            return totalLandingTime.toHours() <= 2;
                }
        ).collect(Collectors.toList());
    }
}

