package com.saxyrepairtracker.saxophone.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data 
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceLineItemStatus {
  private int updatePK;
  
  private int serviceFK;
  
  private int lineItemFK;
  
  private String updates;
  
  //for time of update for ticket 
  private Timestamp updateTime;

}
