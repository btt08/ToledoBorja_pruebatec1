package com.hackaboss.employee.services;

import com.hackaboss.employee.models.Employee;
import com.hackaboss.employee.persistence.PersistenceController;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Borja Toledo
 */
public class EmployeeService {

  private static final PersistenceController pc = new PersistenceController();
  private static final Scanner sc = new Scanner(System.in);

  public static String checkStringValueIsEmpty(String value) {
    while (value.equals("")) {
      System.out.println("El valor no puede ser nulo, ingrese un valor: ");
      value = sc.nextLine();
    }
    return value;
  }

  public static int askForSalary() {
    int salary = -1;
    // Si el siguiente valor en el Scanner es numerico, se mete en salary
    // En caso contrario, es un valor no numérico y se pide hasta tener un número
    while (salary == -1) {
      if (sc.hasNextInt()) {
        salary = sc.nextInt();
        sc.skip("\n");
      } else {
        System.out.print("El valor debe ser numérico: ");
        sc.next(); // Limpia el scanner para poder obtener el siguiente valor
      }
    }
    return salary;
  }

  public static Employee createEmployeeObject() {

    System.out.println("\t\tIntroduzca los datos del empleado a medida que se pidan:");

    System.out.print("\n\t\tNombre: ");
    String name = checkStringValueIsEmpty(sc.nextLine());

    System.out.print("\t\tApellidos: ");
    String lastName = checkStringValueIsEmpty(sc.nextLine());

    System.out.print("\t\tCargo: ");
    String position = checkStringValueIsEmpty(sc.nextLine());

    System.out.print("\t\tSalario: ");
    int salary = askForSalary();

    LocalDate startDate = askForStartDate();

    return new Employee(name, lastName, position, salary,
      startDate);
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

  public static boolean isValidDate(String dateStr) {
    try {
      LocalDate.parse(dateStr);
    } catch (DateTimeParseException e) {
      return false;
    }
    return true;
  }

  public static LocalDate askForStartDate() {
    System.out.print("Fecha inicio (AAAA-MM-DD) (dejar en blanco para hoy): ");
    String startDate = sc.nextLine();

    while (!isValidDate(startDate) && !startDate.equals("")) {
      System.out.println("Introduce un formato válido para la fecha (AAAA-MM-DD) "
        + "o dejar en blanco para hoy");
      startDate = sc.nextLine();
    }

    if (startDate.equals("")) {
      startDate = LocalDate.now().toString();
    }

    return LocalDate.parse(startDate);
  }

  public static int askForId() {
    int id = -1;
    
    while (!EmployeeService.checkIdExists(id)) {
      System.out.print("\n\n\t\tIntroduce el id del empleado a modificar: ");
      if (sc.hasNextInt()) {
        id = sc.nextInt();
        sc.skip("\n");
      } else {
        System.out.println("El valor no es numérico.");
        sc.next(); // Limpia el Scanner para poder obtener el siguiente valor
      }
    }
    System.out.println();
    return id;
  }
}
