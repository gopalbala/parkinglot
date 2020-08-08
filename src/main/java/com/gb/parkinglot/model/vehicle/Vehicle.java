package com.gb.parkinglot.model.vehicle;

import com.gb.parkinglot.model.parking.ParkingTicket;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Vehicle {
    private String licenseNumber;
    private final VehicleType type;
    private ParkingTicket ticket;

    public Vehicle(VehicleType type) {
        this.type = type;
    }
}
