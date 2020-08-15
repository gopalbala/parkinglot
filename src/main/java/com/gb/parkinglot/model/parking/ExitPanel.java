package com.gb.parkinglot.model.parking;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ExitPanel {
    private String id;

    public ParkingTicket scanTicket(ParkingTicket parkingTicket) {
        ParkingSpot parkingSpot =
                ParkingLot.INSTANCE.vacateParkingSpot(parkingTicket.getAllocatedSpotId());
        parkingTicket.setAmount(calculateCost(parkingTicket, parkingSpot.getParkingSpotType()));
        return parkingTicket;
    }

    public void makePayment(ParkingTicket parkingTicket) {
        parkingTicket.setPayedAt(LocalDateTime.now());
        parkingTicket.setTicketStatus(TicketStatus.PAID);
    }

    private double calculateCost(ParkingTicket parkingTicket, ParkingSpotType parkingSpotType) {
        Duration duration = Duration.between(parkingTicket.getIssuedAt(), LocalDateTime.now());
        long hours = duration.toHours();
        double amount = hours * new HourlyCost().getCost(parkingSpotType);
        return amount;
    }
}
