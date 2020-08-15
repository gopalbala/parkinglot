package com.gb.parkinglot;

import com.gb.parkinglot.exceptions.InvlaidParkingFloorException;
import com.gb.parkinglot.model.account.Account;
import com.gb.parkinglot.model.account.Admin;
import com.gb.parkinglot.model.parking.*;
import com.gb.parkinglot.model.vehicle.Car;
import com.gb.parkinglot.model.vehicle.Van;
import com.gb.parkinglot.model.vehicle.Vehicle;
import com.gb.parkinglot.model.vehicle.VehicleType;

public class ParkinglotApplication {
    public static void main(String[] args) throws InvlaidParkingFloorException {
        ParkingLot parkingLot = ParkingLot.INSTANCE;

        Account adminAccount = new Admin();
        ((Admin) adminAccount).addParkingFloor(new ParkingFloor("1"));
        ((Admin) adminAccount).addParkingFloor(new ParkingFloor("2"));

        EntrancePanel entrancePanel = new EntrancePanel("1");
        ((Admin) adminAccount).addEntrancePanel(entrancePanel);

        String floorId = parkingLot.getParkingFloors().get(0).getFloorId();

        ParkingSpot carSpot1 = new CarParkingSpot("1");
        ((Admin) adminAccount).addParkingSpot(floorId, carSpot1);
        ParkingSpot bikeSport = new MotorBikeParkingSpot("1");
        ((Admin) adminAccount).addParkingSpot(floorId, bikeSport);
        ParkingSpot carSpot2 = new CarParkingSpot("2");
        ((Admin) adminAccount).addParkingSpot(floorId, carSpot2);

        // Test case 1 - check for availability of parking lot - TRUE
        System.out.println(ParkingLot.INSTANCE.canPark(VehicleType.CAR));

        // Test case 2 - check for availability of parking lot - FALSE
        System.out.println(ParkingLot.INSTANCE.canPark(VehicleType.CAR));

        // Test case 3 - check for availability of parking lot - FALSE
        System.out.println(ParkingLot.INSTANCE.canPark(VehicleType.ELECTRIC));

        // TEST case 4 - Check if full
        System.out.println(ParkingLot.INSTANCE.isFull());

        // Test case 5 - get parking spot
        Vehicle vehicle = new Car("KA05MR2311");
        ParkingSpot availableSpot = ParkingLot.INSTANCE.getParkingSpot(vehicle.getType());
        System.out.println(availableSpot.getParkingSpotType());
        System.out.println(availableSpot.getParkingSpotId());

        // Test case 6 - should not be able to get spot
        Vehicle van = new Van("KA01MR7804");
        ParkingSpot vanSpot = ParkingLot.INSTANCE.getParkingSpot(van.getType());
        System.out.println(null == vanSpot);

        //Test case 7 - Entrance Panel - 1
        System.out.println(ParkingLot.INSTANCE.getEntrancePanels().size());

        // Test case - 8 - Should be able to get parking ticket
        ParkingTicket parkingTicket = entrancePanel.getParkingTicket(vehicle);
        System.out.println(parkingTicket.getAllocatedSpotId());

        // Test case - 9 - Should be able to get parking ticket
        Vehicle car = new Car("KA02MR6355");
        ParkingTicket parkingTicket1 = entrancePanel.getParkingTicket(car);

        // Test case 10 - Should not be able to get ticket
        ParkingTicket tkt = entrancePanel.getParkingTicket(new Car("ka04rb8458"));
        System.out.println(null == tkt);
    }
}
