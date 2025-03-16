package com.course.jpa.hibernate.projections;

import java.sql.Timestamp;
import java.time.LocalDate;

public interface PersonProjections {

    Long getPersonId();
    String getPersonAddress();
    String getPersonEmail();
    String getPersonGender();
    String getPersonFirstName();
    String getPersonPhoneNumber();
    LocalDate getPersonBirthDate();
    Timestamp getCreatedAt();
}
