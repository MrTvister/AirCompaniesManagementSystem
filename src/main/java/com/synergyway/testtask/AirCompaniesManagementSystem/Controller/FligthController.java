package com.synergyway.testtask.AirCompaniesManagementSystem.Controller;

import com.synergyway.testtask.AirCompaniesManagementSystem.Entity.Flight;
import com.synergyway.testtask.AirCompaniesManagementSystem.Entity.FlightStatus;
import com.synergyway.testtask.AirCompaniesManagementSystem.Service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FligthController {
    @Autowired
    private FlightService flightService;

    @GetMapping
    public List<Flight> getAllFlight() {
        return flightService.getAllFlights();
    }

    // Add New Flight with fligth status seting to PENDING
    @PostMapping
    public void addNewFlight(@RequestBody Flight flight) {
        flight.setFlightStatus(FlightStatus.PENDING);
        flightService.addNewFlight(flight);
    }

    // Update FlightStatus of a flight with option of getting Date in the request or just puttin date of now
    @PutMapping("/{id}")
    public ResponseEntity changeFlightStatus(@PathVariable Long id, @RequestParam(value = "status", required = true) Integer flightStatus,
                                             @RequestParam(value = "date", required = false)
                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime localDateTime) {
        Flight flight = flightService.changeStatus(id, FlightStatus.getById(flightStatus), localDateTime);
        if (flight != null) {
            return ResponseEntity.ok(flight);
        } else return ResponseEntity.badRequest().body("There is no Flight with id " + id);
    }


    /*      Find flights by :
                        1. All Flights in COMPLETED status and difference between started and ended time is bigger than the estimated flight time.
                        2. All Flights in ACTIVE status and started more than 24 hours ago
                        3. All Air Company Flights by status
     */

    @GetMapping("/find")
    public List<Flight> getAllFlightsByAirCompanyAndStatus(@RequestParam(value = "airCompanyName", required = false) String name,
                                                           @RequestParam(value = "status", required = false, defaultValue = "1") Integer status) {
        List<Flight> flightList;
        if (name != null && status == null) {
            //All Air Company Flights by status
            flightList = flightService.getAllFlightsByAirCompanyAndStatus(name, status);
        } else if (status.equals(FlightStatus.COMPLETED.getIndex())) {
            //All Flights in COMPLETED ..
            flightList = flightService.getAllFlightsCompletedAndtimeDiff();
        } else {
            // All Flights in ACTIVE status and started more than 24 hours ago
            flightList = flightService.getAllFlightsActive(FlightStatus.ACTIVE.getIndex());
        }
        return flightList;
    }

}
