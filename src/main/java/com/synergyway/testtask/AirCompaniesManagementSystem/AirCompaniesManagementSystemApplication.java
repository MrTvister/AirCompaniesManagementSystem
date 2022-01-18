package com.synergyway.testtask.AirCompaniesManagementSystem;

import com.synergyway.testtask.AirCompaniesManagementSystem.Entity.Airplane;
import com.synergyway.testtask.AirCompaniesManagementSystem.Entity.Flight;
import com.synergyway.testtask.AirCompaniesManagementSystem.Repository.AirCompanyRepository;
import com.synergyway.testtask.AirCompaniesManagementSystem.Repository.AirplaneRepository;
import com.synergyway.testtask.AirCompaniesManagementSystem.Repository.FlightRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

@SpringBootApplication
public class AirCompaniesManagementSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(AirCompaniesManagementSystemApplication.class, args);
    }
}
