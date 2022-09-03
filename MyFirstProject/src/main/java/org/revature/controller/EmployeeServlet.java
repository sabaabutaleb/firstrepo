package org.revature.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.revature.entity.Employee;
import org.revature.entity.Ticket;
import org.revature.service.EmployeeService;
import org.revature.service.TicketService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
//This servlet Deals with register and log in
public class EmployeeServlet extends HttpServlet {
    //log in functionality
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //create instance of Employee service
        resp.setContentType("text/html");
        EmployeeService employeeService = new EmployeeService();
        PrintWriter out = resp.getWriter();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Employee employee = employeeService.getEmployee(req.getParameter("username"));

        if (employee == null) {
            out.println("<h1>You are not registered yet please register first </h1>");
            return;
        }
        if (username.equals(employee.getUsername()) && password.equals(employee.getPassword())) {
            out.println("<h1>welcome " + username + " to your page</h1>");
            req.getSession().setAttribute("username", employee.getUsername());
            //to save his occupation to check the manager role
            req.getSession().setAttribute("occupation", employee.getOccupation());

        } else {
            out.println("<h1>check your username and passwrod</h1>");
        }

        // otherwise print out the person:
        out.println(employee.getUsername());
    }

    @Override
    //Register functionality
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        //System.out.println("testing::dopost method from employee servlet(registration)");
        EmployeeService employeeService = new EmployeeService();
        ObjectMapper objectMapper = new ObjectMapper();
        PrintWriter out = resp.getWriter();
        Employee employee;
//        // try to get the person username
        String username = req.getParameter("username");

        try {
            employee = objectMapper.readValue(req.getReader(), Employee.class);

            System.out.println(employee.toString());
            employee = employeeService.insertEmployee(employee);
            if (employee == null) {
                out.println("<h1> Employee already registered</h1>");
            } else {
                out.println("<h1> Registration successful</h1>");
                out.println(employee);
            }
        } catch (Exception e) {
            out.println(" check format");
            e.printStackTrace();
            resp.sendError(400, " check format");
        }
    }
}

