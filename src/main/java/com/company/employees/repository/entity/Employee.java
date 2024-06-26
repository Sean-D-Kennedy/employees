package com.company.employees.repository.entity;

import jakarta.persistence.*;
import lombok.*;

// JPQL = Jakarta Persistence Query Language

@Entity // jakarta
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor // lombok
public class Employee {
    @Id // jakarta
    @GeneratedValue(strategy = GenerationType.IDENTITY) // jakarta - use a db id column
    private Long id;

    // @Column required if the member != the db column name i.e. brand_name != brandName
    @Column(name="first_name") // jakarta
    private String firstName;

    private String surname;

    private String dept;

    private int age;

    @Column(name="staff_id") // jakarta
    private int staffId;
}

