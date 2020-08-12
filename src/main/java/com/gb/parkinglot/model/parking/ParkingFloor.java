package com.gb.parkinglot.model.parking;

import com.gb.parkinglot.model.vehicle.Vehicle;
import com.gb.parkinglot.model.vehicle.VehicleType;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

import static com.gb.parkinglot.model.parking.ParkingSpotType.*;


public class ParkingFloor {
    @Getter
    @Setter
    private String floorId;
    @Getter
    @Setter
    private Map<ParkingSpotType, TreeSet<ParkingSpot>> parkingSpots = new HashMap<>();
    private Map<ParkingSpotType, TreeSet<ParkingSpot>> usedParkingSpots = new HashMap<>();

    public ParkingFloor() {
        parkingSpots.put(HANDICAPPED, getComparableTreeSet());
        parkingSpots.put(CAR, getComparableTreeSet());
        parkingSpots.put(LARGE, getComparableTreeSet());
        parkingSpots.put(MOTORBIKE, getComparableTreeSet());
        parkingSpots.put(ELECTRIC, getComparableTreeSet());
    }

    private TreeSet getComparableTreeSet() {
        return new TreeSet<>(new Comparator<ParkingSpot>() {
            @Override
            public int compare(ParkingSpot o1, ParkingSpot o2) {
                return o1.getParkingSpotId().compareTo(o2.getParkingSpotId());
            }
        });
    }

    public boolean isFloorFull() {
        BitSet fullBitSet = new BitSet();
        int bitIndex = 0;
        for (Map.Entry<ParkingSpotType, TreeSet<ParkingSpot>> entry : parkingSpots.entrySet()) {
            if (entry.getValue().size() == 0) {
                fullBitSet.set(bitIndex++);
            } else {
                break;
            }
        }
        return fullBitSet.cardinality() == fullBitSet.size();
    }

    public boolean canPark(VehicleType vehicleType, boolean isHandicapped) {
        if (isHandicapped)
            return parkingSpots.get(HANDICAPPED).size() > 0;
        return canPark(getSpotTypeForVehicle(vehicleType));
    }

    public ParkingSpot getSpot(Vehicle vehicle, boolean handicapped) {
        if (!canPark(vehicle.getType(), handicapped))
            return null;

        ParkingSpotType parkingSpotType = getSpotTypeForVehicle(vehicle.getType());
        ParkingSpot parkingSpot = parkingSpots.get(parkingSpotType)
                .first();
        parkingSpots.remove(parkingSpot.getParkingSpotId());
        usedParkingSpots.get(parkingSpotType).add(parkingSpot);
        return parkingSpot;
    }

    private ParkingSpotType getSpotTypeForVehicle(VehicleType vehicleType) {
        switch (vehicleType) {
            case CAR:
                return CAR;
            case ELECTRIC:
                return ELECTRIC;
            case MOTORBIKE:
                return MOTORBIKE;
            default:
                return LARGE;
        }
    }

    private boolean canPark(ParkingSpotType parkingSpotType) {
        return parkingSpots.get(parkingSpotType).size() > 0;
    }

}
