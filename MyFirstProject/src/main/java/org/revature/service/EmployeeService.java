package org.revature.service;

import org.revature.data.DaoFactory;
import org.revature.data.EmployeeDao;
import org.revature.data.EmployeeDaoImpl;
import org.revature.data.TicketDao;
import org.revature.entity.Employee;
//its about creating Daos and creating methods
public class EmployeeService {

    public Employee insertEmployee (Employee emp){//register

        //In the service layer, getting the DAO and calling the insert DAO method"
//
        //declare the emp to give it the implementation
        EmployeeDao employeeDao = DaoFactory.getEmployeeDao();
        // insert the employee and return the return value:
        return employeeDao.insertEmployee(emp);
    }
    public Employee getEmployee( String username){//login
        EmployeeDao employeeDao= DaoFactory.getEmployeeDao();
        return employeeDao.getEmployee(username);
    }


}
