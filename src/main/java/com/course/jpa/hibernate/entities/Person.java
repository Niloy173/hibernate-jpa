package com.course.jpa.hibernate.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PERSON")
//@NamedQuery(name = "find_all_persons", query = "SELECT * FROM PERSON ")
@DynamicUpdate
public class Person {

    @Id
    @GeneratedValue
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
    private Timestamp createdAt;

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = Timestamp.from(Instant.now());
        }
    }
}
