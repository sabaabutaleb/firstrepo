package org.revature.data;
//producing Daos which create instance of Daos and return it
//call this DaoFactory when ever we need it ...
public class DaoFactory {
    //make instance of employeeDao
    private static EmployeeDao employeeDao;
    private static TicketDao ticketDao;

    //private constructor
    private DaoFactory() {
    }
    public static EmployeeDao getEmployeeDao(){
        if (employeeDao==null){
            employeeDao=new EmployeeDaoImpl();
        }
        return employeeDao;
    }
    public static TicketDao getTicketDao(){
        if (ticketDao==null){
            ticketDao=new TicketDaoImpl();
        }
        return  ticketDao;
    }
}
