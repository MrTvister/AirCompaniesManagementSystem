package com.synergyway.testtask.AirCompaniesManagementSystem.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Setter
@Getter
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;
    private String name;
    private String factorySerialNumber;
    private Long airCompanyId;
    private Integer numberOfFlights;
    private Long flightDistance;
    private Double fuelCapacity;
    private String type;
    private LocalDate createdAt;

    public Airplane() {
    }

    public Airplane(Long ID, String name, String factorySerialNumber, Long airCompanyId, Integer numberOfFlights,
                    Long flightDistance, Double fuelCapacity, String type, LocalDate createdAt) {
        this.ID = ID;
        this.name = name;
        this.factorySerialNumber = factorySerialNumber;
        this.airCompanyId = airCompanyId;
        this.numberOfFlights = numberOfFlights;
        this.flightDistance = flightDistance;
        this.fuelCapacity = fuelCapacity;
        this.type = type;
        this.createdAt = createdAt;
    }
}
