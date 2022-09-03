package org.revature.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.revature.entity.Ticket;
import org.revature.service.TicketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.*;

public class ManagerServlet extends HttpServlet {
    @Override
//get all tickets
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out= resp.getWriter();

        HttpSession session=req.getSession();
        String username;
        String occupation;
        try {
            // trying to access the session and get the username of the current logged in user:
            username = (String) req.getSession().getAttribute("username");
        } catch (Exception e) {
            resp.sendError(401, "Must be logged in ");
            return;
        }
//        try {
            occupation = (String) req.getSession().getAttribute("occupation");
        if (!occupation.equals("manager")){
            out.println("you have to be a manager !!");
            return;
        }
            resp.setContentType("text/html");
            System.out.println("doGet methog from manager servlet has been called/testing");
            TicketService ticketService = new TicketService();
            List<Ticket> tickets = new ArrayList<>();
            //take the id from the path  using subString to remove the slash then convert it to int
            //so we can get the tickets for this employee
            String pathInfo = req.getPathInfo();

          if (pathInfo == null) {
               // System.out.println("no data selected");//testing
                out.println("no data selected");
            }//otherwise
            else {
                //take the id from the path  using subString remove the slash then convert it to int
                //so we can to get the tickets for this employee

                String emlpoyeeId = pathInfo.substring(1);
                int employeeId = Integer.parseInt(emlpoyeeId);

                System.out.println("The employee id you select is: " + pathInfo + '\n' + "His Tickets as follows:");
                List<Ticket> employeeTickets = ticketService.getById(employeeId);
                System.out.println("The Employee tickets: ");
                out.println(employeeTickets);
                System.out.println(req.getParameter("status"));
            }
    }
//update the ticket
protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    System.out.println("testing:  doPut called for updating ticket from manager servlet");
    //first create instance from ticket service
    //then create obj from Print writer to read data from response and write it
    //we have to create mapper object to convert the data from JSON to java code
    //create ticket instance then we have to call the method insert ticket from the service layer
    //print it on the response page
    resp.setContentType("text/html");
    PrintWriter out = resp.getWriter();
    ObjectMapper objectMapper = new ObjectMapper();
    String occupation;
    String username;
    String status;
    try {
        // trying to access the session and get the username of the current logged in user:
        username = (String) req.getSession().getAttribute("username");
    } catch (Exception e) {
        resp.sendError(401, "Must be logged in ");
        return;
    }

    try {
        // trying to access the session and get occupation of the employee
        occupation = (String) req.getSession().getAttribute("occupation");
        //to check if this employee is manager
        if (!occupation.equals("manager")){
            out.println("you have to be a manager !!");
            return;
        }

        } catch (Exception e) {
        resp.sendError(401, "you have to be a manager ");
        return;
    }

    String pathInfo = req.getPathInfo();
    if (pathInfo == null) {
        out.println("please select a ticket to process");
    } else {

        TicketService ticketService = new TicketService();

        //bring the ticket by id
        String sTicketId = pathInfo.substring(1);
        int intTicketId = Integer.parseInt(sTicketId);
        Ticket ticket = ticketService.getTicketById(intTicketId);
        System.out.println("this is the ticket" + ticket);
        //check if status is pending
        if (ticket.getStatus().equals("pending")){
            try {
                    ticket = objectMapper.readValue(req.getReader(), Ticket.class);
                    ticket = ticketService.updateTicket(ticket);
                    out.println("Ticket Updated successfully");
                    out.println(ticket);
                } catch (Exception e) {
                    e.printStackTrace();
                    resp.sendError(400, "Please check the format");
                    return;
                }
        }
        else{
            out.println("Ticket already has been processed ");

        }
        }
    }
}










