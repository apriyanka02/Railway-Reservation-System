package service;

import Repo.TicketDB;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BookingManager {

    TicketDB ticketDB=new TicketDB();

    ArrayList<Tickets> ConfirmedList=new ArrayList<>();
    ArrayList<Tickets>RAC=new ArrayList<>();
    ArrayList<Tickets>WL=new ArrayList<>(1);

    ArrayList<Passenger> Children=new ArrayList<>();

    private  int lower=2;
    private  int upper=2;
    private  int middle=2;
    private  int SideUpper=2;
    private  int SideLower=2;
    private int Wl=1;

    private int TicketId=101;

    private Passenger passenger;
    public Tickets bookTickets(String name, int age, Gender gender,BerthPreference berthPreference, List<Passenger> hasChildren)
    {
        Tickets tickets = null;
        if(age >= 60 ||
                (Gender.F.equals(gender)
                        && hasChildren != null
                        && !hasChildren.isEmpty())) {
           if(ConfirmedList.size()<1) {

               tickets = checkConfirmedAvailability(name, age, gender, BerthPreference.L, tickets);
               Children.addAll(hasChildren);
           }
           else {

               tickets=seatsAvailbility(name,age,gender,berthPreference,tickets);
           }
        }
        else {

            tickets=seatsAvailbility(name,age,gender,berthPreference,tickets);
        }

        if(tickets!=null)
        {
            System.out.println(berthPreference);
            System.out.println("----------------------------------------------------------------------------");
            System.out.printf("TicketID : %s Name: %s Age:%d Gender: %s BerthAllocated: %s Status: %s%n",tickets.getTicketId(),name, age, gender, tickets.getBerthAllocated(), tickets.getStatus());
            if((!(hasChildren ==null) && (!hasChildren.isEmpty())))
            {
                System.out.printf("TicketID : %s Name: %s Age:%d Gender: %s BerthAllocated: %s Status: %s%n",
                        tickets.getTicketId(),hasChildren.getLast().getName(),hasChildren.getLast().getAge(),hasChildren.getLast().getGender(),BerthPreference.NONE, tickets.getStatus());
            }
            System.out.println("Successfully Booked Tickets");
            System.out.println("----------------------------------------------------------------------------");
        }
        else {
            System.out.println("All Seats are Booked");
        }
        return tickets;
    }

    private Tickets seatsAvailbility(String name, int age, Gender gender, BerthPreference berthPrefered, Tickets tickets) {
        if(ConfirmedList.size()<1)
       {
           tickets =checkConfirmedAvailability(name, age, gender, berthPrefered, tickets);
       }
       else if(RAC.size()<1){
               tickets =checkRACAvailability(name, age, gender, tickets);

       }
       else if(WL.size()<1){
           tickets =checkWLAvailability(name, age, gender, tickets);
       }
        return tickets;
    }

    private Tickets checkWLAvailability(String name, int age, Gender gender, Tickets tickets) {
       passenger=new Passenger(name,age,gender,BerthPreference.NONE);
       tickets=new Tickets(TicketId, Status.WL, BerthPreference.NONE, passenger);
       WL.add(tickets);
       ticketDB.passengerList.put(TicketId,tickets);
        ticketDB.insertTicket(tickets);
       TicketId++;
       return tickets;
    }

    private Tickets checkRACAvailability(String name, int age, Gender gender, Tickets tickets) {
        if(SideLower>0)
        {
            passenger=new Passenger(name,age,gender,BerthPreference.SL);
            tickets=new Tickets(TicketId, Status.RAC, BerthPreference.SL, passenger);
            ticketDB.passengerList.put(TicketId,tickets);
            ticketDB.insertTicket(tickets);
            RAC.add(tickets);
            TicketId++;
            SideLower--;
        }
        else {
            System.out.println("No available tickets in RAC");
        }
        return tickets;
    }

    private Tickets checkConfirmedAvailability(String name, int age, Gender gender, BerthPreference berthPrefered, Tickets tickets) {
        if(berthPrefered.equals(BerthPreference.L))
        {
            if(lower>0)
            {
               passenger=new Passenger(name,age,gender,berthPrefered);
                tickets=new Tickets(TicketId, Status.CONFIRMED, BerthPreference.L, passenger);
                ticketDB.passengerList.put(TicketId,tickets);
                ticketDB.insertTicket(tickets);
                ConfirmedList.add(tickets);
                TicketId++;
                lower--;

            }
            else {
                System.out.println("No Seats under LowerBerth");
            }
        }
         else if (berthPrefered.equals(BerthPreference.M)) {
            if(middle>0)
            {
                passenger=new Passenger(name,age,gender,berthPrefered);
                tickets=new Tickets(TicketId, Status.CONFIRMED, BerthPreference.M, passenger);
                ticketDB.passengerList.put(TicketId,tickets);
                ticketDB.insertTicket(tickets);
                ConfirmedList.add(tickets);
                middle--;

                TicketId++;
            }
            else {
                System.out.println("No Seats under MiddleBerth");
            }
        }
        else if (berthPrefered.equals(BerthPreference.U)) {
            if(upper>0)
            {
                passenger=new Passenger(name,age,gender,berthPrefered);
                tickets=new Tickets(TicketId, Status.CONFIRMED, BerthPreference.U, passenger);
                ticketDB.passengerList.put(TicketId,tickets);
                ticketDB.insertTicket(tickets);
                ConfirmedList.add(tickets);
                upper--;

                TicketId++;
            }
            else {
                System.out.println("No Seats under UpperBerth");
            }
        } else if (berthPrefered.equals(BerthPreference.SU)) {
            if(SideUpper>0)
            {
                passenger=new Passenger(name,age,gender,berthPrefered);
                tickets=new Tickets(TicketId, Status.CONFIRMED, BerthPreference.SU, passenger);
                ticketDB.passengerList.put(TicketId,tickets);
                ticketDB.insertTicket(tickets);
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

    public void cancelTicket(int ticketID) {

        checkCancelConfirmedList(ticketID);
        checkCancelRACList(ticketID);
    }

    private void checkCancelRACList(int ticketID) {
        boolean found=false;
        int index=-1;
        for(int i=0;i<RAC.size();i++)
        {
            if(!RAC.isEmpty() && RAC.get(i).getTicketId()==ticketID)
            {
                found=true;
                index=i;
                break;
            }
        }
        if (found)
        {
            RAC.remove(index);
            ticketDB.passengerList.remove(ticketID);
            if(!WL.isEmpty())
            {
                Tickets wlticket=WL.remove(0);
                wlticket.setBerthAllocated(BerthPreference.SL);
                wlticket.setStatus(Status.RAC);
                RAC.add(index,wlticket);
                ticketDB.passengerList.put(wlticket.getTicketId(),wlticket);

            }
            System.out.println("Your Ticket has been Cancelled");
        }
        else {
            System.out.println("Ticket cancel time exceeded");
        }
    }

    private void checkCancelConfirmedList(int ticketID) {
        int index=-1;
        boolean found=false;
        BerthPreference berthallocated=null;

        for(int i=0;i<ConfirmedList.size();i++)
        {
            if(!ConfirmedList.isEmpty() && ConfirmedList.get(i).getTicketId()==ticketID)
            {
                index=i;
                found=true;
                berthallocated=ConfirmedList.get(i).getBerthAllocated();
                break;
            }
        }
        if (found)
        {
            Tickets tickets=ConfirmedList.remove(index);
            ticketDB.passengerList.remove(ticketID);
            ticketDB.delete(ticketID);
            int racticketId=-1;
            if(!RAC.isEmpty())
            {
                Tickets racticket = RAC.remove(0);
                racticket.setBerthAllocated(berthallocated);
                racticket.setStatus(Status.CONFIRMED);
                ConfirmedList.add(index,racticket);
                ticketDB.passengerList.put(racticket.getTicketId(),racticket);
                ticketDB.update(racticket);

                if(!WL.isEmpty()) {
                    Tickets wlticket = WL.remove(0);
                    wlticket.setBerthAllocated(BerthPreference.SL);
                    wlticket.setStatus(Status.RAC);
                    RAC.add(wlticket);
                    ticketDB.passengerList.put(wlticket.getTicketId(), wlticket);
                    ticketDB.update(wlticket);
                }
            } System.out.println("Your Ticket Has Been Cancelled");
        }
        else {
            System.out.println("Ticket cancel time exceeded");
        }
    }

    public void printBookedTickets() {
        System.out.println(ticketDB.passengerList.size());
        for(Map.Entry<Integer, Tickets> entry : ticketDB.passengerList.entrySet())
        {
            Tickets ticket = entry.getValue();

            System.out.println( "Ticket ID : " + ticket.getTicketId() + " Name : " +
                    ticket.getPassenger().getName() + " Age : " +
                    ticket.getPassenger().getAge() + " Gender : " +
                    ticket.getPassenger().getGender() + " Berth : " +
                    ticket.getBerthAllocated() + " Status : " + ticket.getStatus() );
            ticketDB.viewAll(ticket);
        }
    }

    public void printAvailableTickets() {
        if(ConfirmedList.size()<1) {
            System.out.println("------------------------------------------");
            System.out.println("Seats Under Confirmed List :");
            System.out.println("------------------------------------------");
            System.out.println("Lower:" + lower);
            System.out.println("Upper :" + upper);
            System.out.println("Middle:" + middle);
            System.out.println("Side Upper:" + SideUpper);
        }
        if(RAC.size()<1) {
            System.out.println("------------------------------------------");
            System.out.println("Seats Under RAC List :");
            System.out.println("------------------------------------------");
            System.out.println("Side SL:" + SideLower);
            System.out.println("------------------------------------------");
        }
        if(WL.size()<1) {
            System.out.println("Seats Under Waiting List :");
            System.out.println("------------------------------------------");
            System.out.println("Side WL:" + Wl);
        }
    }
}
