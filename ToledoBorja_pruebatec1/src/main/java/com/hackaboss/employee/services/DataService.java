package com.hackaboss.employee.services;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author Borja Toledo
 */
public class DataService {

  private static final Scanner sc = new Scanner(System.in);

  public static String getStringValue() {
    String value = sc.nextLine();

    while (value.equals("")) {
      System.out.print("\t\t\tEl valor no puede ser vacío, ingresa un valor: ");
      value = sc.nextLine();
    }

    return value;
  }

  public static int getNumericValue() {
    int numericValue = -1;

    // Si el siguiente valor en el Scanner es numerico, se mete en salary
    // En caso contrario, es un valor no numérico y se pide hasta tener un número
    while (numericValue == -1) {
      if (sc.hasNextInt()) {
        numericValue = sc.nextInt();
        sc.skip("\n");
      } else {
        System.out.print("\t\t\tEl valor debe ser numérico: ");
        sc.next(); // Limpia el scanner para poder obtener el siguiente valor
      }
    }
    return numericValue;
  }

  public static LocalDate getDateValue() {
    String startDate = sc.nextLine();

    while (!startDate.equals("")
      && !isValidDate(startDate)) {
      System.out.print("\t\t\tFormato inválido (AAAA-MM-DD) (default "
          + LocalDate.now().toString() + "): ");
      startDate = sc.nextLine();
    }

    if (startDate.equals("")) {
      startDate = LocalDate.now().toString();
    }

    return LocalDate.parse(startDate);
  }

  public static int getIdValue() {
    int id = -1;

    do {      
      System.out.print("\n\n\t\t\tIntroduce el id del empleado: ");
      id = DataService.getNumericValue();
    } while (id != 0 && !EmployeeService.checkIdExists(id));
    
    System.out.println();

    return id;
  }

  public static boolean isValidDate(String dateStr) {
    try {
      LocalDate.parse(dateStr);
    } catch (DateTimeParseException e) {
      return false;
    }
    return true;
  }
  
  public static void pause() {
    System.out.println("\n\t\t\tPresione intro para volver al menú principal\n");
    sc.nextLine();
  }
}
