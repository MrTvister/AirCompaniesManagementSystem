package com.synergyway.testtask.AirCompaniesManagementSystem.Controller;

import com.synergyway.testtask.AirCompaniesManagementSystem.Entity.Airplane;
import com.synergyway.testtask.AirCompaniesManagementSystem.Service.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airplains")
public class AirplaneController {
    @Autowired
    private AirplaneService airplainService;

    @GetMapping
    public List<Airplane> getAllPlanes() {
        return airplainService.getAllPlains();
    }

    @GetMapping("/{id}")
    public ResponseEntity getPlaneById(@PathVariable long id) {
        Airplane airplane = airplainService.getPlainById(id);
        if (airplane == null) {
            return ResponseEntity.badRequest().body("There is no Plaine with id = " + id);
        } else return ResponseEntity.ok().body(airplane);
    }
    // Method to change Plain Owner by Company name
    @PutMapping("/reasign/{id}")
    public ResponseEntity reasignAirplane(@PathVariable Long id, @RequestParam("airCompany") String airCompanyName) {
        Airplane airplane = airplainService.reasignPlane(id, airCompanyName);
        if (airplane != null) {
            return ResponseEntity.ok(airplane);
        } else {
            return ResponseEntity.badRequest().body("Error");
        }
    }
    // There is a problem with aircompany id (it could by non existed)
    @PostMapping
    public void addNewAirplain(@RequestBody Airplane airplane) {
        airplainService.addNewPlain(airplane);
    }

}
