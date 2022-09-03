package org.revature.entity;

public class Employee {

    // declare the variables
    int id;
    String firstname;
    String lastname;
    String occupation;

    String username;
    String password;


    // create default constructor

    public Employee() {

    }
    // create the constructor contains the optional information from the user

    public Employee(int id, String firstname, String lastname, String occupation, String username, String password) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.occupation = occupation;
        this.username = username;
        this.password = password;
    }

    //constructor that without id
    public Employee(String firstname, String lastname, String occupation, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.occupation = occupation;
        this.username = username;
        this.password = password;
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", occupation='" + occupation + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}



