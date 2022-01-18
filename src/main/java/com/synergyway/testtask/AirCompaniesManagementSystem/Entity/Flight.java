package com.synergyway.testtask.AirCompaniesManagementSystem.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name="SEQ_GEN", sequenceName="SEQ_JUST_FOR_Flight", allocationSize=1)
//    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_GEN")
    private Long ID;
    private FlightStatus flightStatus;
    private Long airCompanyId;
    private Long airplaneId;
    private String departureCountry;
    private String destinationCountry;
    private Double distance;
    private Time estimatedFlightTime;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private LocalDateTime delayStartedAt;
    private LocalDate createdAt;

    public Flight() {
    }

    public Flight(Long ID, FlightStatus flightStatus, Long airCompanyId, Long airplaneId,
                  String departureCountry, String destinationCountry, Double distance,
                  Time estimatedFlightTime, LocalDateTime startedAt, LocalDateTime endedAt,
                  LocalDateTime delayStartedAt, LocalDate createdAt) {
        this.ID = ID;
        this.flightStatus = flightStatus;
        this.airCompanyId = airCompanyId;
        this.airplaneId = airplaneId;
        this.departureCountry = departureCountry;
        this.destinationCountry = destinationCountry;
        this.distance = distance;
        this.estimatedFlightTime = estimatedFlightTime;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.delayStartedAt = delayStartedAt;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "ID=" + ID +
                ", flightStatus=" + flightStatus +
                ", airCompanyId=" + airCompanyId +
                ", airplaneId=" + airplaneId +
                ", departureCountry='" + departureCountry + '\'' +
                ", destinationCountry='" + destinationCountry + '\'' +
                ", distance=" + distance +
                ", estimatedFlightTime='" + estimatedFlightTime + '\'' +
                ", startedAt=" + startedAt +
                ", endedAt=" + endedAt +
                ", delayStartedAt=" + delayStartedAt +
                ", createdAt=" + createdAt +
                '}';
    }
}
