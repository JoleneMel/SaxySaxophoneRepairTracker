package com.saxyrepairtracker.saxophone.controller.support;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import com.saxyrepairtracker.saxophone.entity.Customer;

public class FetchCustomerTestSupport extends BaseTest {
  protected List<Customer> buildExpected() {
    List<Customer> list = new LinkedList<>();
    
    // @formatter:off
    list.add(Customer.builder()
      //  .customerPK()
        .firstName("Ran")
        .lastName("Randy")
        .phone("6036036030")
        .build());
    // @formatter:on
    
//    Collections.sort(list);
    return list;
  }
    

  /**
   * 
   * @param error
   * @param status
   */
  protected void assertErrorMessage(Map<String, Object> error, 
      HttpStatus status) {
    //@formatter:off
     assertThat(error)
       .containsKey("message")
       .containsEntry("status code", status.value())
       .containsEntry("uri", "/customer")
       .containsKey("timestamp")
       .containsEntry("reason", status.getReasonPhrase());
     //@formatter:on
  }
//  I
  
  /**
   * 
   * @param error
   * @param status
   */
  protected void assertErrorMessageValid(Map<String, Object> error, 
      HttpStatus status) {
    //@formatter:off
     assertThat(error)
       .containsKey("message")
       .containsEntry("status code", status.value())
       .containsEntry("uri", "/customer")
       .containsKey("timestamp")
       .containsEntry("reason", status.getReasonPhrase());
     //@formatter:on
  }
  
}
