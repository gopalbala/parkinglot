package com.gb.parkinglot.model.parking;

import com.gb.parkinglot.model.account.common.Address;
import com.gb.parkinglot.model.vehicle.VehicleType;
import lombok.Getter;
import lombok.Setter;

import java.util.BitSet;
import java.util.List;

@Getter
@Setter
public class ParkingLot {
    private String parkingLotId;
    private Address address;
    private List<ParkingFloor> parkingFloors;
    private List<EntrancePanel> entrancePanels;
    private List<ExitPanel> exitPanels;

    public static ParkingLot INSTANCE = new ParkingLot();

    private ParkingLot() {

    }

    public boolean isFull() {
        int index = 0;
        BitSet bitSet = new BitSet();
        for (ParkingFloor parkingFloor : parkingFloors) {
            bitSet.set(index++, parkingFloor.isFloorFull());
        }
        return bitSet.cardinality() == bitSet.size();
    }

    public boolean canPark(VehicleType vehicleType, boolean handicapped) {
        for (ParkingFloor parkingFloor : parkingFloors) {
            if (parkingFloor.canPark(vehicleType, handicapped))
                return true;
        }
        return false;
    }

}
