package com.synergyway.testtask.AirCompaniesManagementSystem.Entity;

public enum FlightStatus {
    ACTIVE(0), COMPLETED(1), DELAYED(2), PENDING(3);
    private Integer index;

    FlightStatus(Integer index) {
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }

    public static FlightStatus getById(int id) {
        for (FlightStatus e : values()) {
            if (e.index == id) return e;
        }
        return null;
    }
}
