package com.company.employees.controller;

import com.company.employees.repository.entity.Employee;
import com.company.employees.service.IEmployeeService;
import com.company.employees.service.dto.EmployeeDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController // is a Component
//@RequestMapping(path="/employees", produces = MediaType.APPLICATION_JSON_VALUE) // works
@RequestMapping(path="/employees") // works
@AllArgsConstructor // lombok
public class EmployeeController {
    private IEmployeeService iEmployeeService; // injected due to @RestController (which maps to @Component), @AllArgsConstructor

    //       /cars
    @GetMapping
    public List<EmployeeDTO> getAllCars(){return iEmployeeService.getAllEmployees();}


    @PostMapping
    public ResponseEntity<EmployeeDTO> addCar(@RequestBody Employee employee, UriComponentsBuilder uriComponentsBuilder) {
        System.out.println("XXX employee is "+employee);
        EmployeeDTO employeeDto = iEmployeeService.addEmployee(employee);

        URI locationURI = uriComponentsBuilder
                .path("/employees/"+employeeDto.getStaffId())
                .buildAndExpand(uriComponentsBuilder.toUriString())
                .toUri();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(locationURI)
                .body(employeeDto);
    }
    //       /{staffId}
    @GetMapping("/{staffId}")
    public ResponseEntity<EmployeeDTO> getEmployeeDetails(@PathVariable int staffId){
        EmployeeDTO employeeDto = iEmployeeService.getEmployee(staffId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeDto);
    }
    @DeleteMapping("/{staffId}")
    public ResponseEntity<String> deleteEmployeeDetails(@PathVariable int staffId){
        iEmployeeService.deleteEmployee(staffId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT) // success, no message body
                .build();
    }

    /*
    @DeleteMapping
    public ResponseEntity<String> deleteAllCars(){
        iCarService.deleteAllCars();
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT) // success, no message body
                .build();
    }
    @PutMapping
    public ResponseEntity<String> putNotSupported(){
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .build();
    }


    @GetMapping(path = "/car", params="brandName")
    public List<CarDTO> getAllCarsByBrand(@RequestParam String brandName){ // must be ?brandName=XX in Postman
        return iCarService.getAllCarsByBrandName(brandName);
    }

    @GetMapping(path = "/car", params="carType")
    public List<CarDTO> getAllCarsByCarType(@RequestParam String carType){ // must be ?carType=XX in Postman
        return iCarService.getAllCarsByCarType(carType);
    }

    @DeleteMapping("/{carRegNo}")
    public ResponseEntity<String> deleteCarDetails(@PathVariable String carRegNo){
        iCarService.deleteCar(carRegNo);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT) // success, no message body
                .build();
    }
    @PostMapping("/{carRegNo}")
    public ResponseEntity<String> postNotSupported(){
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .build();
    }

    @PutMapping("/{carRegNo}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable String carRegNo, @RequestBody Car car){
        iCarService.updateCar(carRegNo, car);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT) // no message body
                .build();
    }
//    @OptionsMapping  - not available
//    @HeadMapping     - not available
    @RequestMapping(method = RequestMethod.OPTIONS)
    public ResponseEntity<String> optionsCollectionOfCars() {  //      /cars
        return ResponseEntity
                .status(HttpStatus.OK) // 200 OK
                .allow(HttpMethod.HEAD, HttpMethod.GET, HttpMethod.POST, HttpMethod.DELETE) // varargs list
                .build();

    }
   @RequestMapping(value = "/{carRegNo}", method = RequestMethod.OPTIONS)
    public ResponseEntity<String> optionsIndividualCar() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .allow(HttpMethod.HEAD, HttpMethod.GET, HttpMethod.PUT, HttpMethod.DELETE)
                .build();

    }
    @RequestMapping(value = "/car", method = RequestMethod.OPTIONS)
    public ResponseEntity<String> optionsIndividualRequestParams() {
        return ResponseEntity
                .status(HttpStatus.OK) // success
                .allow(HttpMethod.HEAD, HttpMethod.GET)
                .build();

    }

 */
}
