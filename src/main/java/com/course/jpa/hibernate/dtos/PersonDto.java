package com.course.jpa.hibernate.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {

    private long personId;
    private String personAddress;
    private String personEmail;
    private String personGender;
    private String personFirstName;
    private String personPhoneNumber;
    private String personBirthDate; // Now a String for formatted date
    private String createdAt; // Also a String

}
