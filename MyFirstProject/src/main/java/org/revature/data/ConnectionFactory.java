package org.revature.data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactory {
    private static Connection connection=null;
    private  ConnectionFactory(){

    }
    static Connection getConnection(){
        if (connection==null){
            ResourceBundle rb= ResourceBundle.getBundle("DbConfig");
            String url= rb.getString("url");
            String user=rb.getString("user");
            String password= rb.getString("password");
            try{
                Class.forName("org.postgresql.Driver");
                connection= DriverManager.getConnection(url,user,password);
            }catch(SQLException e){
                System.out.println("the error");
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;

    }
}