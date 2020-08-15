package com.gb.parkinglot;

import com.gb.parkinglot.exceptions.InvlaidParkingFloorException;
import com.gb.parkinglot.model.account.Account;
import com.gb.parkinglot.model.account.Admin;
import com.gb.parkinglot.model.parking.*;
import com.gb.parkinglot.model.vehicle.Car;
import com.gb.parkinglot.model.vehicle.Vehicle;
import com.gb.parkinglot.model.vehicle.VehicleType;

public class ParkinglotApplication {
    public static void main(String[] args) throws InvlaidParkingFloorException {
        ParkingLot parkingLot = ParkingLot.INSTANCE;

        Account adminAccount = new Admin();
        ((Admin) adminAccount).addParkingFloor(new ParkingFloor("1"));
        ((Admin) adminAccount).addParkingFloor(new ParkingFloor("2"));

        String floorId = parkingLot.getParkingFloors().get(0).getFloorId();

        ParkingSpot carSpot1 = new CarParkingSpot("1");
        ((Admin) adminAccount).addParkingSpot(floorId, carSpot1);
        ParkingSpot bikeSport = new MotorBikeParkingSpot("1");
        ((Admin) adminAccount).addParkingSpot(floorId, bikeSport);
        ParkingSpot carSpot2 = new CarParkingSpot("2");
        ((Admin) adminAccount).addParkingSpot(floorId, carSpot2);

        // Test case 1 - check for availability of parking lot - TRUE
        System.out.println(ParkingLot.INSTANCE.canPark(VehicleType.CAR, false));

        // Test case 2 - check for availability of parking lot - FALSE
        System.out.println(ParkingLot.INSTANCE.canPark(VehicleType.CAR, true));

        // Test case 3 - check for availability of parking lot - FALSE
        System.out.println(ParkingLot.INSTANCE.canPark(VehicleType.ELECTRIC, false));

        // TEST case 4 - Check if full
        System.out.println(ParkingLot.INSTANCE.isFull());

        // Test case 5 - get parking spot
        Vehicle vehicle = new Car("KA05MR2311");
        ParkingSpot availableSpot = ParkingLot.INSTANCE.getParkingSpot(vehicle.getType());
        System.out.println(availableSpot.getParkingSpotType());
        System.out.println(availableSpot.getParkingSpotId());

    }
}
