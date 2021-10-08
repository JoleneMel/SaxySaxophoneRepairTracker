package com.saxyrepairtracker.saxophone.dao;

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
import com.saxyrepairtracker.saxophone.entity.Saxophones;
import com.saxyrepairtracker.saxophone.entity.SaxophonesType;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class DefaultSaxophonesDao implements SaxophonesDao {
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
      //Retrieve data from database and returns to service layer
  //Implements the SaxophonesDao interface, and begins to finish all of the commands up before it
  //sends them to swagger potentially when called upon. 
    
@Override 
  public List<Saxophones> fetchAllSaxophones() {                                              
    log.info("In saxophones.dao.fetchAllSaxophones");
    
        // @formatter:off
        String sql = ""
            + "SELECT * "
            + "FROM saxophones ";
        // @formatter:on
        
        Map<String, Object> params = new HashMap<>();
        return jdbcTemplate.query(sql, params,
            new RowMapper<Saxophones>() {
              @Override
              public Saxophones mapRow(ResultSet rs, int rowNum) throws SQLException {
                // @formatter:off
                return Saxophones.builder()
                    .saxophonesPK(rs.getInt("saxophones_pk"))
                    .customerFK(rs.getInt("customer_fk"))
                    .serialNumber(rs.getInt("serial_number"))
                    .manufacturer(rs.getString("manufacturer"))
                    .series(rs.getString("series"))
                    .saxophonesType(SaxophonesType.valueOf(rs.getString("type")))
                    .build();
                // @formatter:on
              }
        });
  }


  @Override
  public Saxophones createSaxophones(int customerFK, int serialNumber, 
                      String manufacturer, String series, SaxophonesType type) {

    
// -----------------------------------DON'T DELETE-------------------------------------------------
    
    
    // the first way to get everything without the saxophonePK
//    Map<String, Object> params = new HashMap<>();
//    params.put("customer_fk", customerFK);
//    params.put("serial_number", serialNumber);
//    params.put("manufacturer", manufacturer);
//    params.put("series", series);
//    params.put("type", type);
//  
//    //for if you don't want to return the saxophonePK
//    jdbcTemplate.update(sql, params);
//    return Saxophones.builder()
////      .saxophonePK("saxophones_pk")
//        .customerFK(customerFK)
//        .serialNumber(serialNumber)
//        .manufacturer(manufacturer)
//        .series(series)
//        .saxophonesType (type)
//        .build();
    
    SqlParams sqlparams = new SqlParams();
    KeyHolder keyHolder = new GeneratedKeyHolder();
    sqlparams.sql = ""
        + "INSERT into saxophones "
        + "(customer_fk, serial_number, manufacturer, type, series) " 
        + "VALUES (:customer_fk, :serial_number, :manufacturer, :type, :series)" ;
    sqlparams.source.addValue("customer_fk", customerFK);
    sqlparams.source.addValue("serial_number", serialNumber);
    sqlparams.source.addValue("manufacturer", manufacturer);
    sqlparams.source.addValue("type", type.toString());
    sqlparams.source.addValue("series", series);

    

    jdbcTemplate.update(sqlparams.sql, sqlparams.source, keyHolder);
    return Saxophones.builder()
        .saxophonesPK(keyHolder.getKey().intValue())
        .customerFK(customerFK)
        .serialNumber(serialNumber)
        .manufacturer(manufacturer)
        .series(series)
        .saxophonesType(type)
        .build();
  }

  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }

  @Override
  public List<Saxophones> fetchAllSaxophonesByCustomer(int customerFK) {                       
    // @formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM saxophones "
        + "WHERE customer_fk = :customer_fk ";
    // @formatter:on
    
    Map<String, Object> params = new HashMap<>();
    params.put("customer_fk", customerFK);
    
    return jdbcTemplate.query(sql, params,
        new RowMapper<>() {
          @Override
          public Saxophones mapRow(ResultSet rs, int rowNum) throws SQLException {
            // @formatter:off
            return Saxophones.builder()
                .saxophonesPK(rs.getInt("saxophones_pk"))
                .customerFK(rs.getInt("customer_fk"))
                .serialNumber(rs.getInt("serial_number"))
                .manufacturer(rs.getString("manufacturer"))
                .series(rs.getString("series"))
                .saxophonesType(SaxophonesType.valueOf(rs.getString("type")))
                .build();
            // @formatter:on
          }
    });    
  }

  @Override
  public Saxophones updateSaxophones(int saxophonesPK, Saxophones updatedSaxophones) {
    // @formatter:off
    String sql = ""
        + "UPDATE saxophones "
        + "SET "
        + "customer_fk = :customer_fk, "
        + "serial_number = :serial_number, "
        + "manufacturer = :manufacturer, "
        + "series = :series, "
        + "type = :type "
        + "WHERE saxophones_pk = :saxophones_pk;";
    // @formatter:on
    
    Map<String, Object> params = new HashMap<>();
    params.put("customer_fk", updatedSaxophones.getCustomerFK());
    params.put("serial_number", updatedSaxophones.getSerialNumber());
    params.put("manufacturer", updatedSaxophones.getManufacturer());
    params.put("series", updatedSaxophones.getSeries());
    params.put("type", updatedSaxophones.getSaxophonesType().toString());
    params.put("saxophones_pk", saxophonesPK);
    
   // jdbcTemplate.update(sql, params);
   
    //can be done on creates or inserts to check everything works properly
      if (jdbcTemplate.update(sql, params) == 0) {
      throw new NoSuchElementException("update failed :( ");
     }
    return Saxophones.builder()
        .saxophonesPK(saxophonesPK)
        .customerFK(updatedSaxophones.getCustomerFK())
        .serialNumber(updatedSaxophones.getSerialNumber())
        .manufacturer(updatedSaxophones.getManufacturer())
        .series(updatedSaxophones.getSeries())
        .saxophonesType(updatedSaxophones.getSaxophonesType())
        .build();
    
  }

  @Override
  public List<Saxophones> fetchSaxophones(SaxophonesType type) {                               
    // @formatter:off
    String sql = ""
        + "SELECT * "
        + "FROM saxophones "
        + "WHERE type = :type ";
    // @formatter:on
    
    Map<String, Object> params = new HashMap<>();
    params.put("type", type.toString());
    
    return jdbcTemplate.query(sql, params,
        new RowMapper<>() {
          @Override
          public Saxophones mapRow(ResultSet rs, int rowNum) throws SQLException {
            // @formatter:off
            return Saxophones.builder()
                .saxophonesPK(rs.getInt("saxophones_pk"))
                .customerFK(rs.getInt("customer_fk"))
                .serialNumber(rs.getInt("serial_number"))
                .manufacturer(rs.getString("manufacturer"))
                .series(rs.getString("series"))
                .saxophonesType(SaxophonesType.valueOf(rs.getString("type")))
                .build();
            // @formatter:on
          }
    });    
  }
}
