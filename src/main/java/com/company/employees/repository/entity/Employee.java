package com.company.employees.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Range;

// JPQL = Jakarta Persistence Query Language

@Entity // jakarta
@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor // lombok
public class Employee {
    @Id // jakarta
    @GeneratedValue(strategy = GenerationType.IDENTITY) // jakarta - use a db id column
    private Long id;

    @NotBlank(message = "firstName must not be blank")
    // @Column required if the member != the db column name i.e. brand_name != brandName
    @Column(name="first_name") // jakarta
    private String firstName;

    @NotBlank(message = "surname must not be blank") private String surname;

    @NotBlank(message = "dept must not be blank") private String dept;

//    @NotNull(message = "age required")   // 0
//    @NotEmpty(message = "age required") // No validator could be found for constraint 'jakarta.validation.constraints.NotEmpty'
//    @NotBlank(message = "age required") // No validator could be found for constraint 'jakarta.validation.constraints.NotBlank' validating type 'java.lang.Integer'. Check configuration for 'age'
    @Range(min = 18, message= "age must >=18, and must not be empty or null")    private int age;

    @NotNull(message = "staffId required")
    @Column(name="staff_id") // jakarta
    private int staffId;
}

