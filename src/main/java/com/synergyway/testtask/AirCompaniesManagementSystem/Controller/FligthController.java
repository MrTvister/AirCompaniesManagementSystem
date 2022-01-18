package com.synergyway.testtask.AirCompaniesManagementSystem.Controller;

import com.synergyway.testtask.AirCompaniesManagementSystem.Entity.Flight;
import com.synergyway.testtask.AirCompaniesManagementSystem.Entity.FlightStatus;
import com.synergyway.testtask.AirCompaniesManagementSystem.Service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FligthController {
    @Autowired
    private FlightService flightService;

    @GetMapping
    public List<Flight> getFlightByStatus(@PathVariable FlightStatus flightStatus) {//@RequestBody FlightStatus flightStatus
        return flightService.getAllFlights();
//        return flightService.findFlightByStatus(flightStatus);
    }

    // Add New Flight
    @PostMapping
    public void addNewFlight(@RequestBody Flight flight) {
        flight.setFlightStatus(FlightStatus.PENDING);
        flightService.addNewFlight(flight);
    }

    @PutMapping("/{id}")
    public void changeFlightStatus(@PathVariable Long id, @RequestParam(value = "status", required = true) Integer flightStatus,
                                   @RequestParam(value = "date", required = false)
                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime localDateTime) {
        flightService.changeStatus(id, FlightStatus.getById(flightStatus), localDateTime);
    }


    //find all Air Company Flights by status
    @GetMapping("/find")
    public List<Flight> getAllFlightsByAirCompanyAndStatus(@RequestParam(value = "airCompanyName", required = false) String name,
                                                           @RequestParam(value = "status", required = false, defaultValue = "1") Integer status) {
        List<Flight> flightList;

        if (name != null && status == null) {
            flightList = flightService.getAllFlightsByAirCompanyAndStatus(name, status);
        } else if (status.equals(FlightStatus.COMPLETED.getIndex())) {
            flightList = flightService.getAllFlightsCompletedAndtimeDiff();
        } else {
            flightList = flightService.getAllFlightsActive(FlightStatus.ACTIVE.getIndex());
        }
        return flightList;
    }

}
