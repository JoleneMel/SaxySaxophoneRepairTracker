package com.saxyrepairtracker.saxophone.service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.saxyrepairtracker.saxophone.dao.EmployeeDao;
import com.saxyrepairtracker.saxophone.entity.Employee;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultEmployeeService implements EmployeesService{
    
    @Autowired
    private EmployeeDao employeeDao;

    @Transactional(readOnly = true)
    @Override
    public List<Employee> fetchEmployees(String firstName, String lastName) {
      log.info("The fetchEmployees method was called with firstName={} lastName={}",
          firstName, lastName);
      
      List<Employee> employees = employeeDao.fetchEmployees(firstName, lastName);
      
      
      if(employees.isEmpty()) {
        String msg = String.format("No employees found with firstName=%s and lastName=%s", firstName, lastName);
            throw new NoSuchElementException(msg);
      }
      
     // Collections.sort((List<Employee>) employees);
      return employees;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Employee> fetchAllEmployees() {
        
        
        List<Employee> employees = employeeDao.fetchAllEmployees();
        
        
        if(employees.isEmpty()) {
          String msg = String.format("We have no employees");
              throw new NoSuchElementException(msg);
        }
        
       // Collections.sort((List<Employee>) employees);
        return employees;
      }

    //@Transactional(readOnly = false)
    @Override
    public Employee createEmployee(String firstName, String lastName, BigDecimal payRate) {
log.info("create Employees in service layer");
return employeeDao.createEmployee(firstName, lastName, payRate);
}



    //@Transactional(readOnly = false)
    @Override
    public void deleteEmployee(int deleteId) {
        log.info("The deleteEmployee method was called with employeePK={}",
            deleteId);
        
       employeeDao.deleteEmployee(deleteId);
       //In future business logic is done within the service layer and could be checked here 
       //change method to be public int deleteEmployee and check like above(like in the dao)S
        
      }

    //This could mimic the create method 
   // @Transactional(readOnly = false)
    @Override
    public Employee updateEmployee(int employeePK, Employee updatedEmployee) {
      log.info("updates Employee in service layer");
      return employeeDao.updateEmployee(employeePK, updatedEmployee);
    }

  }



