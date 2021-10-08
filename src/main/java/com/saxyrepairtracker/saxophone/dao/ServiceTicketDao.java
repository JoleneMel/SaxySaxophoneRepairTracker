package com.saxyrepairtracker.saxophone.dao;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;
import com.saxyrepairtracker.saxophone.entity.ServiceTicket;
import com.saxyrepairtracker.saxophone.entity.Status;

public interface ServiceTicketDao {

  List<ServiceTicket> fetchServiceTicketByStatus(Status status);

  List<ServiceTicket> fetchAllServiceTickets();

 ServiceTicket createServiceTicket(int customerFK, String description, Status status,
      BigDecimal estimatedCost, BigDecimal actualCost);

  void deleteServiceTicket(int deleteId);

  ServiceTicket updateServiceTicket(int servicePK, @Valid ServiceTicket updatedServiceTicket);

}
