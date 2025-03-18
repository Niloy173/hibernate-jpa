package com.course.jpa.hibernate.sections.section9.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

//@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
public class Employee{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "EMPLOYEE_ID")
    private Long employeeId;

//    @Column(name = "EMPLOYEE_NAME")
    private String employeeName;

    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                '}';
    }

    public Employee(String employeeName) {
        this.employeeName = employeeName;
    }

}
