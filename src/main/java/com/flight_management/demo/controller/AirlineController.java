package com.flight_management.demo.controller;

import com.flight_management.demo.model.Airline;
import com.flight_management.demo.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/airline")
public class AirlineController {

    @Autowired
    private AirlineService airlineService;

    @GetMapping("/findFlightFrom/{source}/to/{destination}/on/{date}")
    public ResponseEntity<List<Airline>> findBySourceDestinationAndDate(
            @PathVariable("source") String source,
            @PathVariable("destination") String destination,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok().body(airlineService.findBySourceDestinationAndDate(source, destination, date));
    }

    @GetMapping("/findFlightFrom/{source}/on/{date}")
    public ResponseEntity<List<Airline>> findBySourceAndDate(
            @PathVariable("source") String source,
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok().body(airlineService.findBySourceAndDate(source, date));
    }

    @GetMapping("/findFlightFrom/{source}/to/{destination}")
    public ResponseEntity<List<Airline>> findBySourceDestinationAndDate(
            @PathVariable("source") String source,
            @PathVariable("destination") String destination) {
        return ResponseEntity.ok().body(airlineService.findBySourceAndDestination(source, destination));
    }

}
