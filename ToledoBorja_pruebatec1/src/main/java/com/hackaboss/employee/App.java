package com.hackaboss.employee;

import com.hackaboss.employee.GUI.Menu;
import com.hackaboss.employee.models.Employee;
import com.hackaboss.employee.services.EmployeeService;
import com.hackaboss.employee.persistence.PersistenceController;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Borja Toledo
 */
public class App {

  private static final PersistenceController persisControl = new PersistenceController();
  private static final Scanner sc = new Scanner(System.in);

  public static void listEmployees() {
    List<Employee> employees = persisControl.getAllEmployees();

    if (employees.isEmpty()) {
      System.out.println("\t\tLa base de datos está vacía");
    } else {
      Menu.showDataInTable(employees);
    }
  }

  public static void findEmployeeByPosition() {
    System.out.print("\n\n\t\tPor favor, introduce el cargo a buscar: ");
    String position = EmployeeService.checkStringValueIsEmpty(sc.nextLine());

    List<Employee> employees = persisControl.getEmployeesByPosition(position);

    if (employees.isEmpty()) {
      System.out.println("\t\tNo se han encontrado resultados");
    } else {
      System.out.println("\n\n\t\tEstos son los resultados para el cargo "
        + position + ": \n");
      Menu.showDataInTable(employees);
    }
    pause();
  }

  public static void insertNewEmployee() {
    persisControl.insertEmployee(EmployeeService.createEmployeeObject());
    System.out.println("\t\tEmpleado insertado con éxito.");
    pause();
  }

  public static void editEmployee() {
    listEmployees();
    int emploreeId = EmployeeService.askForId();

    Employee employee = persisControl.getEmployeesById(emploreeId);
    Menu.showEditMenu();

    int opt = sc.nextInt();
    sc.skip("\n");

    System.out.print("\n\t\tIntroduce nuevo ");
    switch (opt) {
      case 1 -> {
        System.out.print("nombre: ");
        employee.setName(EmployeeService.checkStringValueIsEmpty(sc.nextLine()));
      }
      case 2 -> {
        System.out.print("apellido: ");
        employee.setLastName(EmployeeService.checkStringValueIsEmpty(sc.nextLine()));
      }
      case 3 -> {
        System.out.print("cargo: ");
        employee.setPosition(EmployeeService.checkStringValueIsEmpty(sc.nextLine()));
      }
      case 4 -> {
        System.out.print("salario: ");
        try {
          employee.setSalary(EmployeeService.askForSalary());

        } catch (InputMismatchException e) {
          System.out.println("El salario debería ser un número");
        }
      }
      case 5 ->
        employee.setStartDate(EmployeeService.askForStartDate());

      default -> {
        return;
      }
    }
    persisControl.updateEmployee(employee);
    System.out.println("\t\tEmpleado modificado con éxito.");
    pause();
  }

  public static void deleteEmployee() {
    listEmployees();
    int id = -1;

    do {
      System.out.print("\n\n\t\tIntroduce el id del empleado a modificar: ");
      id = sc.nextInt();
      sc.skip("\n");
      System.out.println();
    } while (!EmployeeService.checkIdExists(id));

    persisControl.deleteEmployee(id);
    System.out.println("Empleado eliminado.");
    pause();
  }

  public static void pause() {
    System.out.println("\n\tPresione una tecla para volver al menú principal\n");
    sc.nextLine();
  }

  public static void run() {
    int option;
    do {
      Menu.showMenu();
      option = sc.nextInt();
      sc.skip("\n");
      System.out.println();

      switch (option) {
        case 1 -> {
          listEmployees();
          pause();
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
          System.out.println("\n\n\t\tGracias por utilizar nuestro programa");
      }
    } while (option != 6);
  }
}
