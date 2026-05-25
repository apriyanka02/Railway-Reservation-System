package view;

import model.BerthPreference;
import model.Gender;
import model.Passenger;
import service.BookingManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IO_Handler {

    private Scanner scanner;
    private BookingManager bookingManager;

    public IO_Handler() {
        scanner = new Scanner(System.in);
        bookingManager=new BookingManager();
    }

    public void start() {


        while(true)
        {
            System.out.println("----------------------------------------------------");
            System.out.println("RailWay Ticket Booking Management");
            System.out.println("----------------------------------------------------");

            System.out.println("1.Book Ticket");
            System.out.println("2.Cancel Ticket");
            System.out.println("3.Print Booked Ticket");
            System.out.println("4.Print available Ticket");
            System.out.println("5.Exit");
            System.out.println("Enter ur choice:");
            int choice=scanner.nextInt();

            switch (choice)
            {
                case 1:
                    getPassengerDetails();
                    break;
                case 2:
                    getCancelDetails();

                    break;
                case 3:
                   bookingManager.printBookedTickets();
                   break;
                case 4:
                   bookingManager. printAvailableTickets();
                    break;
                case 5:
                    System.exit(0);
                    break;
            }

        }
    }

    private void getCancelDetails() {
        System.out.println("Enter you TicketID:");
        int ticketID=scanner.nextInt();

        bookingManager.cancelTicket(ticketID);

    }

    private void getPassengerDetails() {
        System.out.println("Enter ur name:");
        String name=scanner.next();

        System.out.println("Enter ur age");
        int age=scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter ur Gender");
        String gender1=scanner.nextLine();
        Gender gender=Gender.valueOf(gender1.toUpperCase());

        List<Passenger> children=new ArrayList<>();
        if(gender==Gender.F)
        {
            Passenger passenger=checkChildren();
            if(passenger!=null)
            {
                children.add(passenger);
            }

        }

        System.out.println("Enter ur BerthPreference");
        String berthPrefered=scanner.next();
        BerthPreference bp=BerthPreference.valueOf(berthPrefered.toUpperCase());


        bookingManager.bookTickets(name,age,gender,bp,children);

    }

    private Passenger checkChildren() {
        System.out.println("Do u have children below age 5:(Y/N)");
        String option=scanner.next();
        if(option.equalsIgnoreCase("Y"))
        {
            return getChildrenDetails();
        }
        return  null;
    }

    private Passenger getChildrenDetails() {
        System.out.println("Enter ur child name:");
        String cname= scanner.next();

        System.out.println("Enter age:");
        int cage=scanner.nextInt();

        System.out.println("Enter gender(MALE/FEMALE):");
        String gen=scanner.next();
        Gender gend=Gender.valueOf(gen.toUpperCase());


        Passenger passenger=new Passenger(cname,cage,gend);


        return passenger;

    }
}
