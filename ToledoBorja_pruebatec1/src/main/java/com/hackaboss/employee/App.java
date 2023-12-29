package com.hackaboss.employee;

import com.hackaboss.employee.GUI.GUI;
import com.hackaboss.employee.models.Employee;
import com.hackaboss.employee.services.EmployeeService;
import com.hackaboss.employee.persistence.PersistenceController;
import com.hackaboss.employee.services.DataService;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Borja Toledo
 */
public class App {

  private static final PersistenceController persisControl = new PersistenceController();

  public static void listEmployees() {
    List<Employee> employees = persisControl.getAllEmployees();

    if (employees.isEmpty()) {
      System.out.println("\t\tLa base de datos está vacía");
    } else {
      GUI.showDataInTable(employees);
    }
  }

  public static void findEmployeeByPosition() {
    System.out.print("\n\n\t\t\tPor favor, introduce el cargo a buscar: ");
    String position = DataService.getStringValue();

    List<Employee> employees
      = persisControl.getEmployeesByPosition(position);

    if (employees.isEmpty()) {
      System.out.println("\t\t\tNo se han encontrado resultados para el cargo "
        + position + ".\n");
    } else {
      System.out.println("\n\n\t\t\tEstos son los resultados para el cargo "
        + position + ": \n");
      GUI.showDataInTable(employees);
    }
    DataService.pause();
  }

  public static void insertNewEmployee() {
    persisControl.insertEmployee(EmployeeService.createEmployeeObject());
    System.out.println("\n\t\t\t¡¡¡Empleado insertado con éxito!!!");
    DataService.pause();
  }

  public static void editEmployee() {
    listEmployees();
    int employeeId = DataService.getIdValue();

    Employee employee = persisControl.getEmployeesById(employeeId);

    GUI.showEditMenu();

    int menuOption = DataService.getNumericValue();

    // Usando print para reducir repetición de texto
    System.out.print("\n\t\t\tIntroduce nuevo ");
    switch (menuOption) {
      case 1 -> {
        System.out.print("nombre: ");
        employee.setName(DataService.getStringValue());
      }
      case 2 -> {
        System.out.print("apellido: ");
        employee.setLastName(DataService.getStringValue());
      }
      case 3 -> {
        System.out.print("cargo: ");
        employee.setPosition(DataService.getStringValue());
      }
      case 4 -> {
        System.out.print("salario: ");
        employee.setSalary(DataService.getNumericValue());
      }
      case 5 -> {
        System.out.print("fecha inicio (default "
          + LocalDate.now().toString() + "): ");
        employee.setStartDate(DataService.getDateValue());
      }
      default -> {
        return;
      }
    }

    persisControl.updateEmployee(employee);
    System.out.println("\n\t\t\t¡¡¡Empleado modificado con éxito!!!");

    DataService.pause();
  }

  public static void deleteEmployee() {
    listEmployees();
    int id = DataService.getIdValue();

    if (id != 0) {
      persisControl.deleteEmployee(id);
      System.out.println("\t\t\t¡¡¡Empleado eliminado con éxito!!!");
    } else {
      System.out.println("\t\t\tProceso cancelado.");
    }
    DataService.pause();
  }

  public static void run() {
    int menuOption;
    do {
      GUI.showMenu();
      menuOption = DataService.getNumericValue();

      switch (menuOption) {
        case 1 -> {
          listEmployees();
          DataService.pause();
        }
        case 2 ->
          findEmployeeByPosition();
        case 3 ->
          insertNewEmployee();
        case 4 ->
          editEmployee();
        case 5 ->
          deleteEmployee();
        default ->
          System.out.println("\n\n\t\tGracias por utilizar nuestro software");
      }
    } while (menuOption != 6);
  }
}
