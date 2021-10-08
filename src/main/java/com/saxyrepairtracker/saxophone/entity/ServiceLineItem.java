package com.saxyrepairtracker.saxophone.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceLineItem {

  private int lineItemPK; 
  
  private int serviceFK;
  
  private int saxophonesFK; 
  
  private int employeeFK;
   
  private BigDecimal laborHours;
  
  private RepairType repairType;
    
  private BigDecimal partCost; // implement
  
  private BigDecimal additionalFees;  
  
  private BigDecimal totalCost;

}
