package com.course.jpa.hibernate.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
@SQLDelete(sql = "UPDATE Person p set p.IS_DELETED = 'Y' where p.PERSON_ID = ?")
@Where(clause = "IS_DELETED = 'N'")
@Slf4j
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

    @Column(name = "IS_DELETED")
    private String isDeleted;

    @PreRemove
    protected void onRemove() {
//        if(this.isDeleted == null) {
            log.info("Deleting person");
            this.isDeleted =  "Y";
//        }
    }

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = Timestamp.from(Instant.now());
        }
    }
}
