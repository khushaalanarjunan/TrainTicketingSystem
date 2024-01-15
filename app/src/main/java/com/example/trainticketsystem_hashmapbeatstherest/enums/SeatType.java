package com.example.trainticketsystem_hashmapbeatstherest.enums;

public enum SeatType {
    BUSINESS("Business"),
    STANDARD("Standard"),
    ACCESIBLE("Accessible");

    final public String name;
    SeatType(String name) {
        this.name = name;
    }
}
