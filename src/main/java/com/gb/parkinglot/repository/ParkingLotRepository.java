package com.gb.parkinglot.repository;

import com.gb.parkinglot.exceptions.InvalidParkingLotException;
import com.gb.parkinglot.model.parking.ParkingFloor;
import com.gb.parkinglot.model.parking.ParkingLot;

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
}
