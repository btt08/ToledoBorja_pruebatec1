package com.hackaboss.employee.models;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Modelo de la entidad empleado para la BBDD
 * @author Borja Toledo
 */
@Entity
public class Employee implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "position")
  private String position;

  @Column(name = "salary")
  private int salary;

  @Column(name = "start_date", columnDefinition = "DATE")
  private LocalDate startDate;

  /**
   * Constructor por defecto
   */
  public Employee() {
  }

  /**
   * Constructor con parámetros
   * @param name nombre del empleado
   * @param lastName apellido del empleado
   * @param position cargo del empleado
   * @param salary salario del empleado
   * @param startDate fecha inicio en la empresa
   */
  public Employee(String name, String lastName,
    String position, int salary, LocalDate startDate) {
    this.name = name;
    this.lastName = lastName;
    this.position = position;
    this.salary = salary;
    this.startDate = startDate;
  }

  
  // GETTERS
  /**
   * Getter para id
   * @return id
   */
  public int getId() {
    return id;
  }

  /**
   * Getter para nombre
   * @return name
   */
  public String getName() {
    return name;
  }
  /**
   * Getter para apellido
   * @return lastName
   */
  
  public String getLastName() {
    return lastName;
  }

  /**
   * Getter para position(cargo)
   * @return position
   */
  public String getPosition() {
    return position;
  }

  /**
   * Getter para salario
   * @return salary
   */
  public int getSalary() {
    return salary;
  }

  /**
   * Getter para fecha de inicio
   * @return startDate
   */
  public LocalDate getStartDate() {
    return startDate;
  }

 
  // SETTERS
  /**
   * Setter para id
   * @param id 
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Setter para nombre
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Setter para apellido
   * @param lastName
   */
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
   * Setter para position(cargo)
   * @param position 
   */
  public void setPosition(String position) {
    this.position = position;
  }

  /**
   * Setter para salario
   * @param salary
   */
  public void setSalary(int salary) {
    this.salary = salary;
  }

  /**
   * Setter para fecha de inicio
   * @param startDate 
   */
  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  @Override
  public String toString() {
    return "Employee{" + "id=" + id + ", name=" + name + ", lastName=" + lastName + ", position=" + position + ", salary=" + salary + ", startDate=" + startDate + '}';
  }
}
