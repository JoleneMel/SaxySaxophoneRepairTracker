package com.saxyrepairtracker.saxophone.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
//import com.saxyrepairtracker.saxophone.dao.DefaultEmployeeDao.SqlParams;
//import com.saxyrepairtracker.saxophone.entity.Employee;
import com.saxyrepairtracker.saxophone.entity.ServiceLineItemStatus;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultServiceLineItemStatusDao implements ServiceLineItemStatusDao{
  @Autowired 
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  @Override
  public List<ServiceLineItemStatus> fetchServiceTicketLineStatus(int updatePK, int serviceFK) {
    log.debug("DAO: updatePK={}, serviceFK={}, updatePK, serviceFK");
    
    // @formatter:off
       String sql = ""
           + "SELECT * "
           + "FROM service_line_item_status "
           + "WHERE update_pk = :update_pk AND service_fk = :service_fk";
       // @formatter:on
       
       Map<String, Object> params = new HashMap<>();
       params.put("update_pk", updatePK);
       params.put("service_fk", serviceFK);
       
       return jdbcTemplate.query(sql, params, 
           new RowMapper<>() {
             @Override
             public ServiceLineItemStatus mapRow(ResultSet rs, int rowNum) throws SQLException {
            // @formatter:off
               return ServiceLineItemStatus.builder()
                   .updatePK(rs.getInt("update_pk"))
                   .serviceFK(rs.getInt("service_fk"))
                   .lineItemFK(rs.getInt("line_item_fk"))
                   .updates(rs.getString("updates"))
                   .updateTime(rs.getTimestamp("update_time"))
                   .build();
            // @formatter:on
             }});
     }

  @Override
  public List<ServiceLineItemStatus> fetchAllServiceTicketLineStatuses() {
    // @formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM service_line_item_status ";
    // @formatter:on
    
    Map<String, Object> params = new HashMap<>();
    return jdbcTemplate.query(sql, params, 
        new RowMapper<>() {
          @Override
          public ServiceLineItemStatus mapRow(ResultSet rs, int rowNum) throws SQLException {
         // @formatter:off
            return ServiceLineItemStatus.builder()
                .updatePK(rs.getInt("update_pk"))
                .serviceFK(rs.getInt("service_fk"))
                .lineItemFK(rs.getInt("line_item_fk"))
                .updates(rs.getString("updates"))
                .updateTime(rs.getTimestamp("update_time"))
                .build();
         // @formatter:on
          }});
  }

  @Override
  public ServiceLineItemStatus createServiceLineItemStatus(int serviceFK,
      int lineItemFK, String updates) {
    SqlParams sqlparams = new SqlParams();
    KeyHolder keyHolder = new GeneratedKeyHolder();
    sqlparams.sql = ""
        + "INSERT into service_line_item_status "
        + "(service_fk, line_item_fk, updates)" 
        + "VALUES (:service_fk, :line_item_fk, :updates)" ;
    sqlparams.source.addValue("service_fk", serviceFK);
    sqlparams.source.addValue("line_item_fk", lineItemFK);
    sqlparams.source.addValue("updates", updates);
    //sqlparams.source.addValue("update_time", updateTime);
    
    

    jdbcTemplate.update(sqlparams.sql, sqlparams.source, keyHolder);
    return ServiceLineItemStatus.builder()
        .updatePK(keyHolder.getKey().intValue())
        .serviceFK(serviceFK)
        .lineItemFK(lineItemFK)
        .updates(updates)
        .build();
 // @formatter:on
  }

  @Override
  public ServiceLineItemStatus updateServiceLineItemStatus(int updatePK,
      @Valid ServiceLineItemStatus updatedServiceLineItemStatus) {
    // @formatter:off
    String sql = ""
        + "UPDATE service_line_item_status "
        + "SET "
        + "service_fk = :service_fk, "
        + "line_item_fk = :line_item_fk, "
        + "updates = :updates "
        + "WHERE update_pk = :update_pk; ";
    // @formatter:on
    Map<String, Object> params = new HashMap<>();
    params.put("service_fk", updatedServiceLineItemStatus.getServiceFK());
    params.put("line_item_fk", updatedServiceLineItemStatus.getLineItemFK());
    params.put("updates", updatedServiceLineItemStatus.getUpdates());
    params.put("update_pk", updatePK);
    
    if (jdbcTemplate.update(sql, params) == 0) {
      throw new NoSuchElementException("update failed :( ");
     }
    return ServiceLineItemStatus.builder()
        .updatePK(updatePK)
        .serviceFK(updatedServiceLineItemStatus.getServiceFK())
        .lineItemFK(updatedServiceLineItemStatus.getLineItemFK())
        .updates(updatedServiceLineItemStatus.getUpdates())
        .build();
  }
}

  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }
  
 

