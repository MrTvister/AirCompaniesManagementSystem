package com.synergyway.testtask.AirCompaniesManagementSystem.Service;

import com.synergyway.testtask.AirCompaniesManagementSystem.Entity.AirCompany;
import com.synergyway.testtask.AirCompaniesManagementSystem.Entity.Airplane;
import com.synergyway.testtask.AirCompaniesManagementSystem.Repository.AirCompanyRepository;
import com.synergyway.testtask.AirCompaniesManagementSystem.Repository.AirplaneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirplaneService {

    @Autowired
    private AirplaneRepository airplaneRepository;
    @Autowired
    private AirCompanyRepository airCompanyRepository;

    public Airplane reasignPlane(Long id, String airCompanyName) {
        AirCompany airCompany = airCompanyRepository.findByName(airCompanyName).orElse(null);
        Airplane airplane = airplaneRepository.findById(id).orElse(null);
        if(airplane == null)
            return null;
        airplane.setAirCompanyId(airCompany.getID());
        return airplaneRepository.save(airplane);
    }

    public void addNewPlain(Airplane airplane) {
        airplaneRepository.save(airplane);
    }

    public List<Airplane> getAllPlains() {
        List<Airplane> airplanes = new ArrayList<>();
        airplaneRepository.findAll().forEach(airplanes::add);
        return airplanes;
    }

    public Airplane getPlainById(long id) {
        return airplaneRepository.findById(id).orElse(null);
    }
}
