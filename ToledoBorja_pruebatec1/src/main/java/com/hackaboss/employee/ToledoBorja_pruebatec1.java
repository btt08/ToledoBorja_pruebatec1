package com.hackaboss.employee;

import com.hackaboss.employee.models.Employee;
import com.hackaboss.employee.persistence.PersistenceController;
import java.time.LocalDate;

/**
 *
 * @author Borja Toledo
 */
public class ToledoBorja_pruebatec1 {

    public static void main(String[] args) {
        PersistenceController pc = new PersistenceController();
        
        pc.createEmployee(new Employee("prueba", "numero1", "informatico", 20000, LocalDate.now()));
        
    }
}
