package com.gb.parkinglot.model.account;

import com.gb.parkinglot.exceptions.InvalidParkingLotException;
import com.gb.parkinglot.exceptions.InvlaidParkingFloorException;
import com.gb.parkinglot.model.parking.EntrancePanel;
import com.gb.parkinglot.model.parking.ExitPanel;
import com.gb.parkinglot.model.parking.ParkingFloor;
import com.gb.parkinglot.model.parking.ParkingSpot;
import com.gb.parkinglot.repository.ParkingLotRepository;

public class Admin extends Account {
    ParkingLotRepository parkingLotRepository = new ParkingLotRepository();

    void addParkingFloor(String parkingLotId, ParkingFloor parkingFloor)
            throws InvalidParkingLotException {
        parkingLotRepository.addParkingFloor(parkingLotId,
                parkingFloor);
    }

    void addParkingSpot(String parkingLotId,
                        String parkingFloorId, ParkingSpot parkingSpot)
            throws InvlaidParkingFloorException, InvalidParkingLotException {
        parkingLotRepository.addParkingSpot(parkingLotId, parkingFloorId, parkingSpot);
    }

    void addEntrancePanel(EntrancePanel entrancePanel) {

    }

    void addExitPanel(ExitPanel exitPanel) {

    }
}
