package com.saxyrepairtracker.saxophone.controller.support;

import static org.assertj.core.api.Assertions.assertThat;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import com.saxyrepairtracker.saxophone.entity.Saxophones;
import com.saxyrepairtracker.saxophone.entity.SaxophonesType;

public class FetchSaxophonesTestSupport extends BaseTest {

    protected List<Saxophones> buildExpected() {
      List<Saxophones> list = new LinkedList<Saxophones>();
      
      // @formatter:off
     list.add(Saxophones.builder()
            .saxophonesType(SaxophonesType.TENOR)
            .customerFK(1)
            .serialNumber(15236654)
            .manufacturer("Yamaha")
            .series("Customer Z")
            .build());
     
     list.add(Saxophones.builder()
         .saxophonesType(SaxophonesType.TENOR)
         .customerFK(5)
         .serialNumber(75369852)
         .manufacturer("Selmer")
         .series("65")
         .build());
      // @formatter:on
  
     Collections.reverseOrder();
//      Collections.sort(list);
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
         .containsEntry("uri", "/saxophones")
         .containsKey("timestamp")
         .containsEntry("reason", status.getReasonPhrase());
       //@formatter:on
    }
//    I
    
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
         .containsEntry("uri", "/saxophones")
         .containsKey("timestamp")
         .containsEntry("reason", status.getReasonPhrase());
       //@formatter:on
    }
//    INSERT INTO models (model_id, trim_level, num_doors, wheel_size, base_price) VALUES('WRANGLER', 'Sport', 2, 17, 28475.00);
//    INSERT INTO models (model_id, trim_level, num_doors, wheel_size, base_price) VALUES('WRANGLER', 'Sport', 4, 17, 31975.00);
  

}
