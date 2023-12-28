package com.hackaboss.employee;

import com.hackaboss.employee.models.Employee;
import com.hackaboss.employee.persistence.PersistenceController;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Anonymus
 */
public class App {

  private static final PersistenceController pc = new PersistenceController();
  private static final Scanner sc = new Scanner(System.in);

  public static void showMenu() {
    System.out.println(
      """
          \t\t___________________________________________________________________
          \t\t\t    ____  _                            _     __           
          \t\t\t   / __ )(_)__  ____ _   _____  ____  (_)___/ /___        
          \t\t\t  / __  / / _ \\/ __ \\ | / / _ \\/ __ \\/ / __  / __ \\  
          \t\t\t / /_/ / /  __/ / / / |/ /  __/ / / / / /_/ / /_/ /       
          \t\t\t/_____/_/\\___/_/ /_/|___/\\___/_/ /_/_/\\__,_/\\____/    
          \t\t___________________________________________________________________
        """);
    System.out.println("\n\t\t\tEsta es la aplicación de administración de empleados");
    System.out.println("\t\t\t  1.Listar todos los empleados");
    System.out.println("\t\t\t  2.Buscar empleados por puesto");
    System.out.println("\t\t\t  3.Añadir un nuevo empleado");
    System.out.println("\t\t\t  4.Editar un empleado existente");
    System.out.println("\t\t\t  5.Eliminar un empleado");
    System.out.println("\t\t\t  6.Salir");
    System.out.print("\n\t\t\tIntroduce la opción deseada: ");
  }

  public static void showEditMenu() {
    System.out.println("¿Qué campo deseas modificar?");
    System.out.println("1.Nombre");
    System.out.println("2.Apellidos");
    System.out.println("3.Cargo");
    System.out.println("4.Salario");
    System.out.println("5.Fecha inicio");
    System.out.println("6.Cancelar");
    System.out.print("Introduce opción: ");
  }

  public static void listEmployees() {
    List<Employee> employees = pc.getAllEmployees();
    if (employees.isEmpty()) {
      System.out.println("La base de datos está vacía");
    } else {
      for (Employee employee : employees) {
        System.out.println(employee);
      }
    }
  }

  public static void findEmployeeByPosition() {
    System.out.print("\n\n\t\tPor favor, introduce el cargo a buscar: ");
    String position = sc.nextLine();
    List<Employee> employees = pc.getEmployeesByPosition(position);

    if (employees.isEmpty()) {
      System.out.println("No se han encontrado resultados");
    } else {
      System.out.println("\n\n\t\tEstos son los resultados para el cargo " + position + ": \n");
      for (Employee employee : employees) {
        System.out.println(employee);
      }
    }
    pause();
  }

  public static Employee createEmployeeObject() {
    System.out.println("\t\tIntroduzca los datos del empleado a medida que se pidan:");

    System.out.print("\nNombre: ");
    String name = sc.nextLine();

    System.out.print("Apellidos: ");
    String lastName = sc.nextLine();

    System.out.print("Cargo: ");
    String position = sc.nextLine();

    System.out.print("Salario: ");
    int salary = sc.nextInt();
    sc.skip("\n");

    LocalDate startDate = askForStartDate();

    return new Employee(name, lastName, position, salary,
      startDate);
  }

  public static void insertNewEmployee() {
    pc.insertEmployee(createEmployeeObject());
    System.out.println("Empleado insertado con éxito.");
    pause();
  }

  public static void editEmployee() {
    int id = 0;
    listEmployees();
    while (!checkIdExists(id)) {
      System.out.print("\n\n\t\tIntroduce el id del empleado a modificar: ");
      id = sc.nextInt();
      sc.skip("\n");
    }

    Employee employee = pc.getEmployeesById(id);
    showEditMenu();

    int opt = sc.nextInt();
    sc.skip("\n");
    System.out.print("\nIntroduce nuevo ");
    switch (opt) {
      case 1 -> {
        System.out.print("nombre: ");
        employee.setName(sc.nextLine());
      }
      case 2 -> {
        System.out.print("apellido: ");
        employee.setLastName(sc.nextLine());
      }
      case 3 -> {
        System.out.print("cargo: ");
        employee.setPosition(sc.nextLine());
      }
      case 4 -> {
        System.out.print("salario: ");
        employee.setSalary(sc.nextInt());
      }
      case 5 ->
        employee.setStartDate(askForStartDate());

      default -> {
        return;
      }
    }
    pc.updateEmployee(employee);
    System.out.println("Empleado modificado con éxito.");
    pause();
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

  public static void deleteEmployee() {
    listEmployees();
    System.out.print("\n\n\t\tIntroduce el id del empleado a modificar: ");
    int id = sc.nextInt();
    sc.skip("\n");
    System.out.println();

    if (checkIdExists(id)) {
      pc.deleteEmployee(id);
      System.out.println("Empleado eliminado.");
      pause();
    } else {
      // TODO: throw NonexistentEntityException
    }

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

  public static void pause() {
    System.out.println("\nPresione una tecla para volver al menú principal\n");
    sc.nextLine();
  }

  public static void run() {
    int option;
    do {
      showMenu();
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
