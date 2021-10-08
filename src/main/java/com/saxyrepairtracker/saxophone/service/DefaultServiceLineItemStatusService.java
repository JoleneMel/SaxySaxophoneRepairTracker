package com.saxyrepairtracker.saxophone.service;

import java.util.List;
import java.util.NoSuchElementException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.saxyrepairtracker.saxophone.dao.ServiceLineItemStatusDao;
import com.saxyrepairtracker.saxophone.entity.ServiceLineItemStatus;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultServiceLineItemStatusService implements ServiceLineItemStatusService {
  
  @Autowired 
  private ServiceLineItemStatusDao serviceLineItemStatusDao;
  
  @Transactional(readOnly = true)
  @Override
  public List<ServiceLineItemStatus> fetchServiceTicketLineStatus(int updatePK, int serviceFK) {
    log.info("The fetchServiceTicketLineStatus method was called with updatePK={} serviceFK={}",
        updatePK, serviceFK);
    
    
    List<ServiceLineItemStatus> serviceLineItemStatuses = serviceLineItemStatusDao.fetchServiceTicketLineStatus(updatePK, serviceFK);
    
    
    if(serviceLineItemStatuses.isEmpty()) {
      String msg = String.format("No Service Line Item Status found with updatePK=%s and serviceFK=%s", updatePK, serviceFK);
          throw new NoSuchElementException(msg);
    }
    
   // Collections.sort((List<Employee>) employees);
    return serviceLineItemStatuses;
  }
  
  
  @Transactional(readOnly = true)
  @Override
  public List<ServiceLineItemStatus> fetchAllServiceTicketLineStatuses() {
    List<ServiceLineItemStatus> serviceLineItemStatuses = serviceLineItemStatusDao.fetchAllServiceTicketLineStatuses();
    
    if(serviceLineItemStatuses.isEmpty()) {
      String msg = String.format("No Service Line Item Status found");
          throw new NoSuchElementException(msg);
    }
    
   // Collections.sort((List<Employee>) employees);
    return serviceLineItemStatuses;
  }

  @Override
  public ServiceLineItemStatus createServiceLineItemStatus(int serviceFK,
      int lineItemFK, String updates) {
    log.info("create ServiceLineItemStatus in service layer");
    return serviceLineItemStatusDao.createServiceLineItemStatus(serviceFK, lineItemFK, updates);
    }

  @Override
  public ServiceLineItemStatus updateServiceLineItemStatus(int updatePK,
      @Valid ServiceLineItemStatus updatedServiceLineItemStatus) {
    log.info("updates ServiceLineItemStatus in service layer");
    return serviceLineItemStatusDao.updateServiceLineItemStatus(updatePK, updatedServiceLineItemStatus);
  }

}
