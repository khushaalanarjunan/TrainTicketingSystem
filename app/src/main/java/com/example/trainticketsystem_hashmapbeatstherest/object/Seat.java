package com.example.trainticketsystem_hashmapbeatstherest.object;

import com.example.trainticketsystem_hashmapbeatstherest.enums.SeatType;

public class Seat {
    private String id;
    private String coach;
    private int column;
    private boolean isBooked;
    private double price;
    private int row;
    private SeatType type;

    public Seat() {
    }

    public Seat(String id, String coach, int column, boolean isBooked, double price, int row, String type) {
        this.id = id;
        this.coach = coach;
        this.column = column;
        this.isBooked = isBooked;
        this.price = price;
        this.row = row;
        this.type = SeatType.valueOf(type);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getType() {
        return type.name;
    }

    public void setType(String type) {
        this.type = SeatType.valueOf(type);
    }
}
