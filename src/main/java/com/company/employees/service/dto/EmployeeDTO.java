package com.company.employees.service.dto;

import jakarta.persistence.Column;
import lombok.Data;

// @Data : Generates getters/setters, a constructor, toString, hashCode and equals.
@Data
public class EmployeeDTO {
    private String firstName, surname, dept;
    private int age, staffId;
}
