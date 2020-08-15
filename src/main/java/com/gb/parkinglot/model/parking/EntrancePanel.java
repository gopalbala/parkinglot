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

    public ParkingTicket getParkingTicket(Vehicle vehicle, boolean handicapped) {
        if (ParkingLot.INSTANCE.isFull())
            return null;
        ParkingSpot parkingSpot = ParkingLot.INSTANCE.getParkingSpot(vehicle, handicapped);
        if (parkingSpot == null)
            return null;
        return buildTicket(vehicle.getLicenseNumber());
    }

    private ParkingTicket buildTicket(String vehicleLicenseNumber) {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicket.setIssuedAt(LocalDateTime.now());
        parkingTicket.setLicensePlateNumber(vehicleLicenseNumber);
        parkingTicket.setTicketNumber(UUID.randomUUID().toString());
        parkingTicket.setTicketStatus(TicketStatus.ACTIVE);
        return parkingTicket;
    }
}
