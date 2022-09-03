package org.revature.controller;

import org.revature.entity.Employee;
import org.revature.service.EmployeeService;
import org.revature.entity.Ticket;
import org.revature.*;

import  org.revature.service.TicketService;

import java.util.List;
import java.util.Scanner;

public class EmployeeCommandLineInterface {

        public static void menu() {
            EmployeeService employeeService = new EmployeeService();
            TicketService ticketService = new TicketService();
            Scanner intscanner = new Scanner(System.in);
            Scanner stringscanner = new Scanner(System.in);
            Scanner doublescanner = new Scanner(System.in);
            Employee employee = null;

            while (true) {
                printOptions();
                int selestion = intscanner.nextInt();
                switch (selestion) {
                    case 1:
                        if (employee != null) {
                            System.out.println("You already logged in");
                            break;
                        }
                        System.out.println("Enter the following fields to register:");
                        System.out.print("Enter your first name: ");
                        String firstname = stringscanner.nextLine();
                        System.out.print("Enter your last name: ");
                        String lastname = stringscanner.nextLine();
                        System.out.print("Enter your occupation: ");
                        String occupation = stringscanner.nextLine();
                        System.out.print("Enter your user name: ");
                        String username = stringscanner.nextLine();
                        System.out.print("Enter your password: ");
                        String password = stringscanner.nextLine();
                        //create employee object to give it these date
                        Employee newemployee = new Employee(firstname, lastname, occupation, username, password);
                        //create an instance of employeeservice
                        // EmployeeService employeeservice = new EmployeeService();
                        //to see how we insert the employee --remove lator
                        //once we took the data from the cmdline we called the service
                        System.out.println(employeeService.insertEmployee(newemployee));
                        break;
                    case 2:
                        if (employee != null) {
                            System.out.println("You already logged in");
                            break;
                        }
                        System.out.println("Enter the following fields to log in:");
                        System.out.print("Enter your user name: ");
                        username = stringscanner.nextLine();
                        System.out.print("Enter your password: ");
                        password = stringscanner.nextLine();
                        System.out.println(employeeService.getEmployee(username));
                        break;
                    case 3:
////                        if (employee ==null){
////                            System.out.println("You have to log in");
////                            break;
//                        } else{
                        System.out.print("Enter the amount of your ticket: ");
                        double amount = intscanner.nextDouble();
                        System.out.print("Enter description: ");
                        String description = stringscanner.nextLine();

                        // int id = employeeGeneratedId;
                        Ticket newTicket = new Ticket(amount, description);
                        System.out.println(ticketService.insertTicket(newTicket));
                        break;

                    case 4:
                        System.out.print("Enter your ID: ");
                        int employerId = intscanner.nextInt();
                        List<Ticket> employerTickets = ticketService.getById(employerId);
                        System.out.println("Your tickets: ");
                        for (Ticket ticket : employerTickets) {
                            System.out.println(employerTickets);
                        }

                    case 5:
                        //View All Tickets
//                        if (employee == null) {
//                            System.out.println("Please log in first");
//                            break;
//                        }
                        List<Ticket> tickets = ticketService.viewAll();

                        System.out.println("Here the tickets list :");
                        for (Ticket ticket : tickets) {
                            System.out.println(ticket);
                        }

                        break;

                    case 6:
                        System.out.print("Enter ticket Id: ");
                        int tickectId = intscanner.nextInt();
                        System.out.println("Process new status: approved , denied");
                        String newStatus=stringscanner.nextLine();
                        //Ticket ticket = new Ticket(tickectId,double amount,String description,newStatus,employerId);
                       // System.out.println(ticketService.updateTicket(Ticket));



                    default:
                        System.out.println("Please insert your selection again, invalid option");
                }
            }

        }
    public static void printOptions() {
        System.out.println("Please insert selection: ");
        System.out.println("General Options>>>>>");
        System.out.println("1. Register: ");
        System.out.println("2. Log In: ");
        System.out.println("Employee Options:>>>>>");
        System.out.println("3.Submit Ticket");//must has amount ana description
        System.out.println("4.View previous submitted tickets");//get all and can filterd by approved pending denied
        //View Previous Submissions, filter by pending/approved/denied

        System.out.println("Manager Options>>>>>>");
        System.out.println("5. View All Tickets");
        System.out.println("6. Process ticket: ");//Approve/Deny Ticket, update the table (In Java code, you could check what the role is)
    }




}



