package com.course.jpa.hibernate.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PERSONS")
@DynamicUpdate
public class Person {

    @Id
    @Column(name = "PERSON_ID")
    private long personId;

    @Column(name = "ADDRESS")
    private String personAddress;

    @Column(name = "EMAIL")
    private String personEmail;

    @Column(name = "GENDER")
    private String personGender;

    @Column(name = "FIRST_NAME")
    private String personFirstName;

    @Column(name = "PHONE_NUMBER")
    private String personPhoneNumber;

    @Column(name = "USERDOB")
    private Date personBirthDate;

    @Column(name = "CREATED_DATE")
    private Date createdAt;
}
