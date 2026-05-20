package model;

public class Tickets {
    private  int TicketId;
    private Passenger passenger;
    private Status status;
    private  BerthPreference berthAllocated;


    public Tickets(int ticketId, Passenger passenger, Status status, BerthPreference berthAllocated) {
        TicketId = ticketId;
        this.passenger = passenger;
        this.status = status;
        this.berthAllocated = berthAllocated;

    }

    public int getTicketId() {
        return TicketId;
    }

    public void setTicketId(int ticketId) {
        TicketId = ticketId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BerthPreference getBerthAllocated() {
        return berthAllocated;
    }

    public void setBerthAllocated(BerthPreference berthAllocated) {
        this.berthAllocated = berthAllocated;
    }


}
