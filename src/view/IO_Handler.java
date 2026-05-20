package view;

import model.BerthPreference;
import model.Gender;
import service.BookingManager;

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
            System.out.println("Enter ur choice:");
            int choice=scanner.nextInt();

            switch (choice)
            {
                case 1:
                    getPassengerDetails();
                    break;
                case 2:
                    bookingManager.cancelTicket();
                    break;
                case 3:
                   bookingManager.printBookedTickets();
                   break;
                case 4:
                   bookingManager. printAvailableTickets();
                    break;
                case 5:
                    System.out.println(0);
            }

        }
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

        System.out.println("Enter ur BerthPrefernce");
        String berthPrefered=scanner.nextLine();
        BerthPreference bp=BerthPreference.valueOf(berthPrefered.toUpperCase());
        BerthPreference.valueOf(berthPrefered.toUpperCase());

        bookingManager.bookTickets(name,age,gender,bp);
    }
}
