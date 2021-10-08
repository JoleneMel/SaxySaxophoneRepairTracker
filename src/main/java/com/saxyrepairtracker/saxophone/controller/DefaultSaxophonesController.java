package com.saxyrepairtracker.saxophone.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.saxyrepairtracker.saxophone.entity.Saxophones;
import com.saxyrepairtracker.saxophone.entity.SaxophonesType;
import com.saxyrepairtracker.saxophone.service.SaxophonesService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController

public class DefaultSaxophonesController implements SaxophonesController {

  //Default Saxophones Controller implements everything from the Saxophone Controller interface.
  //From here, it hands it off to to the service layer.

  @Autowired
  private SaxophonesService saxophonesService;

  @Override
  public List<Saxophones> fetchSaxophones(SaxophonesType type) {
    log.debug("'type={}'", type);
    return saxophonesService.fetchSaxophones(type);
  }

  
  
  @Override
  public List<Saxophones> fetchAllSaxophones() {                                               
    return saxophonesService.fetchAllSaxophones();
    }
  
  @Override
  public List<Saxophones> fetchAllSaxophonesByCustomer(int customerFK) {                       
    return saxophonesService.fetchAllSaxophonesByCustomer(customerFK);
  }
  
  @Override
  public Saxophones updateSaxophones(int saxophonesPK, Saxophones updatedSaxophones) {
    // TODO Auto-generated method stub
    return saxophonesService.updateSaxophones(saxophonesPK, updatedSaxophones);
  }

  @Override                                                                                    
  public Saxophones createSaxophones(int customerFK, int serialNumber, String manufacturer, String series,
                                            SaxophonesType type) {
    return saxophonesService.createSaxophones(customerFK, serialNumber, manufacturer, series, type);
    }
}
