package com.saxyrepairtracker.saxophone.dao;

import java.math.BigDecimal;
import java.util.List;
import com.saxyrepairtracker.saxophone.entity.Employee;
//Last version of functions to access database 
//Controller calls Service 
//inturn calls Dao
//interfaces need unimplemented methods that will 
public interface EmployeeDao {
  List<Employee> fetchEmployees(String firstName, String lastName);

  List<Employee> fetchAllEmployees();

  void deleteEmployee(int deleteId);

  Employee createEmployee(String firstName, String lastName, BigDecimal payRate);

 Employee updateEmployee(int employeePK, Employee updatedEmployee);

 
}


