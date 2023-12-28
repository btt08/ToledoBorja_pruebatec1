package com.hackaboss.employee;

import com.hackaboss.employee.models.Employee;
import com.hackaboss.employee.persistence.PersistenceController;
import com.hackaboss.employee.persistence.exceptions.NonexistentEntityException;
import java.time.LocalDate;
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

  public static void listEmployees() {
    List<Employee> employees = pc.getAllEmployees();
    for (Employee employee : employees) {
      System.out.println(employee);
    }
  }

  public static void findEmployeeByPosition() {
    System.out.print("\n\n\t\tPor favor, introduce el cargo a buscar: ");
    String position = sc.nextLine();
    List<Employee> employees = pc.getEmployeesByPosition(position);

    System.out.println("\n\n\t\tEstos son los resultados para el cargo " + position + ": \n");
    for (Employee employee : employees) {
      System.out.println(employee);
    }
  }

  public static Employee createEmployeeObject() {
    System.out.println("\t\tIntroduzca los datos del empleado a medida que se pidan:");

    System.out.print("\nNombre: ");
    String name = sc.nextLine();

    System.out.print("\nApellidos: ");
    String lastName = sc.nextLine();

    System.out.print("\nCargo: ");
    String position = sc.nextLine();

    System.out.print("\nSalario: ");
    int salary = sc.nextInt();
    sc.skip("\n");

    // TODO: pedir fecha al crear un nuevo empleado y comprobar el formato
    return new Employee(name, lastName, position, salary, LocalDate.now());

  }

  public static void insertNewEmployee() {
    pc.insertEmployee(createEmployeeObject());
  }

  public static void editEmployee() {
    listEmployees();
    System.out.print("\n\n\t\tIntroduce el id del empleado a modificar: ");
    int id = sc.nextInt();
    sc.skip("\n");
    Employee employee = pc.getEmployeesById(id);
    System.out.println("¿Qué campo deseas modificar?");
    System.out.println("1.Nombre");
    System.out.println("2.Apellidos");
    System.out.println("3.Cargo");
    System.out.println("4.Salario");
    System.out.println("5.Cancelar");
    System.out.print("Introduce opción: ");
//    TODO: poder modificar la fecha 
//    System.out.println("5.Fecha inicio");
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
      default -> {
        return;
      }
    }
    pc.updateEmployee(employee);
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

  public static void deleteEmployee() {
    listEmployees();
    System.out.print("\n\n\t\tIntroduce el id del empleado a modificar: ");
    int id = sc.nextInt();
    sc.skip("\n");
    if (checkIdExists(id)) {
      pc.deleteEmployee(id);
      System.out.println("Empleado eliminado.");
      System.out.println("Presione una tecla para volver al menú principal");
      sc.nextLine();
    } else {
      // TODO: throw NonexistentEntityException
    }

  }

  public static void run() {
    int option;
    do {
      showMenu();
      option = sc.nextInt();
      sc.skip("\n");

      switch (option) {
        case 1 ->
          listEmployees();
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
