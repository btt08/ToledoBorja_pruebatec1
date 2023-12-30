package com.hackaboss.employee.services;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Contiene diferentes métodos para pedir y gestionar datos
 *
 * @author Borja Toledo
 */
public class DataService {

  private static final Scanner sc = new Scanner(System.in);

  /**
   * Pide al usuario un valor de tipo String, comprueba si es válido y lo
   * devuelve
   *
   * @return value Valor de un string pedido por consola
   */
  public static String getStringValue() {
    String value = sc.nextLine();

    // Comprueba si el valor es vacío o contiene valores que no son letras
    while (value.equals("") || !value.matches("[a-zA-ZÁÉÍÓÚáéíóú]+")) {
      System.out.print("""
                       \t\t\tEl valor no puede ser vac\u00edo ni contener n\u00fameros,\n\t\t\tingresa un nuevo valor: """);
      value = sc.nextLine();
    }

    return value;
  }

  /**
   * Pide por consola un valor numérico, comprueba que el valor introducido sea
   * numérico y lo devuelve
   *
   * @return numericValue Valor numérico pedido por consola
   */
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

  /**
   * Pide una fecha por consola, comprueba que sea válido y lo devuelve
   *
   * @return startDate Valor tipo fecha pedido por consola
   */
  public static LocalDate getDateValue() {
    String startDate = sc.nextLine();

    // Si el valor no es vacío y tiene mal formato lo vuelve a pedir
    while (!startDate.equals("")
      && !isValidDate(startDate)) {
      System.out.print("\t\t\tFormato inválido (AAAA-MM-DD) (default "
        + LocalDate.now().toString() + "): ");
      startDate = sc.nextLine();
    }

    // Si se ingresa un valor vacío se pone el día de hoy por defecto
    if (startDate.equals("")) {
      startDate = LocalDate.now().toString();
    }

    return LocalDate.parse(startDate);
  }

  /**
   * Pide un ID por consola, comprueba que exista en la BBDD y lo devuelve.
   *
   * @return id Id válido contenido en la base de datos
   */
  public static int getIdValue() {
    int id = -1;

    do {
      System.out.print("\n\t\t\tIntroduce el id del empleado: ");
      id = DataService.getNumericValue();
    } while (id != 0 && !EmployeeService.checkIdExists(id));

    System.out.println();

    return id;
  }

  /**
   * Comprueba si una cadena tiene un formato fecha válido
   *
   * @param dateStr Cadena de fecha para comprobar
   * @return true o false
   */
  public static boolean isValidDate(String dateStr) {
    try {
      LocalDate.parse(dateStr);
    } catch (DateTimeParseException e) {
      return false;
    }
    return true;
  }

  /**
   * Genera una pausa en la ejecución. Espera a que el usuario pulse intro y
   * continúa
   */
  public static void pause() {
    System.out.println("\n\t\t\tPresione intro para volver al menú principal\n");
    sc.nextLine();
  }
}
