package org.revature.data;

import org.revature.entity.Employee;

public interface EmployeeDao {
    public Employee insertEmployee(Employee emp);
    public Employee getEmployee(String username);
}
