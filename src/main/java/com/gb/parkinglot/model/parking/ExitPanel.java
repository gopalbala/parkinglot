package com.gb.parkinglot.model.parking;

import com.gb.parkinglot.model.vehicle.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ExitPanel {
    private String id;

    public void scanTicket(ParkingTicket parkingTicket) {
        parkingTicket.setPayedAt(LocalDateTime.now());
        parkingTicket.setTicketStatus(TicketStatus.PAID);
    }

    public void makePayment(Vehicle vehicle) {
        Duration duration = Duration.between(vehicle.getTicket().getIssuedAt(), LocalDateTime.now());
        long hours = duration.toHours();
        double amount = hours * new HourlyCost().getCost(vehicle.getType());
        vehicle.getTicket().setAmountPaid(amount);
        vehicle.getTicket().setPayedAt(LocalDateTime.now());
        vehicle.getTicket().setTicketStatus(TicketStatus.PAID);
    }
}
