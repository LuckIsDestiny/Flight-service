package com.flight_management.demo;

import com.flight_management.demo.model.Airline;
import com.flight_management.demo.repository.AirlineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.apache.commons.csv.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class AirlineDataLoader implements CommandLineRunner {

    private static final Logger LOGGER = Logger.getLogger(AirlineDataLoader.class.getName());

    @Autowired
    private AirlineRepository airlineRepository;

    private static final String CSV_FILE_PATH = "airline_data.csv";

    @Override
    public void run(String... args) {
        if (airlineRepository.count() > 0) {
            LOGGER.info("Airlines are already loaded. Skipping data import.");
            return;
        }

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(CSV_FILE_PATH)) {
            if (inputStream == null) {
                LOGGER.severe("CSV file not found in resources!");
                return;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withFirstRecordAsHeader()
                    .withTrim()
                    .parse(reader);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            List<Airline> airlinesToSave = new ArrayList<>();

            for (CSVRecord record : records) {
                try {
                    Airline airline = new Airline();
                    airline.setAirline(record.get("Airline"));
                    airline.setSource(record.get("Source"));
                    airline.setSourceName(record.get("Source Name"));
                    airline.setDestination(record.get("Destination"));
                    airline.setDestinationName(record.get("Destination Name"));
                    airline.setDepartureDateTime(LocalDateTime.parse(record.get("Departure Date & Time"), formatter));
                    airline.setArrivalDateTime(LocalDateTime.parse(record.get("Arrival Date & Time"), formatter));
                    airline.setDurationHours(Double.parseDouble(record.get("Duration (hrs)")));
                    airline.setStopovers(record.get("Stopovers"));
                    airline.setAircraftType(record.get("Aircraft Type"));
                    airline.setFlightClass(record.get("Class"));
                    airline.setBookingSource(record.get("Booking Source"));
                    airline.setBaseFareBdt(Double.parseDouble(record.get("Base Fare (BDT)")));
                    airline.setTaxSurchargeBdt(Double.parseDouble(record.get("Tax & Surcharge (BDT)")));
                    airline.setTotalFareBdt(Double.parseDouble(record.get("Total Fare (BDT)")));
                    airline.setSeasonality(record.get("Seasonality"));
                    airline.setDaysBeforeDeparture(Integer.parseInt(record.get("Days Before Departure")));

                    airlinesToSave.add(airline);
                } catch (Exception e) {
                    LOGGER.log(Level.WARNING, "Error processing record: " + record.toString(), e);
                }
            }

            airlineRepository.saveAll(airlinesToSave);
            LOGGER.info("Successfully imported " + airlinesToSave.size() + " airlines.");

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to load CSV file", e);
        }
    }
}
