package com.gb.parkinglot.model.parking;

import com.gb.parkinglot.model.vehicle.VehicleType;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class HourlyCost {
    private Map<VehicleType, Double> hourlyCosts = new HashMap<>();

    public HourlyCost() {
        hourlyCosts.put(VehicleType.CAR, 20.0);
        hourlyCosts.put(VehicleType.TRUCK, 30.0);
        hourlyCosts.put(VehicleType.ELECTRIC, 25.0);
        hourlyCosts.put(VehicleType.VAN, 35.0);
        hourlyCosts.put(VehicleType.MOTORBIKE, 10.0);

    }

    public double getCost(VehicleType vehicleType) {
        return hourlyCosts.get(vehicleType);
    }
}
