package com.course.jpa.hibernate.entities;

import jakarta.persistence.*;
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
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HOBBY_ID")
    private long hobbyId;

    @Column(name = "HOBBY_NAME")
    private String hobbyName;

    @Column(name = "CREATED_DATE")
    private Date createdAt;
}
