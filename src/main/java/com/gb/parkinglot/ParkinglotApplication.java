package com.gb.parkinglot;

import com.gb.parkinglot.model.account.Account;
import com.gb.parkinglot.model.account.Admin;
import com.gb.parkinglot.model.parking.ParkingFloor;
import com.gb.parkinglot.model.parking.ParkingLot;

public class ParkinglotApplication {
    public static void main(String[] args) {
        ParkingLot parkingLot = ParkingLot.INSTANCE;

        Account adminAccount = new Admin();
        ((Admin) adminAccount).addParkingFloor(new ParkingFloor("1"));
        ((Admin) adminAccount).addParkingFloor(new ParkingFloor("1"));
    }
}
