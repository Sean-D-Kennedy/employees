package com.company.employees.service.dto;


import com.company.employees.repository.entity.Employee;

public class EmployeeMapper {
    public static EmployeeDTO mapToEmployeeDto(Employee employee, EmployeeDTO employeeDto) {
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setSurname(employee.getSurname());
        employeeDto.setDept(employee.getDept());
        employeeDto.setAge(employee.getAge());
        employeeDto.setStaffId(employee.getStaffId());

        return employeeDto;
    }
}
