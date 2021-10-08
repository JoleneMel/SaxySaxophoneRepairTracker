package com.saxyrepairtracker.saxophone.entity;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceTicket {

  private int servicePK;
  private int customerFK;
  private String description;
  private Status status;
  private BigDecimal estimatedCost;
  private BigDecimal actualCost;
  


//  public List<ServiceTicket> getid() {
//    // this will probably get deleted due to the proper id being the servicePk 
//   return null;
   
    
    //Need to utilize the lomboks getters and setters accordingly, I am unknowingly making this slightly
    //more frustrating by ignoring the bonuses of lombok getters and setters
  }
  

