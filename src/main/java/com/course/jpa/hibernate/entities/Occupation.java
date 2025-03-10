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
@Table(name = "OCCUPATIONS")
@DynamicUpdate
public class Occupation {

    @Id
    @Column(name = "OCCUPATION_ID")
    private long occupationId;

    @Column(name = "OCCUPATION_NAME")
    private String occupationName;

    @Column(name = "CREATED_DATE")
    private Date createdAt;
}
