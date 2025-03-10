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
@Table(name = "HOBBIES")
@DynamicUpdate
public class Hobby {

    @Id
    @Column(name = "HOBBY_ID")
    private long hobbyId;

    @Column(name = "HOBBY_NAME")
    private String hobbyName;

    @Column(name = "CREATED_DATE")
    private Date createdAt;
}
