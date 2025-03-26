package com.flight_management.demo.service;

import com.flight_management.demo.model.Airline;
import com.flight_management.demo.repository.AirlineRepository;
import com.speedment.jpastreamer.application.JPAStreamer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirlineService {

    @Autowired
    private AirlineRepository airlineRepository;

    private final JPAStreamer jpaStreamer;

    @Autowired
    public AirlineService(final JPAStreamer jpaStreamer){
        this.jpaStreamer = jpaStreamer;
    }

    public List<Airline> findBySourceDestinationAndDate(String source, String destination, LocalDate date){
        return jpaStreamer.stream(Airline.class)
                .filter((airline) -> (airline.getSource().equals(source))
                        && (airline.getDestination().equals(destination))
                        && (airline.getDepartureDateTime().toLocalDate().isEqual(date)))
                .collect(Collectors.toList());
    }

    public List<Airline> findBySourceAndDate(String source, LocalDate date) {
        return jpaStreamer.stream(Airline.class)
                .filter((airline) -> (airline.getSource().equals(source))
                        && (airline.getDepartureDateTime().toLocalDate().isEqual(date)))
                .collect(Collectors.toList());
    }

    public List<Airline> findBySourceAndDestination(String source, String destination){
        return jpaStreamer.stream(Airline.class)
                .filter((airline) -> (airline.getSource().equals(source))
                        && (airline.getDestination().equals(destination)))
                .collect(Collectors.toList());
    }

}
