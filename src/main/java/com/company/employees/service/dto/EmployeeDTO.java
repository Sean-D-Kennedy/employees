package com.company.employees.service.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

// @Data : Generates getters/setters, a constructor, toString, hashCode and equals.
@Data
public class EmployeeDTO extends RepresentationModel<EmployeeDTO> {
    private String firstName, surname, dept;
    private int age, staffId;
}
