package com.hackaboss.employee.persistence;

import com.hackaboss.employee.models.Employee;

/**
 *
 * @author Borja Toledo
 */
public class PersistenceController {
    EmployeeJpaController empController = new EmployeeJpaController();
    
    public void createEmployee(Employee employee){
        empController.create(employee);
    }
}
