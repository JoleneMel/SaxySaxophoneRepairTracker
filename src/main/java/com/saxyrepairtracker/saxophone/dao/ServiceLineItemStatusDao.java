package com.saxyrepairtracker.saxophone.dao;

import java.util.List;
import javax.validation.Valid;
import com.saxyrepairtracker.saxophone.entity.ServiceLineItemStatus;

public interface ServiceLineItemStatusDao {

  List<ServiceLineItemStatus> fetchServiceTicketLineStatus(int updatePK, int serviceFK);

  List<ServiceLineItemStatus> fetchAllServiceTicketLineStatuses();

//  ServiceLineItemStatus createServiceLineItemStatus(int updatePK, int serviceFK, int lineItemFK,
//      String updates);
  
  ServiceLineItemStatus createServiceLineItemStatus(int serviceFK, int lineItemFK,
      String updates);

  ServiceLineItemStatus updateServiceLineItemStatus(int updatePK,
      @Valid ServiceLineItemStatus updatedServiceLineItemStatus);

}
