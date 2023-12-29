package com.hackaboss.employee.GUI;

import com.hackaboss.employee.models.Employee;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Borja Toledo
 */
public class GUI {

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
    System.out.println("\t\t\t¿Qué campo deseas modificar?");
    System.out.println("\t\t\t  1.Nombre");
    System.out.println("\t\t\t  2.Apellidos");
    System.out.println("\t\t\t  3.Cargo");
    System.out.println("\t\t\t  4.Salario");
    System.out.println("\t\t\t  5.Fecha inicio");
    System.out.println("\t\t\t  6.Cancelar");
    System.out.print("\t\t\tIntroduce opción: ");
  }

  public static void showDataInTable(List<Employee> employees) {
    String leftAlignFormat = "\t|  %-4d|   %-12s|   %-17s|  %-14s|   %-8d|    %-13s|";

    System.out.format("\t+------");
    System.out.format("+---------------");
    System.out.format("+--------------------");
    System.out.format("+----------------");
    System.out.format("+-----------");
    System.out.format("+-----------------+%n");
    System.out.format("\t|  ID  ");
    System.out.format("|     Nombre    ");
    System.out.format("|      Apellidos     ");
    System.out.format("|     Cargo      ");
    System.out.format("|  Salario  ");
    System.out.format("|  Fecha inicio   |%n");

    System.out.format("\t+------");
    System.out.format("+---------------");
    System.out.format("+--------------------");
    System.out.format("+----------------");
    System.out.format("+-----------");
    System.out.format("+-----------------+%n");
    for (Employee employee : employees) {
      System.out.format(leftAlignFormat,
        employee.getId(),
        employee.getName(),
        employee.getLastName(),
        employee.getPosition(),
        employee.getSalary(),
        employee.getStartDate().toString()
      );
      System.out.println();
    }
    System.out.format("\t+------");
    System.out.format("+---------------");
    System.out.format("+--------------------");
    System.out.format("+----------------");
    System.out.format("+-----------");
    System.out.format("+-----------------+%n");
  }
}
