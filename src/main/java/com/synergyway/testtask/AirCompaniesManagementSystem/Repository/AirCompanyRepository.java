package com.synergyway.testtask.AirCompaniesManagementSystem.Repository;

import com.synergyway.testtask.AirCompaniesManagementSystem.Entity.AirCompany;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AirCompanyRepository extends CrudRepository<AirCompany, Long> {

    Optional<AirCompany> findByName(String name);
}
