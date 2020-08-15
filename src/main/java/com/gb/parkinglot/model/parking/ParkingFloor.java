package com.gb.parkinglot.model.parking;

import com.gb.parkinglot.model.vehicle.Vehicle;
import com.gb.parkinglot.model.vehicle.VehicleType;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

import static com.gb.parkinglot.model.parking.ParkingSpotType.*;


public class ParkingFloor {
    @Getter
    @Setter
    private String floorId;
    @Getter
    @Setter
    private Map<ParkingSpotType, Deque<ParkingSpot>> parkingSpots = new HashMap<>();
    private Map<String, ParkingSpot> usedParkingSpots = new HashMap<>();

    public ParkingFloor() {
        parkingSpots.put(HANDICAPPED, new ConcurrentLinkedDeque());
        parkingSpots.put(CAR, new ConcurrentLinkedDeque());
        parkingSpots.put(LARGE, new ConcurrentLinkedDeque());
        parkingSpots.put(MOTORBIKE, new ConcurrentLinkedDeque());
        parkingSpots.put(ELECTRIC, new ConcurrentLinkedDeque());
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
        for (Map.Entry<ParkingSpotType, Deque<ParkingSpot>> entry : parkingSpots.entrySet()) {
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
                .getFirst();
        parkingSpots.remove(parkingSpot.getParkingSpotId());
        usedParkingSpots.put(parkingSpot.getParkingSpotId(), parkingSpot);
        return parkingSpot;
    }

    private ParkingSpot vacateSpot(ParkingSpot parkingSpot) {
        parkingSpot.freeSpot();
        parkingSpots.get(parkingSpot.getParkingSpotType())
                .addFirst(usedParkingSpots.remove(parkingSpot.getParkingSpotId()));
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
