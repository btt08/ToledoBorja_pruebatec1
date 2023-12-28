package com.hackaboss.employee.persistence;

import com.hackaboss.employee.models.Employee;
import com.hackaboss.employee.persistence.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Borja Toledo
 */
public class PersistenceController {

  EmployeeJpaController empController = new EmployeeJpaController();

  public void insertEmployee(Employee employee) {
    empController.create(employee);
  }

  public List<Employee> getAllEmployees() {
    return empController.findEmployeeEntities();
  }

  public Employee getEmployeesById(int id) {
    return empController.findEmployee(id);
  }

  public List<Employee> getEmployeesByPosition(String position) {
    return empController.findEmployeeEntities(position);
  }

  public void deleteEmployee(int id) {
    try {
      empController.destroy(id);
    } catch (NonexistentEntityException ex) {
      Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }

  public void updateEmployee(Employee employee) {
    try {
      empController.edit(employee);
    } catch (Exception ex) {
      Logger.getLogger(PersistenceController.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
}
