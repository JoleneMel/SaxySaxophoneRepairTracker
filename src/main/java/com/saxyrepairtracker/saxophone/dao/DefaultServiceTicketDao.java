package com.saxyrepairtracker.saxophone.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import com.saxyrepairtracker.saxophone.entity.Customer;
import com.saxyrepairtracker.saxophone.entity.ServiceTicket;
import com.saxyrepairtracker.saxophone.entity.Status;
import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class DefaultServiceTicketDao implements ServiceTicketDao{
  @Autowired 
  private NamedParameterJdbcTemplate jdbcTemplate;
  //private DefaultCustomerDao defaultCustomerDao = new DefaultCustomerDao(); 
  @Override
  public List<ServiceTicket> fetchServiceTicketByStatus(Status status) {
    log.debug("DAO: status={}", status);
    
    // @formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM service_ticket "
        + "WHERE status = :status ";
    // @formatter:on
    
    Map<String, Object> params = new HashMap<>();
    params.put("status", status.toString());
    
    return jdbcTemplate.query(sql, params,
        new RowMapper<>() {
            @Override
            public ServiceTicket mapRow(ResultSet rs, int rowNum) throws SQLException {
           // @formatter:off
            return ServiceTicket.builder()
            .servicePK(rs.getInt("service_pk"))
            .customerFK(rs.getInt("customer_fk"))
            .description(rs.getString("description"))
            .status(Status.valueOf(rs.getString("status")))
            .estimatedCost(rs.getBigDecimal("estimated_cost"))
            .actualCost(rs.getBigDecimal("actual_cost"))
            .build();
//              return ServiceTicket.builder()
//                  .servicePK(rs.getInt("service_pk"))
//                  .customerFK(fetchCustomerByPK(rs.getInt("customer_fk")))
//                  .description(rs.getString("description"))
//                  .status(Status.valueOf(rs.getString("status")))
//                  .estimatedCost(rs.getBigDecimal("estimated_cost"))
//                  .actualCost(rs.getBigDecimal("actual_cost"))
//                  .build();
           // @formatter:on
          }
    });    
  }
  class CustomerResultSetExtractor implements ResultSetExtractor<Customer> {
   // @Override
    public Customer extractData(ResultSet rs) 
        throws SQLException, DataAccessException {
      rs.next();
      // @formatter:off
      return Customer.builder()
          .customerPK(rs.getInt("customer_pk"))
          .firstName(rs.getString("first_name"))
          .lastName(rs.getString("last_name"))
          .phone(rs.getString("phone"))
          .build();
      // @formatter:on
    }
  } 
  
  protected Customer fetchCustomerByPK(int customerPK) {
    //@formatter:off
    String sql = ""
          +"SELECT * "
          + "FROM customers "
          + "WHERE customer_pk = :customer_pk";
    // @formatter:on
 
    Map<String, Object> params = new HashMap<>();
    params.put("customer_pk", customerPK);
  
    return 
        jdbcTemplate.query(sql,params, new CustomerResultSetExtractor());
  }
 
  
//  return ServiceTicket.builder()
//      .servicePK(rs.getInt("service_pk"))
//      .customerFK(rs.getInt("customer_fk"))
//      .description(rs.getString("description"))
//      .status(Status.valueOf(rs.getString("status")))
//      .estimatedCost(rs.getBigDecimal("estimated_cost"))
//      .actualCost(rs.getBigDecimal("actual_cost"))
//      .build();
  @Override
  public List<ServiceTicket> fetchAllServiceTickets() {
    // @formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM service_ticket ";
    // @formatter:on
    
    Map<String, Object> params = new HashMap<>();
    
    
    return jdbcTemplate.query(sql, params, 
        new RowMapper<>() {
          @Override
          public ServiceTicket mapRow(ResultSet rs, int rowNum) throws SQLException {
         // @formatter:off
            return ServiceTicket.builder()
                .servicePK(rs.getInt("service_pk"))
                .customerFK(rs.getInt("customer_fk"))
                .description(rs.getString("description"))
                .status(Status.valueOf(rs.getString("status")))
                .estimatedCost(rs.getBigDecimal("estimated_cost"))
                .actualCost(rs.getBigDecimal("actual_cost"))
                .build();
         // @formatter:on
          }});
  }

  @Override
  public ServiceTicket createServiceTicket(int customerFK, String description, Status status,
      BigDecimal estimatedCost, BigDecimal actualCost) {
    // @formatter:off
    SqlParams sqlparams = new SqlParams();
    KeyHolder keyHolder = new GeneratedKeyHolder();
    sqlparams.sql = ""
        + "INSERT INTO service_ticket "
        + "(customer_fk, description, "
        + "status, estimated_cost, actual_cost) VALUES"
        + "(:customer_fk, :description,"
        + ":status, :estimated_cost, :actual_cost)";
    //sqlparams.source.addValue("service_pk", servicePK);
    sqlparams.source.addValue("customer_fk", customerFK);
    sqlparams.source.addValue("description", description);
    sqlparams.source.addValue("status", status.toString());
    sqlparams.source.addValue("estimated_cost", estimatedCost);
    sqlparams.source.addValue("actual_cost", actualCost);
    // @formatter:on
    
    
    jdbcTemplate.update(sqlparams.sql, sqlparams.source, keyHolder);
    return ServiceTicket.builder()
        .servicePK(keyHolder.getKey().intValue())
        .customerFK(customerFK)
        .description(description)
        .status(status)
        .estimatedCost(estimatedCost)
        .actualCost(actualCost)
        .build();
  }

  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }

  @Override
  public void deleteServiceTicket(int deleteId) {
    // @formatter:off
    String sql = ""
        + "DELETE FROM service_ticket "
        + "WHERE service_pk = :service_pk;";
    // @formatter:on    
       
    Map<String, Object> params = new HashMap<>();
    
    params.put("service_pk", deleteId);    
    if (jdbcTemplate.update(sql,  params) == 0) throw new NoSuchElementException();
  }

  @Override
  public ServiceTicket updateServiceTicket(int servicePK,
      @Valid ServiceTicket updatedServiceTicket) {
    // @formatter:off
    String sql = ""
        + "UPDATE service_ticket "
        + "SET "
        + "customer_fk = :customer_fk, "
        + "description = :description, "
        + "status = :status, "
        + "estimated_cost = :estimated_cost, "
        + "actual_cost = :actual_cost "
        + "WHERE service_pk = :service_pk; ";
    // @formatter:on
    
    Map<String, Object> params = new HashMap<>();
    params.put("customer_fk", updatedServiceTicket.getCustomerFK());
    params.put("description", updatedServiceTicket.getDescription());
    params.put("status", updatedServiceTicket.getStatus().toString());
    params.put("estimated_cost", updatedServiceTicket.getEstimatedCost());
    params.put("actual_cost", updatedServiceTicket.getActualCost());
    params.put("service_pk", servicePK);
    
    if (jdbcTemplate.update(sql, params) == 0) {
      throw new NoSuchElementException("update failed :( ");
     }
       return ServiceTicket.builder()
           .servicePK(servicePK)
           .customerFK(updatedServiceTicket.getCustomerFK())
           .description(updatedServiceTicket.getDescription())
           .status(updatedServiceTicket.getStatus())
           .estimatedCost(updatedServiceTicket.getEstimatedCost())
           .actualCost(updatedServiceTicket.getActualCost())
           .build();
   
 }
}
