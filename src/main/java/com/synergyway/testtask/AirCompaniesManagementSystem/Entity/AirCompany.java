package com.synergyway.testtask.AirCompaniesManagementSystem.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class AirCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @Column(unique = true)
    private String name;
    private String companyType;
    private LocalDate foundedAt;

    public AirCompany() {
    }

    public AirCompany(Long ID, String name, String companyType, LocalDate foundedAt) {
        this.ID = ID;
        this.name = name;
        this.companyType = companyType;
        this.foundedAt = foundedAt;
    }

    @Override
    public String toString() {
        return "AirCompany{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", companyType='" + companyType + '\'' +
                ", foundedAt=" + foundedAt +
                '}';
    }
}