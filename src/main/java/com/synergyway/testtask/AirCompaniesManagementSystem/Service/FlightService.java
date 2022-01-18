package com.synergyway.testtask.AirCompaniesManagementSystem.Service;

import com.synergyway.testtask.AirCompaniesManagementSystem.Entity.Flight;
import com.synergyway.testtask.AirCompaniesManagementSystem.Entity.FlightStatus;
import com.synergyway.testtask.AirCompaniesManagementSystem.Model.FlightModel;
import com.synergyway.testtask.AirCompaniesManagementSystem.Repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> findFlightByStatus(FlightStatus flightStatus) {
        List<Flight> flightList = new ArrayList<>();
//        flightRepository.findAll().iterator().forEachRemaining(x -> x.getFlightStatus().equals(flightStatus));

        return flightList;
    }

    public void addNewFlight(Flight flight) {
        flightRepository.save(flight);
    }

    public Flight changeStatus(Long id, FlightStatus flightStatus, LocalDateTime localDateTime) {
        Flight flight = flightRepository.findById(id).get();
        flight.setFlightStatus(flightStatus);
        switch (flightStatus) {
            case ACTIVE:
                flight.setStartedAt(localDateTime == null ? LocalDateTime.now() : localDateTime);
                break;
            case DELAYED:
                flight.setDelayStartedAt(localDateTime == null ? LocalDateTime.now() : localDateTime);
                break;
            case COMPLETED:
                flight.setEndedAt(localDateTime == null ? LocalDateTime.now() : localDateTime);
                break;
        }
        return flightRepository.save(flight);
    }

    public List<Flight> getAllFlightsIsComplitedWhitOptionInTime() {
        List<Flight> flightList = new ArrayList<>();
//        flightRepository.findAllByFlightStatus(FlightStatus.COMPLETED).spliterator()
//                .forEachRemaining(x -> x.getEstimatedFlightTime() > x.getEndedAt().).forEach(flightList::add);
        return flightList;
    }

    public List<Flight> getAllFlights() {
        List<Flight> flightList = new ArrayList<>();
        flightRepository.findAll().forEach(flightList::add);
        return flightList;
    }

    public List<Flight> getAllFlightsByAirCompanyAndStatus(String name, Integer status) {
        List<Flight> flightList = new ArrayList<>();
        flightRepository
                .findAllByairCompanyNameAndFlightStatus(name, status)
                .forEach(flightList::add);
        return flightList;
    }

    public List<Flight> getAllFlightsActive(Integer status) {
        List<Flight> flightList = new ArrayList<>();
        StreamSupport.stream(flightRepository.findAllByFlightStatus(status).spliterator(), false)
                .filter(flight -> Duration.between(flight.getStartedAt(), LocalDateTime.now()).toHours() >= 24)
                .forEach(flightList::add);
        return flightList;
    }

    public List<Flight> getAllFlightsCompletedAndtimeDiff() {
        List<Flight> flightList = new ArrayList<>();
        StreamSupport.stream(flightRepository.findAllByFlightStatus(FlightStatus.COMPLETED.getIndex()).spliterator(), false)
                .filter(flight -> Duration.between(flight.getStartedAt(), flight.getEndedAt()).toMinutes() > toMins(flight.getEstimatedFlightTime().toString()))
        .forEach(flightList::add);
        return flightList;
    }

    private long toMins(String s) {
        String[] hourMin = s.split(":");
        long hour = Integer.parseInt(hourMin[0]);
        long mins = Integer.parseInt(hourMin[1]);
        long hoursInMins = hour * 60;
        return hoursInMins + mins;
    }
}
