package com.saxyrepairtracker.saxophone.controller;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.saxyrepairtracker.saxophone.entity.ServiceTicket;
import com.saxyrepairtracker.saxophone.entity.Status;
import com.saxyrepairtracker.saxophone.service.ServiceTicketService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultServiceTicketController implements ServiceTicketController{

  @Autowired
  private ServiceTicketService serviceTicketService;
  
  
  @Override
  public List<ServiceTicket> fetchServiceTicketByStatus(Status status) {
    log.debug("status={}", status);
    return serviceTicketService.fetchServiceTicketByStatus(status);
  }

  @Override
  public List<ServiceTicket> fetchAllServiceTickets() {
    return serviceTicketService.fetchAllServiceTickets();
  }

//  @Override
//  public List<ServiceTicket> createServiceTicket(@Valid ServiceTicket newServiceTicket) {
//    return serviceTicketService.createServiceTicket(newServiceTicket);
//  }

  @Override
  public void deleteServiceTicket(int deleteId) {
    log.debug("servicePK={}", deleteId);
    serviceTicketService.deleteServiceTicket(deleteId);
  }

  @Override
  public ServiceTicket updateServiceTicket(int servicePK,
      @Valid ServiceTicket updatedServiceTicket) {
    return serviceTicketService.updateServiceTicket(servicePK, updatedServiceTicket);
  }

  @Override
  public ServiceTicket createServiceTicket(int customerFK, String description, Status status,
      BigDecimal estimatedCost, BigDecimal actualCost) {
    return serviceTicketService.createServiceTicket(customerFK, description, status,
         estimatedCost, actualCost);
  }

}
