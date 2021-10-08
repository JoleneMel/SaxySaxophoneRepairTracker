package com.saxyrepairtracker.saxophone.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.saxyrepairtracker.saxophone.entity.ServiceLineItemStatus;
import com.saxyrepairtracker.saxophone.service.ServiceLineItemStatusService;

@RestController 
public class DefaultServiceLineItemStatusController implements ServiceLineItemStatusController{

  @Autowired
  private ServiceLineItemStatusService serviceLineItemStatusService;
  @Override
  public List<ServiceLineItemStatus> fetchServiceTicketLineStatus(int updatePK, int serviceFK) {
    return serviceLineItemStatusService.fetchServiceTicketLineStatus(updatePK, serviceFK);
  }
  @Override
  public List<ServiceLineItemStatus> fetchAllServiceTicketLineStatuses() {
    return serviceLineItemStatusService.fetchAllServiceTicketLineStatuses();
  }

//  @Override
//  public ServiceLineItemStatus createServiceLineItemStatus(int updatePK, int serviceFK,
//      int lineItemFK, String updates) {
//    return serviceLineItemStatusService.createServiceLineItemStatus(updatePK, serviceFK, lineItemFK, updates);
//  }
//  
  @Override
  public ServiceLineItemStatus createServiceLineItemStatus(int serviceFK,
      int lineItemFK, String updates) {
    return serviceLineItemStatusService.createServiceLineItemStatus(serviceFK, lineItemFK, updates);
  }
  
  @Override
  public ServiceLineItemStatus updateServiceLineItemStatus(int updatePK,
      @Valid ServiceLineItemStatus updatedServiceLineItemStatus) {
    return serviceLineItemStatusService.updateServiceLineItemStatus(updatePK, updatedServiceLineItemStatus);
  }

}
