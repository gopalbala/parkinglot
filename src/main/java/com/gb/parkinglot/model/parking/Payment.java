package com.gb.parkinglot.model.parking;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class Payment {
    private String id;
    private String ticketId;
    private double amount;

    @Setter
    private LocalDateTime initiatedDate;
    @Setter
    private LocalDateTime completedDate;
    @Setter
    private PaymentStatus paymentStatus;

    public Payment(String id, String ticketId, double amount) {
        this.id = id;
        this.ticketId = ticketId;
        this.amount = amount;
    }

}
