package org.revature.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.revature.entity.Employee;
import org.revature.entity.Ticket;
import org.revature.service.EmployeeService;
import org.revature.service.TicketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
//doing employee functionality with tickets like retreiving/filtering /submitting
public class TicketServlet extends HttpServlet {
    //submit ticket
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("testing:  doPost called to submit ticket from ticket servlet");
        //first create instance from ticket service
        //then create obj from Print writer to read data from response and write it
        //we have to create mapper object to convert the data from JSON to java code
        //create ticket instance then we have to call the method insert ticket from the service layer
        //print it on the response page
        resp.setContentType("text/html");
        TicketService ticketService = new TicketService();
        PrintWriter out = resp.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        Ticket ticket;
        String username;


        try {
            // trying to access the session and get the id of the current logged in user:
            username = (String) req.getSession().getAttribute("username");
        } catch (Exception e) {
            resp.sendError(401, "you have to log in");
            return;
        }
        try {
            ticket = objectMapper.readValue(req.getReader(), Ticket.class);
        } catch (Exception e) {
            out.println("Please check the format.");
            e.printStackTrace();
            resp.sendError(400, "Please check the format");
            return;
        }
        System.out.println(ticket.toString());
        ticket = ticketService.insertTicket(ticket);
        out.println("successfull submission");
        out.println(ticket);
    }

    @Override//get the tickets and filtering
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        System.out.println("testing:  doGet called for getting tickets from ticket servlet");
        //getting the path info to determine the selected employeeId and removing the / and converting it to int
        TicketService ticketService = new TicketService();
        List<Ticket> ticket;
        String status = req.getParameter("status");
        System.out.println("from paramter" + status);//testing
        String username = (String) req.getSession().getAttribute("username");
        //to do --check if the id is for this employee
       // Employee employee = employeeService.getEmployee(req.getParameter("username"));
        //  if (username.equals(employee.getUsername()) && employeeId == employee.getId()) {

        try {
            // trying to access the session and get the username of the current logged in employee:
            //              String username = (String) req.getSession().getAttribute("username");
           System.out.println("from try get attribute session");//testing
        } catch (Exception e) {
            // if we don't get the username
            System.out.println("from catch get attribute session");//testing

            resp.sendError(401, "You have to log in first");
            return;
        }
        if (status == null) {
            String pathInfo = req.getPathInfo();
            //if the user didnt put his id
            if (pathInfo == null) {
                System.out.println("please enter your ID");
                out.println("please enter your ID");
            } else {
                String emlpoyeeId = pathInfo.substring(1);
                int employeeId = Integer.parseInt(emlpoyeeId);
                System.out.println(employeeId);//testing
                out.println("The employee id you select is: " + employeeId + '\n' + "His Tickets as follows:");
                List<Ticket> employeeTickets = ticketService.getById(employeeId);
                out.println(employeeTickets);
//            out.println(status);//testing
            }
        } else if (status != null) {

            try {
                    String pathInfo = req.getPathInfo();
                    String emlpoyeeId = pathInfo.substring(1);
                    int employeeId = Integer.parseInt(emlpoyeeId);
        // trying to access the session and get tickit status
                //status = (String) req.getSession().getAttribute("status");
                //status = req.getParameter("status");
                List<Ticket> filteredTickets = ticketService.getTicketByStatus(status, employeeId);
                System.out.println("entered try filteredtickets");
                out.println(filteredTickets);


            } catch (Exception e) {
                resp.sendError(401, "no data found ");
                return;
            }

        }

    }
}



