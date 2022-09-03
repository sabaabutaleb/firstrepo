package org.revature.data;

import org.revature.entity.Employee;

import java.sql.*;

import static java.lang.System.out;

public class EmployeeDaoImpl implements EmployeeDao{
    //declare connection
    Connection connection ;

    //build constructor once instantiated open connection
    public EmployeeDaoImpl(){
        connection=ConnectionFactory.getConnection();
    }
    //implement the interface methods
    //will take the data from the user
    //@Override
    public Employee insertEmployee(Employee employee) {

        //takes data from user through getters and pass it in parametarized set string method called by the ps obj
        //with its places in the sql statement and send it to DB
        String sql="insert into employee (id,firstname,lastname,occupation,username,password) values (default,?,?,?,?,?);";
        try {
            //connection obj calls a ps method that tales the sql statement
            PreparedStatement preparedStatement= connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //ps obj calls set string method which takes the placeholders place as int and
            //from employee obj which calls getter methods data from the user through the getter
            //this will be the result ="insert into employee (id,firstname,lastname,occupation,username,password)
            // //values (default,'the entered fname.etc....;
            preparedStatement.setString(1,employee.getFirstname());
            preparedStatement.setString(2, employee.getLastname());
            preparedStatement.setString(3,employee.getOccupation());
            preparedStatement.setString(4,employee.getUsername());
            preparedStatement.setString(5,employee.getPassword());
            //to execute the ps
            if (preparedStatement.executeUpdate()==1) {
                out.println("Successful Registration ");
                ResultSet resultSet= preparedStatement.getGeneratedKeys();
                resultSet.next();
                int employeeGeneratedId=resultSet.getInt(1);
                employee.setId(employeeGeneratedId);
                out.println("Your ID is : "+ employeeGeneratedId );
            }
            else{
                out.println("Unsuccessful Registration from if statement");
            }

        } catch (SQLException e) {
            out.println("User name unavailable");
            e.printStackTrace();
            out.println("Unsuccessful Registration from catch statement");
            out.println("User name unavailable");
            return null;

        }

        return employee;
    }
   // @Override
    public Employee getEmployee(String username) {

        String sql = "select * from employee where username=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,username);
            ResultSet resultSet=preparedStatement.executeQuery();

            if (resultSet.next()){
                int id =resultSet.getInt("id");
                String firstname= resultSet.getString("firstname");
                String lastname= resultSet.getString("lastname");
                String occupation= resultSet.getString("occupation");
                //String username= resultSet.getString("username");
                String password= resultSet.getString("password");

                out.println("Welcome "+firstname+" "+ lastname);
                Employee employee= new Employee(id,firstname,lastname,occupation,username,password);
               // out.println(employee);

                return employee;
            }
            else{

                out.println("Error while log in ");
            }

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
        //if faild
        return null;
    }

}
