package com.saxyrepairtracker.saxophone.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.saxyrepairtracker.saxophone.dao.ServiceTicketDao;
import com.saxyrepairtracker.saxophone.entity.ServiceTicket;
import com.saxyrepairtracker.saxophone.entity.Status;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class DefaultServiceTicketService implements ServiceTicketService{

  @Autowired
  private ServiceTicketDao serviceTicketDao;
  
  @Transactional(readOnly = true)
  @Override
  public List<ServiceTicket> fetchServiceTicketByStatus(Status status) {
    
      log.info("The fetchServiceTicketByStatus method was called with 'status={}'", status);
           List<ServiceTicket> serviceTickets = serviceTicketDao.fetchServiceTicketByStatus(status);
           
            if(serviceTickets.isEmpty()) {
              String msg = String.format("We have no Service Tickets with this status");
                  throw new NoSuchElementException(msg);
            }
           // Collections.sort((List<Employee>) employees);
            return serviceTickets;
    }
    
  
  @Transactional(readOnly = true)
  @Override
  public List<ServiceTicket> fetchAllServiceTickets() {
    
    List<ServiceTicket> serviceTickets = serviceTicketDao.fetchAllServiceTickets();
  
  
  if(serviceTickets.isEmpty()) {
    String msg = String.format("We have no service tickets, maybe we are closed");
        throw new NoSuchElementException(msg);
  }
  
 // Collections.sort((List<ServiceTicket>) serviceTickets);
  return serviceTickets;
  }

  @Override
  public ServiceTicket createServiceTicket(int customerFK, String description, Status status,
      BigDecimal estimatedCost, BigDecimal actualCost) {
        log.info("The createServiceTicket method was called with "
            + "customerFK={}, description={}, status={}, "
            + "estimatedCost={} and actualCost={}");
       // Collections.sort((List<Employee>) employees);
        return serviceTicketDao.createServiceTicket(customerFK, description, status,
            estimatedCost, actualCost);
      }
  @Override
  public void deleteServiceTicket(int deleteId) {
      log.info("The deleteServiceTicket method was called with servicePK={}",
          deleteId);
      
      serviceTicketDao.deleteServiceTicket(deleteId);
     //In future business logic is done within the service layer and could be checked here 
     //change method to be public int deleteEmployee and check like above(like in the dao)S
      
    }
    
  

  @Override
  public ServiceTicket updateServiceTicket(int servicePK,
      @Valid ServiceTicket updatedServiceTicket) {
      return serviceTicketDao.updateServiceTicket(servicePK, updatedServiceTicket);
    }

  }

