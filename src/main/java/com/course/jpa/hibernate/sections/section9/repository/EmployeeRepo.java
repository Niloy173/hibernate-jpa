package com.course.jpa.hibernate.sections.section9.repository;

import com.course.jpa.hibernate.sections.section9.entity.Employee;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeRepo {

    @Autowired
    EntityManager em;

    @Transactional
    public void insert(Employee employee) {
        em.persist(employee);
    }

    public List<Employee> getAllEmployee() {
        return em.createQuery("SELECT e FROM Employee e",Employee.class).getResultList();
    }

}
