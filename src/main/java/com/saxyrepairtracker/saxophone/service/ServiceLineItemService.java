package com.saxyrepairtracker.saxophone.service;

import java.math.BigDecimal;
import java.util.List;
import com.saxyrepairtracker.saxophone.entity.ServiceLineItem;
import com.saxyrepairtracker.saxophone.entity.RepairType;

public interface ServiceLineItemService {

  List<ServiceLineItem> fetchAllServiceLineItems();
  
  List<ServiceLineItem> fetchAServiceLineItem(int serviceFK);
  
  ServiceLineItem createServiceLineItem(int serviceFK, int saxophonesFK, int employeeFK, 
      BigDecimal laborHours, RepairType RepairType, BigDecimal partCost, BigDecimal additionalFees,
      BigDecimal totalCost);
  
  ServiceLineItem updateServiceLineItem(int lineItemPK, ServiceLineItem updatedItem);
  
//  List<ServiceLineItem> fetchAServiceLineItemByStatus(RepairType RepairType);
}
