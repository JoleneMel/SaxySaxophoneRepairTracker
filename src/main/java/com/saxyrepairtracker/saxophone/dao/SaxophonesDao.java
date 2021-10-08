package com.saxyrepairtracker.saxophone.dao;

import java.util.List;
import com.saxyrepairtracker.saxophone.entity.Saxophones;
import com.saxyrepairtracker.saxophone.entity.SaxophonesType;

public interface SaxophonesDao {
  
  //SaxophonesDao takes everything from the service layer and begins to implement it.
  //Afterwards, passes it off to the default Saxophone Dao.
  
  List<Saxophones> fetchAllSaxophonesByCustomer(int customerFK);                        

  List<Saxophones> fetchAllSaxophones();                                                

  Saxophones createSaxophones(int customerFK, int serialNumber, String manufacturer, String series, 
                                              SaxophonesType type);

  Saxophones updateSaxophones(int saxophonesPK, Saxophones updatedSaxophones);            

  List<Saxophones> fetchSaxophones(SaxophonesType type);                                 
  }
  