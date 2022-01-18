package com.synergyway.testtask.AirCompaniesManagementSystem.Controller;

import com.synergyway.testtask.AirCompaniesManagementSystem.Entity.AirCompany;
import com.synergyway.testtask.AirCompaniesManagementSystem.Repository.AirCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/aircompany")
public class AirCompanyController {
    @Autowired
    private AirCompanyRepository airCompanyRepository;


    @GetMapping
    public List<AirCompany> getAllAirCompany() {
        List<AirCompany> airCompanyList = new ArrayList<>();
        airCompanyRepository.findAll().forEach(airCompanyList::add);
        return airCompanyList;
    }

    @GetMapping("/{id}")
    public ResponseEntity getAirCompanyById(@PathVariable Long id) {
        AirCompany airCompany = airCompanyRepository.findById(id).orElse(null);
        if (airCompany == null) {
            return ResponseEntity.badRequest().body("There is no AirCompany with id = " + id);
        }
        return ResponseEntity.ok(airCompany);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAirCompany(@PathVariable Long id) {
        try {
            airCompanyRepository.deleteById(id);
            return ResponseEntity.ok("AirCoppany id " + id + " was deleted.");
        } catch (EmptyResultDataAccessException Ex) {
            return ResponseEntity.badRequest().body("AirCompany with id " + id + " didn't exist.");
        }
    }

    @PutMapping("/{id}")
    public void updateAirCompany(@PathVariable Long id, @RequestBody AirCompany airCompany) {
        airCompany.setID(id);
        airCompanyRepository.save(airCompany);
    }

    @PostMapping
    public ResponseEntity addNewAirCompany(@RequestBody AirCompany airCompany) {
        return ResponseEntity.ok().body(airCompanyRepository.save(airCompany));
    }
}
