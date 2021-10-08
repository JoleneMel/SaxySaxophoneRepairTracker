package com.saxyrepairtracker.saxophone.controller.support;
//For future implementation
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import com.saxyrepairtracker.saxophone.entity.Employee;
import java.util.Map;
import org.springframework.http.HttpStatus;
public class FetchEmployeesTestSupport extends BaseTest{
    protected List<Employee> buildExpected() {
      List<Employee> list = new LinkedList<Employee>();
      
      //@formatter:off    
      list.add(Employee.builder()
          .firstName("Jojo")
          .lastName("Mel")
          .build());
      
//      list.add(Employee.builder()
//          .modelId(EmployeeModel.WRANGLER)
//          .trimLevel("Sport")
//          .numDoors(2)
//          .wheelSize(17)
//          .basePrice(new BigDecimal("28475.00"))
//          .build());    
      // @formatter:on
         
      //Collections.sort(list);
      return list;
    }
}
