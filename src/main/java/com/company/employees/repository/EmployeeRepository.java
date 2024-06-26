package com.company.employees.repository;

import com.company.employees.repository.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

// This is where I inform JPA of my type/table. JPA can look up my Car entity and work from there.
// The implementation of this interface is done by the JPA (in the background)!
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    // The methods defined here are custom methods that the JPA will implement for me, assuming I name
    // them properly. These are in addition to the other methods already available e.g. save().

    Optional<Employee> findByStaffId(int staffId);
    // no method save(Car) defined, framework provides by default

    @Transactional  // jakarta, REQUIRED, this method is done completely or not at all
    void deleteByStaffId(int staffId);

//    List<Car> findAllByBrandName(String brandName); // must have a property "brandName" in Car
//    List<Car> findAllByCarType(String carType); // must have a property "carType" in Car

//    @Transactional  // jakarta, REQUIRED, this method is done completely or not at all
//    void deleteByRegNo(String regNo);

    @Transactional  // jakarta, REQUIRED, this method is done completely or not at all
    @Modifying
    @Query("update Employee e set e.firstName = ?1, e.surname = ?2, e.dept = ?3, e.age = ?4 where e.staffId = ?5")
    void updateEmployee(String firstName, String surname, String dept, Integer age, Integer staffId);

}
