package com.saxyrepairtracker.saxophone.dao;

import java.math.BigDecimal;
import java.util.List;
import com.saxyrepairtracker.saxophone.entity.ServiceLineItem;
import com.saxyrepairtracker.saxophone.entity.RepairType;

public interface ServiceLineItemDao {

  public List<ServiceLineItem> fetchAllServiceLineItems();
  
  public List<ServiceLineItem> fetchAServiceLineItem(int serviceFK);
  
  public ServiceLineItem createServiceLineItem(int serviceFK, int saxophonesFK, int employeeFK, 
      BigDecimal laborHours, RepairType RepairType, BigDecimal partCost, BigDecimal additionalFees,
      BigDecimal totalCost);
  
  public ServiceLineItem updateServiceLineItem(int lineItemPK, ServiceLineItem updatedItem);
  
//  public List<ServiceLineItem> fetchAServiceLineItemByStatus(
//      RepairType RepairType);
}
