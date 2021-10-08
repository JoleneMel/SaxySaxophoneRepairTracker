package com.saxyrepairtracker.saxophone.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
//import com.promineotech.jeep.controller.support.CreateOrderTestSupport;
//import com.promineotech.jeep.entity.JeepModel;
//import com.promineotech.jeep.entity.Order;
import com.saxyrepairtracker.saxophone.controller.support.CreateEmployeeTestSupport;
import com.saxyrepairtracker.saxophone.entity.Employee;
//For future implementation
//Create test of employee

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(
     scripts = {"Saxopone repair.sql", 
         "Saxopone repair data.sql"}, 
                  config = @SqlConfig(encoding = "utf-8"))
class CreateEmployeeTest extends CreateEmployeeTestSupport{

  //This is the POST method 
//  @Test
//  void testCreateEmployeeReturnsSuccess201() {
//    //Given: an order as JSON 
////    String body = createEmployeeBody();
//    String uri = getBaseUriForNewEmployee();
//    
//    HttpHeaders headers = new HttpHeaders();
//    headers.setContentType(MediaType.APPLICATION_JSON);
//
//    HttpEntity<String> bodyEntity = new HttpEntity<>(body, headers);
//    //When: the order is sent
//   ResponseEntity<Employee> response = getRestTemplate().exchange(uri, HttpMethod.POST, bodyEntity, 
//        Employee.class);
//
//   //Then: a 201 status is returned 
//    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//    
//    //And: and returned order is correct
//    assertThat(response.getBody()).isNotNull();
//
//    Employee employee = response.getBody();
////    assertThat(order.getCustomer().getCustomerId()).isEqualTo("MORISON_LINA");
////    assertThat(order.getModel().getModelId()).isEqualTo(JeepModel.WRANGLER);
////    assertThat(order.getModel().getTrimLevel()).isEqualTo("Sport Altitude");
////    assertThat(order.getModel().getNumDoors()).isEqualTo(4);
////    assertThat(order.getColor().getColorId()).isEqualTo("EXT_NACHO");
////    assertThat(order.getEngine().getEngineId()).isEqualTo("2_0_TURBO");
////    assertThat(order.getTire().getTireId()).isEqualTo("35_TOYO");
////    assertThat(order.getOptions()).hasSize(6);

//  }






  @Test
  void test() {
    fail("Not yet implemented");
  }

}
