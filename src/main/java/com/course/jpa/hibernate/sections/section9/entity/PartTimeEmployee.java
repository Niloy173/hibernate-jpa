package com.course.jpa.hibernate.sections.section9.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

//@Entity
public class PartTimeEmployee extends Employee {




//    @Column(name = "EMPLOYEE_HOURLY_WAGE")
    private String employeeHourlyWage;

    public PartTimeEmployee() {

    }

    public PartTimeEmployee(String employeeName, String employeeHourlyWage) {
        super(employeeName);
        this.employeeHourlyWage = employeeHourlyWage;
    }

    @Override
    public String toString() {
        return "PartTimeEmployee{" +
                "employeeHourlyWage='" + employeeHourlyWage + '\'' +
                '}';
    }
}
