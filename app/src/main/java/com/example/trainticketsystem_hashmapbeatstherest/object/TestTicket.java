package com.example.trainticketsystem_hashmapbeatstherest.object;

public class TestTicket {
    String origin, destination, pax, departureDate, returnDate;

    public TestTicket() {
    }

    public TestTicket(String departureDate, String destination, String origin, String pax, String returnDate) {
        this.origin = origin;
        this.destination = destination;
        this.pax = pax;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getPax() {
        return pax;
    }

    public void setPax(String pax) {
        this.pax = pax;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
