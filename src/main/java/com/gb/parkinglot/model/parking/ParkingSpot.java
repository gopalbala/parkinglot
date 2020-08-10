package com.gb.parkinglot.model.parking;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ParkingSpot {
    private String parkingSpotId;
    private boolean isFree;
    private ParkingSpotType parkingSpotType;
}
