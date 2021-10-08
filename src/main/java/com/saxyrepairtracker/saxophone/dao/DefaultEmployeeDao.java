package com.saxyrepairtracker.saxophone.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
//import com.saxyrepairtracker.saxophone.dao.DefaultEmployeeCreateRD.SqlParams;
//import com.saxyrepairtracker.saxophone.entity.Customer;
//import org.springframework.stereotype.Service;
import com.saxyrepairtracker.saxophone.entity.Employee;
import lombok.extern.slf4j.Slf4j;
//@Service
@Component
@Slf4j
public class DefaultEmployeeDao implements EmployeeDao{
  @Autowired 
  private NamedParameterJdbcTemplate jdbcTemplate;
  @Override
  public List<Employee> fetchEmployees(String firstName, String lastName) {
    log.debug("DAO: first name={}, last name={}, firstName, lastName");
    
 // @formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM employee "
        + "WHERE first_name = :first_name AND last_name = :last_name";
    // @formatter:on
    
    Map<String, Object> params = new HashMap<>();
    params.put("first_name", firstName);
    params.put("last_name", lastName);
//    params.put("pay_rate", payRate);
    
    return jdbcTemplate.query(sql, params, 
        new RowMapper<>() {
          @Override
          public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
         // @formatter:off
            return Employee.builder()
                .employeePK(rs.getInt("employee_pk"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .payRate(rs.getBigDecimal("pay_rate"))
                .build();
         // @formatter:on
          }});
//Above builder allowed for the payrate to be properly taken 
  }
//new functions implemented in here 
  //May not always need a rowmapper i.e. in delete 
  
  @Override
  public List<Employee> fetchAllEmployees() {
    // @formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM employee ";
    // @formatter:on
    
    Map<String, Object> params = new HashMap<>();
    
    
    return jdbcTemplate.query(sql, params, 
        new RowMapper<>() {
          @Override
          public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
         // @formatter:off
            return Employee.builder()
                .employeePK(rs.getInt("employee_pk"))
                //.employeePK(((Employee) rs.getEmployeePK("employee_pk"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .payRate(rs.getBigDecimal("pay_rate"))
                .build();
         // @formatter:on
          }});
  }
  
  public Employee createEmployee(String firstName, String lastName, BigDecimal payRate) {            //!!!
    SqlParams sqlparams = new SqlParams();
    KeyHolder keyHolder = new GeneratedKeyHolder();
    sqlparams.sql = ""
        + "INSERT into employee "
        + "(first_name, last_name, pay_rate)" 
        + "VALUES (:first_name, :last_name, :pay_rate)" ;
    sqlparams.source.addValue("first_name", firstName);
    sqlparams.source.addValue("last_name", lastName);
    sqlparams.source.addValue("pay_rate", payRate);
    

    jdbcTemplate.update(sqlparams.sql, sqlparams.source, keyHolder);
    return Employee.builder()
        .employeePK(keyHolder.getKey().intValue())
        .firstName(firstName)
        .lastName(lastName)
        .payRate(payRate)
        .build();
  }

  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }
  
  @Override
  public void deleteEmployee(int deleteId) {
    // @formatter:off
    String sql = ""
        + "DELETE FROM employee "
        + "WHERE employee_pk = :employee_pk;";
    // @formatter:on    
       
    Map<String, Object> params = new HashMap<>();
    
    params.put("employee_pk", deleteId);    
    if (jdbcTemplate.update(sql,  params) == 0) throw new NoSuchElementException();
  }

  @Override
  public Employee updateEmployee(int employeePK, Employee updatedEmployee) {
    // @formatter:off
    String sql = ""
        + "UPDATE employee "
        + "SET "
        + "first_name = :first_name, "
        + "last_name = :last_name, "
        + "pay_rate = :pay_rate "
        + "WHERE employee_pk = :employee_pk; ";
    // @formatter:on
    
    Map<String, Object> params = new HashMap<>();
    params.put("first_name", updatedEmployee.getFirstName());
    params.put("last_name", updatedEmployee.getLastName());
    params.put("pay_rate", updatedEmployee.getPayRate());
    params.put("employee_pk", employeePK);
    
    if (jdbcTemplate.update(sql, params) == 0) {
      throw new NoSuchElementException("update failed :( ");
     }
   return Employee.builder()
       .employeePK(employeePK)
       .firstName(updatedEmployee.getFirstName())
       .lastName(updatedEmployee.getLastName())
       .payRate(updatedEmployee.getPayRate())
       .build();
   
 }
}

