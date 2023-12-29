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
  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }
  
  public String getLastName() {
    return lastName;
  }

  public String getPosition() {
    return position;
  }

  public int getSalary() {
    return salary;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setId(int id) {
    this.id = id;
  }

  // SETTERS
  public void setName(String name) {
    this.name = name;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public void setSalary(int salary) {
    this.salary = salary;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  @Override
  public String toString() {
    return "Employee{" + "id=" + id + ", name=" + name + ", lastName=" + lastName + ", position=" + position + ", salary=" + salary + ", startDate=" + startDate + '}';
  }
}
