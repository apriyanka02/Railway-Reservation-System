package service;

import Repo.TicketDB;
import model.*;

import java.util.ArrayList;

public class BookingManager {

    TicketDB ticketDB=new TicketDB();

    ArrayList<Tickets> ConfirmedList=new ArrayList<>();
    ArrayList<Tickets>RAC=new ArrayList<>();
    ArrayList<Tickets>WL=new ArrayList<>();

    private  int lower=2;
    private  int upper=2;
    private  int middle=2;
    private  int SideUpper=2;
    private  int SideLower=2;

    private int TicketId=101;

    private Passenger passenger;
    public void bookTickets(String name, int age, Gender gender, BerthPreference berthPrefered)
    {

        Tickets tickets = null;
        if(ConfirmedList.size()<2)
        {
            tickets=checkConfirmedAvailability(name, age, gender, berthPrefered, tickets );
        }
        else if(RAC.size()<2){
            tickets=checkRACAvailability(name, age, gender, tickets);
        }

        if(tickets!=null)
        {
            System.out.println("----------------------------------------------------------------------------");
            System.out.printf("Name: %s Age:%d Gender: %s BerthAllocated: %s Status: %s%n", name, age, gender, tickets.getBerthAllocated(), Status.CONFIRMED);
            System.out.println("Successfully Booked Tickets");
            System.out.println("----------------------------------------------------------------------------");

        }
        else {
            System.out.println("No seats available");
        }
    }

    private Tickets checkRACAvailability(String name, int age, Gender gender, Tickets tickets) {
        if(SideLower>0)
        {
            passenger=new Passenger(name,age,gender,BerthPreference.SL);
            tickets=new Tickets(TicketId,passenger,Status.RAC,BerthPreference.SL);
            ticketDB.passengerList.put(TicketId,tickets);

            RAC.add(tickets);
            SideLower--;
        }
        else {
            System.out.println("No available tickets in RAC");
        }
        return tickets;
    }

    private Tickets checkConfirmedAvailability(String name, int age, Gender gender, BerthPreference berthPrefered, Tickets tickets) {
        if(berthPrefered==BerthPreference.L)
        {
            if(lower>0)
            {
                passenger=new Passenger(name,age,gender,berthPrefered);
                tickets=new Tickets(TicketId,passenger, Status.CONFIRMED,BerthPreference.L);
                ticketDB.passengerList.put(TicketId,tickets);
                ConfirmedList.add(tickets);
                TicketId++;
                lower--;

            }
            else {
                System.out.println("No Seats under LowerBerth");
            }
        }
        else if (berthPrefered==BerthPreference.M) {
            if(middle>0)
            {
                passenger=new Passenger(name,age,gender,berthPrefered);
                tickets=new Tickets(TicketId,passenger, Status.CONFIRMED,BerthPreference.M);
                ticketDB.passengerList.put(TicketId,tickets);
                ConfirmedList.add(tickets);
                middle--;

                TicketId++;
            }
            else {
                System.out.println("No Seats under MiddleBerth");
            }
        } else if (berthPrefered==BerthPreference.U) {
            if(upper>0)
            {
                passenger=new Passenger(name,age,gender,berthPrefered);
                tickets=new Tickets(TicketId,passenger, Status.CONFIRMED,BerthPreference.U);
                ticketDB.passengerList.put(TicketId,tickets);
                ConfirmedList.add(tickets);
                upper--;

                TicketId++;
            }
            else {
                System.out.println("No Seats under UpperBerth");
            }
        } else if (berthPrefered==BerthPreference.SU) {
            if(SideUpper>0)
            {
                passenger=new Passenger(name,age,gender,berthPrefered);
                tickets=new Tickets(TicketId,passenger, Status.CONFIRMED,BerthPreference.SU);
                ticketDB.passengerList.put(TicketId,tickets);
                ConfirmedList.add(tickets);
                SideUpper--;

                TicketId++;
            }
            else {
                System.out.println("No Seats under LowerBerth");
            }
        }
        return tickets;
    }

    public void cancelTicket() {
    }

    public void printBookedTickets() {
    }

    public void printAvailableTickets() {
    }
}
