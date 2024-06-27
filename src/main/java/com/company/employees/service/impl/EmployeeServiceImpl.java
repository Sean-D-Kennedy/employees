package com.company.employees.service.impl;

import com.company.employees.controller.exception.EmployeeAlreadyExistsException;
import com.company.employees.controller.exception.EmployeeNotFoundException;
import com.company.employees.controller.exception.StaffIdsMismatchException;
import com.company.employees.repository.EmployeeRepository;
import com.company.employees.repository.entity.Employee;
import com.company.employees.service.IEmployeeService;
import com.company.employees.service.dto.EmployeeDTO;
import com.company.employees.service.dto.EmployeeMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service // is a Component
@AllArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {
    private EmployeeRepository employeeRepository; // will be injected

    public EmployeeDTO addEmployee(Employee employee){
        if(employeeRepository.findByStaffId(employee.getStaffId()).isPresent()) {
            // employee reg is already in db, do not add it again
            throw new EmployeeAlreadyExistsException("Employee already exists in db! : "+employee.getStaffId());
        }
        Employee savedEmployee = employeeRepository.save(employee); // no need to code save(Car)!
        EmployeeDTO employeeDto = EmployeeMapper.mapToEmployeeDto(savedEmployee, new EmployeeDTO()); // so the primary key is not returned
        return employeeDto;
    }
    public List<EmployeeDTO> getAllEmployees(){
        List<EmployeeDTO> listOfEmployeeDtos = new ArrayList<>();
        for(Employee employee: employeeRepository.findAll()){
            EmployeeDTO employeeDto = EmployeeMapper.mapToEmployeeDto(employee, new EmployeeDTO()); // so the primary key is not returned
            listOfEmployeeDtos.add(employeeDto);
        }
        return listOfEmployeeDtos;
    }
    public EmployeeDTO getEmployee(int staffId){
        Employee employee = employeeRepository
                .findByStaffId(staffId) // returns Optional<Employee>
                    .orElseThrow(() -> new EmployeeNotFoundException("Employee not found in db! : "+staffId));
        EmployeeDTO employeeDto = EmployeeMapper.mapToEmployeeDto(employee, new EmployeeDTO()); // so the primary key is not returned
        return employeeDto;
    }
    public boolean deleteEmployee(int staffId){
        Employee employee = employeeRepository
                .findByStaffId(staffId) // returns Optional<Employee>
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found in db! : "+staffId));
        employeeRepository.deleteByStaffId(staffId); // derived query
        return true;
    }
    public boolean deleteAllEmployees(){
        employeeRepository.deleteAll(); // already available
        return true;
    }
    public boolean updateEmployee(int staffIdFromURI, Employee employee){
        if(staffIdFromURI != employee.getStaffId()) {
            throw new StaffIdsMismatchException("Staff Ids numbers mismatch!. URI: "+staffIdFromURI+", Entity Body: "+employee.getStaffId());
        }
        // this is an update as the regNo is already in the database
        // Update - save() does insert as we know; it will do update if the primary key is present.
        // However, as I am letting the db generate ID's for the primary keys, this will not work.
        // Thus, we need to annotate the update method with @Transaction, @Query and @Modifying.
        // https://stackoverflow.com/questions/11881479/how-do-i-update-an-entity-using-spring-data-jpa
        employeeRepository.updateEmployee(employee.getFirstName(), employee.getSurname(), employee.getDept(),
                employee.getAge(), employee.getStaffId());
        return true;
    }
    /*
    public List<CarDTO> getAllCarsByBrandName(String brandName){
        List<CarDTO> listOfCarDtos = new ArrayList<>();
        for(Car car: employeeRepository.findAllByBrandName(brandName)){
            CarDTO carDto = CarMapper.mapToCarDTO(car, new CarDTO()); // so the primary key is not returned
            listOfCarDtos.add(carDto);
        }
        return listOfCarDtos;
    }
    public List<CarDTO> getAllCarsByCarType(String carType){
        List<CarDTO> listOfCarDtos = new ArrayList<>();
        for(Car car: employeeRepository.findAllByCarType(carType)){
            CarDTO carDto = CarMapper.mapToCarDTO(car, new CarDTO()); // so the primary key is not returned
            listOfCarDtos.add(carDto);
        }
        return listOfCarDtos;
    }
    public boolean deleteCar(String regNo){
        Car car = employeeRepository
                .findByRegNo(regNo) // returns Optional<Car>
                .orElseThrow(() -> new EmployeeNotFoundException("Car reg number not found in db! : "+regNo));
        employeeRepository.deleteByRegNo(regNo); // derived query
        return true;
    }
    public boolean deleteAllCars(){
        employeeRepository.deleteAll(); // already available
        return true;
    }

     */
}
