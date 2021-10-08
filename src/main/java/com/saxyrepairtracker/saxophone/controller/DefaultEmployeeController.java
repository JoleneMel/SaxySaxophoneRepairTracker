package com.saxyrepairtracker.saxophone.controller;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.saxyrepairtracker.saxophone.entity.Employee;
import com.saxyrepairtracker.saxophone.service.EmployeesService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultEmployeeController implements EmployeeController{

 @Autowired 
  private EmployeesService employeesService;
  //this is what swagger sees this is how 

  
  
  @Override
  public List<Employee> fetchEmployees(String firstName, String lastName) {
    log.debug("firstName={}, lastName={}", firstName, lastName);
    return employeesService.fetchEmployees(firstName, lastName);
  }
 
  //Get All Employees 
  @Override
  public List<Employee> fetchAllEmployees() {
    return employeesService.fetchAllEmployees();
  }
  
  //deleteEmployee
    @Override
    public void deleteEmployee(int deleteId) {
    log.debug("employeePK={}", deleteId);
    employeesService.deleteEmployee(deleteId);
    }

    @Override
    public Employee createEmployee(String firstName, String lastName, BigDecimal payRate) {
      return employeesService.createEmployee(firstName, lastName, payRate);
    }
 
    @Override
    public Employee updateEmployee(int employeePK, @Valid Employee updatedEmployee) {
      return employeesService.updateEmployee(employeePK, updatedEmployee);
    }
}