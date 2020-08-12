package com.gb.parkinglot.model.parking;

import com.gb.parkinglot.model.vehicle.Vehicle;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class EntrancePanel {
    private String id;

    public EntrancePanel(String id) {
        this.id = id;
    }

    public ParkingTicket printTicket() {
        return null;
    }

    public ParkingTicket getParkingTicket(Vehicle vehicle, boolean handicapped) {
        if (ParkingLot.INSTANCE.isFull())
            return null;

        for (ParkingFloor parkingFloor : ParkingLot.INSTANCE.getParkingFloors()) {
            ParkingSpot parkingSpot = parkingFloor.getSpot(vehicle, handicapped);
            if (parkingSpot != null) {
                vehicle.setTicket(getParkingTicket());
                return vehicle.getTicket();
            }
        }
        return null;
    }

    private ParkingTicket getParkingTicket() {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicket.setIssuedAt(LocalDateTime.now());
        parkingTicket.setTicketNumber(UUID.randomUUID().toString());
        parkingTicket.setTicketStatus(TicketStatus.ACTIVE);
        return parkingTicket;
    }
}
