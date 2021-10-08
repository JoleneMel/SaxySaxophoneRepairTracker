package com.saxyrepairtracker.saxophone.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
  
  //begins the process by labeling what the different variables can be within the Customer table

  private int customerPK;
  
  private String firstName;
  
  private String lastName;
  
  private String phone;
}
