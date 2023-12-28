package com.hackaboss.employee.persistence;

import com.hackaboss.employee.models.Employee;
import com.hackaboss.employee.persistence.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

/**
 *
 * @author Borja Toledo
 */
public class EmployeeJpaController implements Serializable {

  private EntityManagerFactory emf = null;

  public EmployeeJpaController() {
    emf = Persistence.createEntityManagerFactory("jpaPU");
  }

  public EmployeeJpaController(EntityManagerFactory emf) {
    this.emf = emf;
  }

  public EntityManager getEntityManager() {
    return emf.createEntityManager();
  }

  public void create(Employee employee) {
    EntityManager em = null;

    try {
      em = getEntityManager();
      em.getTransaction().begin();
      em.persist(employee);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void edit(Employee employee) throws NonexistentEntityException, Exception {
    EntityManager em = null;

    try {
      em = getEntityManager();
      em.getTransaction().begin();
      employee = em.merge(employee);
      em.getTransaction().commit();
    } catch (Exception ex) {
      String msg = ex.getLocalizedMessage();
      if (msg == null || msg.length() == 0) {
        int id = employee.getId();
        if (findEmployee(id) == null) {
          throw new NonexistentEntityException("The employee with id " + id + " no longer exists.");
        }
      }
      throw ex;
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public void destroy(int id) throws NonexistentEntityException {
    EntityManager em = null;

    try {
      em = getEntityManager();
      em.getTransaction().begin();
      Employee employee;

      try {
        employee = em.getReference(Employee.class,
          id);
        employee.getId();
      } catch (EntityNotFoundException enfe) {
        throw new NonexistentEntityException("The employee with id " + id + " no longer exists.", enfe);
      }
      em.remove(employee);
      em.getTransaction().commit();
    } finally {
      if (em != null) {
        em.close();
      }
    }
  }

  public List<Employee> findEmployeeEntities() {
    EntityManager em = getEntityManager();

    try {
      CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
      cq.select(cq.from(Employee.class));
      Query q = em.createQuery(cq);
      return q.getResultList();
    } finally {
      em.close();
    }
  }

  public List<Employee> findEmployeeEntities(String position) {
    EntityManager em = getEntityManager();

    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery cQuery = cb.createQuery(Employee.class);
    Root<Employee> c = cQuery.from(Employee.class);
    ParameterExpression<String> paramEmpPosition = cb.parameter(String.class);

    cQuery.select(c)
      .where(
        cb.equal(c.get("position"), paramEmpPosition));

    TypedQuery<Employee> query = em.createQuery(cQuery);
    query.setParameter(paramEmpPosition, position);
    return query.getResultList();
  }

  public Employee findEmployee(int id) {
    EntityManager em = getEntityManager();

    try {
      return em.find(Employee.class,
        id);
    } finally {
      em.close();
    }
  }
}
