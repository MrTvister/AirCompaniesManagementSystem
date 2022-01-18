package com.synergyway.testtask.AirCompaniesManagementSystem.Repository;

import com.synergyway.testtask.AirCompaniesManagementSystem.Entity.Flight;
import com.synergyway.testtask.AirCompaniesManagementSystem.Entity.FlightStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FlightRepository extends CrudRepository<Flight, Long> {
    @Query(value = " SELECT * FROM flight f where f.flight_status = :status", nativeQuery = true)
    Iterable<Flight> findAllByFlightStatus( @Param("status")Integer fligthStatus);

    @Query(value = " SELECT * FROM flight f where f.air_company_id in (Select id from air_company a where a.name = :name ) and f.flight_status = :status", nativeQuery = true)
    Iterable<Flight> findAllByairCompanyNameAndFlightStatus(@Param("name") String name, @Param("status") Integer status);

}
