package com.saxyrepairtracker.saxophone.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Saxophones {
  
  //begins the process by labeling what the different variables can be within the Saxophones table

  private int saxophonesPK;
  
  private int customerFK;
  
  private int serialNumber;
  
  private String manufacturer;
  
  private String series;
  
  private SaxophonesType saxophonesType;
  

  
}
