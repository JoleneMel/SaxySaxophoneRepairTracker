package com.saxyrepairtracker.saxophone.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doThrow;
import java.util.List;
import java.util.Map;
//import java.util.stream.Stream;
import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import com.saxyrepairtracker.saxophone.controller.support.FetchEmployeesTestSupport;
import com.saxyrepairtracker.saxophone.entity.Employee;

//For future implementation
//Below is the test annotation 
//we are telling it that we want the tests to be run in a web environment AND to ensure the tests will not 
//run on top of each other we specify the .random_port for each test class
//host class is always local host means it will stay within the local machine environment 
//class FetchJeepTest extends FetchJeepTestSupport {
class FetchEmployeesTest {

  @Nested
  @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
  @ActiveProfiles("test")
  @Sql(
//      scripts = {"classpath:flyway/migrations/V1.0__Jeep_Schema.sql", 
//                  "classpath:flyway/migrations/V1.1__Jeep_Data.sql"}, 
          scripts = {"Saxophone repair.sql", 
                      "Saxophone repair data.sql"}, 
            config = @SqlConfig(encoding = "utf-8"))
  class TestThatDoNotPolluteTheApplicationContext extends FetchEmployeesTestSupport {
    /**
     * 
     */
    @Test
    void testThatAnEmployeeAreReturnedWhenAValidFirstNameAndLastNameAreSupplied() {
   // Given: a valid first name, last name, and URI
      String firstName = "Jojo";
      String lastName = "Mel";
      String uri = 
          //Update Uri name to an employee not list 
          //Add payrate
          String.format("%s?firstName=%s&lastName=%s", getBaseUriForEmployees(), firstName, lastName);
    
       // When: a connection is made to the URI
       ResponseEntity<List<Employee>> response = getRestTemplate().exchange(uri, 
               HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
    
       // Then: a not found (404) status code is returned
       assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); 
    
       // And: an error message is returned
       List<Employee> actual = response.getBody();
       List<Employee> expected = buildExpected();
       
       assertThat(actual).isEqualTo(expected);
    }
    
    /**
     * 
     */
    // Test for error message
//    @Test
//    void testThatAnErrorMessageIsReturnedWhenAnUnknownNamesAreSupplied() {
//       // Given: a valid first name, last name, and URI
//      String firstName = "Jojo";
//      String lastName = "Mel";
//      String uri = 
//          String.format("%s?firstName=%s&lastName=%s", getBaseUriForEmployees(), firstName, lastName);
//    
//       // When: a connection is made to the URI
//       ResponseEntity<Map<String, Object>> response = getRestTemplate().exchange(uri, 
//               HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
//    
//       // Then: a not found (404) status code is returned
//       assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND); 
//    
//       // And: an error message is returned
//       Map<String, Object> error = response.getBody();
//       
//       assertErrorMessageValid(error, HttpStatus.NOT_FOUND);
//    }
//
////    /**
////     * 
////     */
////    // Test for invalid value message
//    @ParameterizedTest
//    @MethodSource("com.promineotech.jeep.controller.FetchJeepTest#parametersForInvalidInput")
//    void testThatAnErrorMessageIsReturnedWhenAnInvalidValueIsSupplied(
//        String firstName, String lastName, String reason) {
//       // Given: a valid model, trim, and URI
//      String uri = 
//          String.format("%s?firstName=%s&lastName=%s", getBaseUriForEmployees(), firstName, lastName);
//       // When: a connection is made to the URI
//       ResponseEntity<Map<String, Object>> response = getRestTemplate().exchange(uri, 
//               HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
//    
//       // Then: a not found (404) status code is returned
//       assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST); 
//    
//       // And: an error message is returned
//       Map<String, Object> error = response.getBody();
//       
//       assertErrorMessageValid(error, HttpStatus.BAD_REQUEST);
//    }
//
//  }
//    static Stream<Arguments> parametersForInvalidInput() {
//      // @formatter:off
//      return Stream.of(
//          arguments("WRANGLER", "@#$%^&&%", "Trim constains non-alpha-numeric chars"),
//          arguments("WRANGLER", "C".repeat(Constants.TRIM_MAX_lENGTH +1), "Trim length to long"),
//          arguments("INVALID", "SPORT", "Model is not enum value")
//      // @ formatter:on    
//          );
//    }
//  @Nested
//  @SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//  @ActiveProfiles("test")
//  @Sql(
//      scripts = {"classpath:flyway/migrations/V1.0__Jeep_Schema.sql", 
//                  "classpath:flyway/migrations/V1.1__Jeep_Data.sql"}, 
//            config = @SqlConfig(encoding = "utf-8"))
//  class TestThatPolluteTheApplicationContext extends FetchJeepTestSupport {
//    @MockBean
//    private JeepSalesService jeepSalesService;
//    
//    /**
//     * 
//     */
//    // Test for error message
//    @Test
//    void testThatUnplannedErrorResultsInA500Status() {
//       // Given: a valid model, trim, and URI
//      JeepModel model = JeepModel.WRANGLER;
//      String trim = "Invalid";
//      String uri = 
//          String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);
//    
//      doThrow(new RuntimeException("Ouch!")).when(jeepSalesService)
//        .fetchJeeps(model, trim);
//      
////        When: a connection is made to the URI
//              ResponseEntity<Map<String, Object>> response = 
//              getRestTemplate().exchange(
//                  uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
//      
//       // Then: an internal server error (500) status is returned
//       assertThat(response.getStatusCode())
//           .isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR); 
//    
//       // And: an error message is returned
//       Map<String, Object> error = response.getBody();
//       
//       assertErrorMessageValid(error, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//  }
 
  }

}
