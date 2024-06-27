package com.company.employees.service;


import com.company.employees.repository.entity.Employee;
import com.company.employees.service.dto.EmployeeDTO;

import java.util.List;

public interface IEmployeeService {
    // POST
    EmployeeDTO addEmployee(Employee employee);

    // GET
    public List<EmployeeDTO> getAllEmployees();
    public EmployeeDTO getEmployee(int staffId);
//    public List<EmployeeDTO> getAllCarsByBrandName(String brandName);
//    public List<EmployeeDTO> getAllCarsByCarType(String carType);

    // DELETE
    boolean deleteEmployee(int staffId);
//    boolean deleteAllEmployee();

    // PUT
//    public boolean updateEmployee(String staffId, Employee employee);
}
