package com.gb.parkinglot.model.parking;

import lombok.Getter;

@Getter
public class EntrancePanel {
    private String id;

    public EntrancePanel(String id) {
        this.id = id;
    }

    public ParkingTicket printTicket() {
        return null;
    }
}
