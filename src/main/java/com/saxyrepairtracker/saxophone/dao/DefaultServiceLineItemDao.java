package com.saxyrepairtracker.saxophone.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import com.saxyrepairtracker.saxophone.entity.ServiceLineItem;
import com.saxyrepairtracker.saxophone.entity.RepairType;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultServiceLineItemDao implements ServiceLineItemDao {
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  
  @Override
  public List<ServiceLineItem> fetchAllServiceLineItems() {
    log.info("In serviceLineItemDao.fetchAllServiceLineItem()");
    
    // @formatter:off
    String sql = ""
        +"SELECT * "
        + "FROM service_line_item;";
    // @formatter:on  
   
    Map<String, Object> params = new HashMap<>();
    return jdbcTemplate.query(sql, params,
        new RowMapper<ServiceLineItem>() {
          @Override
          public ServiceLineItem mapRow(ResultSet rs, int rowNum) throws SQLException {
            // @formatter:off
            return ServiceLineItem.builder()
                .lineItemPK(rs.getInt("line_item_pk"))
                .serviceFK(rs.getInt("service_fk"))
                .saxophonesFK(rs.getInt("saxophones_fk"))
                .employeeFK(rs.getInt("employee_fk"))
                .laborHours(rs.getBigDecimal("labor_hours"))
                .repairType(RepairType.valueOf(rs.getString("repair_type")))
                .partCost(rs.getBigDecimal("part_cost"))
                .additionalFees(rs.getBigDecimal("additional_fees"))
                .totalCost(rs.getBigDecimal("total_cost"))
                .build();
            // @formatter:on
          }});
  }

  @Override
  public List<ServiceLineItem> fetchAServiceLineItem(int serviceFK) {
    // @formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM service_line_item "
        + "WHERE service_fk = :service_fk ";
    // @formatter:on
    
    Map<String, Object> params = new HashMap<>();
    params.put("service_fk", serviceFK);
      
    return jdbcTemplate.query(sql, params,
        new RowMapper<>() {
          @Override
          public ServiceLineItem mapRow(ResultSet rs, int rowNum) throws SQLException {
          // @formatter:off
            return ServiceLineItem.builder()
                .lineItemPK(rs.getInt("line_item_pk"))
                .serviceFK(rs.getInt("service_fk"))
                .saxophonesFK(rs.getInt("saxophones_fk"))
                .employeeFK(rs.getInt("employee_fk"))
                .laborHours(rs.getBigDecimal("labor_hours"))
                .repairType(RepairType.valueOf(rs.getString("repair_type")))
                .partCost(rs.getBigDecimal("part_cost"))
                .additionalFees(rs.getBigDecimal("additional_fees"))
                .totalCost(rs.getBigDecimal("total_cost"))
                .build();
            // @formatter:on
          }
      });
          
  }

  @Override
  public ServiceLineItem createServiceLineItem(int serviceFK, int saxophonesFK, int employeeFK,
      BigDecimal laborHours, RepairType RepairType, BigDecimal partCost, BigDecimal additionalFees,
      BigDecimal totalCost) {
    
    SqlParams sqlparams = new SqlParams();
    KeyHolder keyHolder = new GeneratedKeyHolder();
    sqlparams.sql = ""
        + "INSERT into service_line_item "
        + "(service_fk, saxophones_fk, employee_fk, labor_hours, repair_type, "
        +                                "part_cost, additional_fees, total_cost) " 
        + "VALUES (:service_fk, :saxophones_fk, :employee_fk, :labor_hours, :repair_type, "
        +                                ":part_cost, :additional_fees, :total_cost)" ;
    sqlparams.source.addValue("service_fk", serviceFK);
    sqlparams.source.addValue("saxophones_fk", saxophonesFK);
    sqlparams.source.addValue("employee_fk", employeeFK);
    sqlparams.source.addValue("labor_hours", laborHours);
    sqlparams.source.addValue("repair_type", RepairType.toString());
    sqlparams.source.addValue("part_cost", partCost);
    sqlparams.source.addValue("additional_fees", additionalFees);
    sqlparams.source.addValue("total_cost", totalCost);

    

    jdbcTemplate.update(sqlparams.sql, sqlparams.source, keyHolder);
    return ServiceLineItem.builder()
        .lineItemPK(keyHolder.getKey().intValue())
        .serviceFK(serviceFK)
        .saxophonesFK(saxophonesFK)
        .employeeFK(employeeFK)
        .laborHours(laborHours)
        .repairType(RepairType)
        .partCost(partCost)
        .additionalFees(additionalFees)
        .totalCost(totalCost)
        .build();
  }

  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }


  @Override
  public ServiceLineItem updateServiceLineItem(int lineItemPK, ServiceLineItem updatedItem) {
    // @formatter:off
    String sql = ""
        + "UPDATE service_line_item "
        + "SET "
        + "service_fk = :service_fk, "
        + "saxophones_fk = :saxophones_fk, "
        + "employee_fk = :employee_fk, "
        + "labor_hours = :labor_hours, "
        + "repair_type = :repair_type, "
        + "part_cost = :part_cost, "
        + "additional_fees = :additional_fees, "
        + "total_cost = :total_cost "
        + "WHERE line_item_pk = :line_item_pk;";
    // @formatter:on
        
        Map<String, Object> params = new HashMap<>();
        params.put("service_fk", updatedItem.getServiceFK());
        params.put("saxophones_fk", updatedItem.getSaxophonesFK());
        params.put("employee_fk", updatedItem.getEmployeeFK());
        params.put("labor_hours", updatedItem.getLaborHours());
        params.put("repair_type", updatedItem.getRepairType().toString());
        params.put("part_cost", updatedItem.getPartCost());
        params.put("additional_fees", updatedItem.getAdditionalFees());
        params.put("total_cost", updatedItem.getTotalCost());
        params.put("line_item_pk", lineItemPK);    
    
        
    if (jdbcTemplate.update(sql, params) == 0) {
    throw new NoSuchElementException("update failed :( ");
   }
    return ServiceLineItem.builder()
        .lineItemPK(lineItemPK)
        .serviceFK(updatedItem.getServiceFK())
        .saxophonesFK(updatedItem.getSaxophonesFK())
        .employeeFK(updatedItem.getEmployeeFK())
        .laborHours(updatedItem.getLaborHours())
        .repairType(updatedItem.getRepairType())
        .partCost(updatedItem.getPartCost())
        .additionalFees(updatedItem.getAdditionalFees())
        .totalCost(updatedItem.getTotalCost())
        .build();
  }

