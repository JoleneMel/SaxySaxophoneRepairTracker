package com.saxyrepairtracker.saxophone.service;

import java.util.List;
import com.saxyrepairtracker.saxophone.entity.Saxophones;
import com.saxyrepairtracker.saxophone.entity.SaxophonesType;

public interface SaxophonesService {
  
  //The service layer takes what has begun with the controller layer,
  //From here, the Saxophones Service interface passes along to the default saxophones service.
  
  public List<Saxophones> createSaxophones(Saxophones saxophones); 

  public List<Saxophones> fetchAllSaxophones(); 
    
  public Saxophones createSaxophones(int customerFK, int serialNumber, String manufacturer, String series,
                                            SaxophonesType type);

  public Saxophones updateSaxophones(int saxophonePK, Saxophones updatedSaxophones); 

  public List<Saxophones> fetchAllSaxophonesByCustomer(int customerFK);   

  public List<Saxophones> fetchSaxophones(SaxophonesType type);    
}
