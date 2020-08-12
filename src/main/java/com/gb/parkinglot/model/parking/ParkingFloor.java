package com.gb.parkinglot.model.parking;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ParkingFloor {
    private String floorId;
    private Map<ParkingSpotType, List<ParkingSpot>> parkingSpots = new HashMap<>();
}
