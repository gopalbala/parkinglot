package com.gb.parkinglot.model.parking;

import com.gb.parkinglot.model.account.common.Address;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ParkingLot {
    private String parkingLotId;
    private Address address;
    private List<ParkingFloor> parkingFloors;
    private List<EntrancePanel> entrancePanels;
    private List<ExitPanel> exitPanels;
}
