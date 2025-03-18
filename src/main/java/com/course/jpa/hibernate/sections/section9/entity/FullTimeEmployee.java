package com.course.jpa.hibernate.sections.section9.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

//@Entity
public class FullTimeEmployee extends Employee{

    public FullTimeEmployee() {

    }

    public FullTimeEmployee(String employeeName, String employeeSalary) {
        super(employeeName);
        this.employeeSalary = employeeSalary;
    }

//    @Column(name = "EMPLOYEE_SALARY")
    private String employeeSalary;

    @Override
    public String toString() {
        return "FullTimeEmployee{" +
                "employeeSalary='" + employeeSalary + '\'' +
                '}';
    }
}
