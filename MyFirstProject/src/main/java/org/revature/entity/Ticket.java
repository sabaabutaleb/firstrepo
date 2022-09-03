package org.revature.entity;

public class Ticket {
    private int id;
    private double amount;
    private String description;
    private String status;
    private int employeeIdFK;


    private Ticket(){

    }

    public Ticket(int id, double amount, String description, String status, int employeeidFK) {
        this.id= id;
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.employeeIdFK = employeeidFK;
    }
    public Ticket( double amount, String description, String status, int employeeIdFK) {
        this.amount = amount;
        this.description = description;
        this.status = status;
        this.employeeIdFK = employeeIdFK;
    }
    // without the ids keys
    public Ticket(double amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id= id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getEmployeeIdFK() {
        return employeeIdFK;
    }

    public void setEmployeeIdFK(int employeeidFK) {
        this.employeeIdFK = employeeidFK;
    }
    public String toString() {
        return "Employee{" +
                "ticket id=" + id +
                ", amount'= " + amount + '\'' +
                ", description'= " + description + '\'' +
                ", status'= " + status + '\'' +
                ", employee id'= " + employeeIdFK + '\'' +
                '}';
    }
}
