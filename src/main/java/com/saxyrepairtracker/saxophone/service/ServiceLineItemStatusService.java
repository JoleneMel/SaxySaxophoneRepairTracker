package com.saxyrepairtracker.saxophone.service;

import java.util.List;
import javax.validation.Valid;
import com.saxyrepairtracker.saxophone.entity.ServiceLineItemStatus;

public interface ServiceLineItemStatusService {

  List<ServiceLineItemStatus> fetchServiceTicketLineStatus(int updatePK, int serviceFK);

  List<ServiceLineItemStatus> fetchAllServiceTicketLineStatuses();

//  ServiceLineItemStatus createServiceLineItemStatus(int updatePK, int serviceFK, int lineItemFK,
//      String updates);

  ServiceLineItemStatus updateServiceLineItemStatus(int updatePK,
      @Valid ServiceLineItemStatus updatedServiceLineItemStatus);

  ServiceLineItemStatus createServiceLineItemStatus(int serviceFK, int lineItemFK, String updates);

}