//  @Override
//  public List<ServiceLineItem> fetchAServiceLineItemByStatus(
//      RepairType RepairType) {
//    // @formatter:off
//    String sql = ""
//        + "FROM service_line_item "
//        + "WHERE repair_type = :repair_type ";
//    // @formatter:on
//
//    Map<String, Object> params = new HashMap<>();
//    params.put("service_repair_item_status", RepairType.toString());
//    
//    return jdbcTemplate.query(sql,  params, 
//        new RowMapper<>() {
//        @Override
//        public ServiceLineItem mapRow(ResultSet rs, int rowNum) throws SQLException {
//          // @formatter:off
//          return ServiceLineItem.builder()
//              .lineItemPK(rs.getInt("line_item_pk"))
//              .serviceFK(rs.getInt("service_fk"))
//              .saxophonesFK(rs.getInt("saxophones_fk"))
//              .employeeFK(rs.getInt("employee_fk"))
//              .laborHours(rs.getBigDecimal("labor_hours"))
//              .repairType(RepairType.valueOf(rs.getString("repair_type")))
//              .partCost(rs.getBigDecimal("part_cost"))
//              .additionalFees(rs.getBigDecimal("additional_fees"))
//              .totalCost(rs.getBigDecimal("total_cost)"))
//              .build();
//          // @formatter:on
//        }
//    
//      });
//    }
}
