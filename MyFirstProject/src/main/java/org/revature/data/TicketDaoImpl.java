package org.revature.data;

import org.revature.entity.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDaoImpl implements TicketDao{
    //create connection through the connectionFactory
    Connection connection;
    public TicketDaoImpl() {
        connection = ConnectionFactory.getConnection();
    }


    public Ticket insertTicket(Ticket ticket) {
        String sql= "insert into ticket (id,amount,description,status,employeeId)values(default,?,?,?,?);";
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // taking our statement and setting up the values based on where we put the ?
            preparedStatement.setDouble(1, ticket.getAmount());
            preparedStatement.setString(2, ticket.getDescription());
            preparedStatement.setString(3, ticket.getStatus());
            preparedStatement.setInt(4,ticket.getEmployeeIdFK());
            System.out.println(preparedStatement.toString());
            if (preparedStatement.executeUpdate()==1) {
                System.out.println("ticket added successfully.");
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
               int generatedId = resultSet.getInt(1);
                ticket.setId(generatedId);
                System.out.println("this is the genereated id: "+generatedId);
                // set the id to the original object:
            }
            else{
                System.out.println("Unsuccessful submission, please retry.");
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong with ticket submission");
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return ticket;
       }

    public List<Ticket> viewAll() {
        List<Ticket> tickets= new ArrayList<>();

        String sql= "select* from ticket;";
        try {
            Statement statement=connection.createStatement();
            //execute the query
            ResultSet resultSet= statement.executeQuery(sql);
            while(resultSet.next()){
                int ticketId= resultSet.getInt("id");
                String description = resultSet.getString("description");
                double amount= resultSet.getDouble("amount");
                String status = resultSet.getString("status");
                int employeeId= resultSet.getInt("employeeid");
                Ticket ticket= new Ticket(ticketId,amount,description,status,employeeId);
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong");

        }

        return tickets;
    }


    public List<Ticket> getById(int employeeId){
        List<Ticket> tickets= new ArrayList<>();

        String sql= "select* from ticket where employeeid=?;";
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(sql);

            preparedStatement.setInt(1 , employeeId);
            ResultSet resultSet= preparedStatement.executeQuery();

            //execute the query
            while(resultSet.next()){
                int ticketId= resultSet.getInt("id");
                String description = resultSet.getString("description");
                double amount= resultSet.getDouble("amount");
                String status = resultSet.getString("status");
                employeeId= resultSet.getInt("employeeid");
                Ticket ticket= new Ticket(ticketId,amount,description,status,employeeId);
                tickets.add(ticket);
                System.out.println(tickets);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No data available for this employee");
        }

        return tickets;
    }

    @Override
    public Ticket getByTicketId(int ticketId) {

       //String sql = "select* from ticket where id=?;";
        String sql= "select * from ticket where id=?;"; //and status = 'pending';";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, ticketId);
            ResultSet resultSet= preparedStatement.executeQuery();


            //execute the query
            if(resultSet.next()) {
                ticketId = resultSet.getInt("id");
                String description = resultSet.getString("description");
                double amount = resultSet.getDouble("amount");
                String status = resultSet.getString("status");
                int employeeId = resultSet.getInt("employeeid");
                Ticket updatedTicket = new Ticket(ticketId, amount, description, status, employeeId);
                System.out.println(updatedTicket);
                return updatedTicket;
            }

        } catch (SQLException e) {
            e.printStackTrace();

            System.out.println("No data available for this employee");
        }
        System.out.println("you can not make changes for this ticket,it has been processed");

        return null ;
    }


    public List<Ticket> getByTicketStatus(String ticketStatus, int employeeid) {
        List<Ticket> tickets= new ArrayList<>();

        String sql= "select * from ticket where  status = ? and employeeid=?;";
        try{
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1,ticketStatus);
            preparedStatement.setInt(2,employeeid);


            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()) {
                System.out.println("entered if result set");
                int ticketId = resultSet.getInt("id");
                String description = resultSet.getString("description");
                double amount = resultSet.getDouble("amount");
                String status = resultSet.getString("status");
                int employeeId = resultSet.getInt("employeeid");
                Ticket filteredTicket = new Ticket(ticketId, amount, description, status, employeeId);
                System.out.println(tickets);
                tickets.add(filteredTicket);
                //return tickets;
            }
//            else {
//                System.out.println("no data available");
//            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception occurred");
            throw new RuntimeException(e);
        }
        System.out.println("there is no "+ ticketStatus+" tickeits available");
        return tickets;
    }


    public Ticket updateTicket(Ticket ticket) {
         String sql= "update ticket set  amount = ?, description = ?, status = ?, employeeid = ? where id= ?;";
        // String sql="update ticket set status= ?  where id = ?;";
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(sql);

            preparedStatement.setDouble(1, ticket.getAmount());
            preparedStatement.setString(2,ticket.getDescription());
            preparedStatement.setString(3,ticket.getStatus());
            preparedStatement.setInt(4,ticket.getEmployeeIdFK());
            preparedStatement.setInt(5,ticket.getId());


            // ResultSet resultSet= preparedStatement.executeQuery(sql);

            int count= preparedStatement.executeUpdate();
            if (count==1){

                System.out.println("Ticket updated successfully");
//                int id =resultSet.getInt("id");
//                double amount= resultSet.getDouble("amount");
//                String description = resultSet.getString("description");
//                String status = resultSet.getString("status");
//                int employeeId= resultSet.getInt("employeeid");
//                Ticket updatedTicket = new Ticket(id, amount, description, status, employeeId);

                System.out.println(ticket);
                return ticket;
            }
            else {
                System.out.println("error while updating, try again");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("something went wrong");
        }
        return null;
    }
}
