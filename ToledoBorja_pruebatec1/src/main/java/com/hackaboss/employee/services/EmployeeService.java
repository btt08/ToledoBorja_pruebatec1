package com.hackaboss.employee.services;

import com.hackaboss.employee.models.Employee;
import com.hackaboss.employee.persistence.PersistenceController;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Borja Toledo
 */
public class EmployeeService {

  private static final PersistenceController pc = new PersistenceController();

  public static Employee createEmployeeObject() {

    System.out.println("\n\t\t\tIntroduzca los datos del empleado a medida que se pidan:");

    System.out.print("\n\t\t\t  Nombre: ");
    String name = DataService.getStringValue();

    System.out.print("\t\t\t  Apellidos: ");
    String lastName = DataService.getStringValue();

    System.out.print("\t\t\t  Cargo: ");
    String position = DataService.getStringValue();

    System.out.print("\t\t\t  Salario: ");
    int salary = DataService.getNumericValue();

    System.out.print("\t\t\t  Fecha inicio (default "
      + LocalDate.now().toString() + "): ");
    LocalDate startDate = DataService.getDateValue();

    return new Employee(name, lastName, position, salary, startDate);
  }

  public static boolean checkIdExists(int id) {
    boolean exists = false;
    List<Employee> employees = pc.getAllEmployees();

    for (Employee employee : employees) {
      if (employee.getId() == id) {
        exists = true;
        break;
      }
    }
    return exists;
  }
}
