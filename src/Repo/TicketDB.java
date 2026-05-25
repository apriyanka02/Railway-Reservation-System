package Repo;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketDB {
    public Map<Integer, Tickets> passengerList=new HashMap<>();

    public void insertTicket(Tickets tickets)
    {
        String query=
                "INSERT INTO tickets values(?,?,?,?,?,?)";
        try{
            Connection connection=DBConnection.getConnection();

            PreparedStatement preparedStatement=connection.prepareStatement(query);

            preparedStatement.setInt(1,tickets.getTicketId());

            preparedStatement.setString(2,tickets.getPassenger().getName());

            preparedStatement.setInt(3,tickets.getPassenger().getAge());

            preparedStatement.setString(4,tickets.getPassenger().getGender().toString());

            preparedStatement.setString(5,tickets.getPassenger().getBerthPreference().toString());

            preparedStatement.setString(6,tickets.getStatus().toString());

            int rows = preparedStatement.executeUpdate();

            System.out.println(
                    rows + " row inserted"
            );
            System.out.println("Insert successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewAll(Tickets tickets)
    {
        String query="Select * from tickets";
        try {
            Connection connection=DBConnection.getConnection();

            PreparedStatement statement=connection.prepareStatement(query);
            statement.executeQuery(query);
            System.out.println("Printed All Rows");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void delete(int ticketID)
    {
        String query="Delete from tickets where ticketId=?";

        try {
            Connection connection=DBConnection.getConnection();
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1,ticketID);

            int rows=statement.executeUpdate();
            if(rows>0)
            {
                System.out.println("Ticket cancelled");
            }
            else {
                System.out.println("Not cancelled");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void update(Tickets tickets)
    {
        String query="Update tickets set name=?,age=?,gender=?,berthAllocated=?, status=? where ticketId=?";

        try {
            Connection connection=DBConnection.getConnection();
            PreparedStatement statement=connection.prepareStatement(query);

           statement.setString(1,tickets.getPassenger().getName());

           statement.setInt(2,tickets.getPassenger().getAge());

           statement.setString(3, String.valueOf(tickets.getPassenger().getGender()));

           statement.setString(4,String.valueOf(tickets.getBerthAllocated()));

           statement.setString(5,String.valueOf(tickets.getStatus()));

           statement.setInt(6,tickets.getTicketId());

            int rows=statement.executeUpdate();
            if(rows>0)
            {
                System.out.println("Updated successfully");
            }
            else {
                System.out.println("Not Updated");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
