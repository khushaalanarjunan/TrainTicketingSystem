package com.example.trainticketsystem_hashmapbeatstherest;

import java.util.Date;

public class Ticket {
    String ticketID;
    String ticketOrigin, ticketDestination;
    String ticketPax;
    String ticketSeat;
    Date ticketDate;
    float ticketPrice;


    public Ticket(String ticketID, String ticketOrigin, String ticketDestination, String ticketPax, float ticketPrice, String ticketSeat, Date ticketDate) {
        this.ticketID = ticketID;
        this.ticketOrigin = ticketOrigin;
        this.ticketDestination = ticketDestination;
        this.ticketPax = ticketPax;
        this.ticketDate = ticketDate;
        this.ticketPrice = ticketPrice;
        this.ticketSeat = ticketSeat;
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public String getTicketOrigin() {
        return ticketOrigin;
    }

    public void setTicketOrigin(String ticketOrigin) {
        this.ticketOrigin = ticketOrigin;
    }

    public String getTicketDestination() {
        return ticketDestination;
    }

    public void setTicketDestination(String ticketDestination) {
        this.ticketDestination = ticketDestination;
    }

    public String getTicketPax() {
        return ticketPax;
    }

    public void setTicketPax(String ticketPax) {
        this.ticketPax = ticketPax;
    }

    public String getTicketSeat() {
        return ticketSeat;
    }

    public void setTicketSeat(String ticketSeat) {
        this.ticketSeat = ticketSeat;
    }

    public Date getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(Date ticketDate) {
        this.ticketDate = ticketDate;
    }

    public float getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
}
