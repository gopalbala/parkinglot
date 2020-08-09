package com.gb.parkinglot.model.parking;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ParkingFloor {
    private String floorId;
    private List<ParkingSpot> parkingSpots;
}
