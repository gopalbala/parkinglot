package com.gb.parkinglot.model.parking;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ParkingTicket {
    private String ticketNumber;
    private LocalDateTime issuedAt;
    private LocalDateTime payedAt;
    private double amountPaid;
    private TicketStatus ticketStatus;
}
