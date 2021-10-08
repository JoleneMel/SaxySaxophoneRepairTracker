package com.saxyrepairtracker.saxophone.entity;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
  private int employeePK;
  private String firstName;
  private String lastName;
  private BigDecimal payRate;
//So Json ignore the PK in it
// @JsonIgnore
public int getEmployeePK() {
  return employeePK;
}
}
