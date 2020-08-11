package com.gb.parkinglot.repository;

import com.gb.parkinglot.exceptions.InvalidParkingLotException;
import com.gb.parkinglot.exceptions.InvlaidParkingFloor;
import com.gb.parkinglot.model.parking.ParkingFloor;
import com.gb.parkinglot.model.parking.ParkingLot;
import com.gb.parkinglot.model.parking.ParkingSpot;

import java.util.*;

public class ParkingLotRepository {
    public static Map<String, ParkingLot> parkingLotMap = new HashMap<>();
    public static List<ParkingLot> parkingLots = new ArrayList<>();

    public ParkingLot addParkingLot(ParkingLot parkingLot) {
        parkingLotMap.putIfAbsent(parkingLot.getParkingLotId(), parkingLot);
        parkingLots.add(parkingLot);
        return parkingLot;
    }

    public ParkingLot getParkingLot(String parkingLotId) {
        return parkingLotMap.get(parkingLotId);
    }

    public ParkingFloor addParkingFloor(String parkingLotId, ParkingFloor parkingFloor)
            throws InvalidParkingLotException {
        ParkingLot parkingLot = parkingLotMap.get(parkingLotId);
        if (parkingLot == null)
            throw new InvalidParkingLotException("Invalid parking lot");

        //Idempotency
        Optional<ParkingFloor> floor = parkingLot.getParkingFloors().stream()
                .filter(pFloor -> pFloor.getFloorId()
                        .equalsIgnoreCase(parkingFloor.getFloorId())).findFirst();

        if (floor.isPresent())
            return floor.get();

        parkingLot.getParkingFloors().add(parkingFloor);
        return parkingFloor;
    }

    public ParkingSpot addParkingSpot(String parkingLotId, String parkingFloorId, ParkingSpot parkingSpot) throws InvalidParkingLotException, InvlaidParkingFloor {
        ParkingLot parkingLot = parkingLotMap.get(parkingLotId);
        if (parkingLot == null)
            throw new InvalidParkingLotException("Invalid parking lot");
        Optional<ParkingFloor> floor = parkingLot.getParkingFloors().stream()
                .filter(pFloor -> pFloor.getFloorId()
                        .equalsIgnoreCase(parkingFloorId)).findFirst();
        if (!floor.isPresent()) {
            throw new InvlaidParkingFloor("Invalid parking floor");
        }
        Optional<ParkingSpot> spot =
                floor.get().getParkingSpots().stream().filter(pSpot ->
                        pSpot.getParkingSpotId().equalsIgnoreCase(parkingSpot.getParkingSpotId())).findFirst();
        if (spot.isPresent())
            return spot.get();
        floor.get().getParkingSpots().add(parkingSpot);
        return parkingSpot;

    }
}
