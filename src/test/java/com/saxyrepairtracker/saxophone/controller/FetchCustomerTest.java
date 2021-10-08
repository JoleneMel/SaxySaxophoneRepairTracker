//package com.saxyrepairtracker.saxophone.controller;
////For future implementation
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.doThrow;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Stream;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.MethodSource;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.core.ParameterizedTypeReference;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.jdbc.Sql;
//import com.saxyrepairtracker.saxophone.controller.support.FetchCustomerTestSupport;
//import com.saxyrepairtracker.saxophone.entity.Customer;
//import com.saxyrepairtracker.saxophone.service.CustomerService;
//
//@Nested
//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
//@ActiveProfiles("test")
//@Sql(
//    scripts = {"Saxophone repair.sql", 
//                "Saxophone repair data.sql"}, 
//          config = @SqlConfig(encoding = "utf-8"))
//
//public class FetchCustomerTest extends FetchCustomerTestSupport {
//  
//
//
//  class TestThatDoNotPolluteTheApplicationContext extends FetchCustomerTestSupport {
//    /**
//     * 
//     */
//    @Test
//    void testThatJeepsAreReturnedWhenAValidModelModelAndTrimAreSupplied() {
//       // Given: a valid model, trim, and URI
//      String firstName = "Stephanie";
//      String lastName = "Lynn";
//      String uri = 
//          String.format("%s?firstName=%s&lastName=%s", getBaseUri(), firstName, lastName);
//    
//       // When: a connection is made to the URI
//       ResponseEntity<List<Customer>> response = getRestTemplate().exchange(uri, 
//               HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
//    
//       // Then: a not found (404) status code is returned
//       assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK); 
//    
//       // And: an error message is returned
//       List<Customer> actual = response.getBody();
//       List<Customer> expected = buildExpected();
//       
//       assertThat(actual).isEqualTo(expected);
//    }
//    
//    /**
//     * 
//     */
//    // Test for error message
//    @Test
//    void testThatAnErrorMessageIsReturnedWhenAnUnknownTrimIsSupplied() {
//       // Given: a valid model, trim, and URI
//      String firstName = "Stephanie";
//      String lastName = "Unknown Value";
//      String uri = 
//          String.format("%s?model=%s&trim=%s", getBaseUri(), firstName, lastName);
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
//    /**
//     * 
//     */
//    // Test for invalid value message
//    @ParameterizedTest
//    @MethodSource("com.promineotech.jeep.controller.FetchJeepTest#parametersForInvalidInput")
//    void testThatAnErrorMessageIsReturnedWhenAnInvalidValueIsSupplied(
//        String model, String trim, String reason) {
//       // Given: a valid model, trim, and URI
//      String uri = 
//          String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);
//    
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
//          arguments("WRANGLER", "C", "Trim length to long"),
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
//  class TestThatPolluteTheApplicationContext extends FetchCustomerTestSupport {
//    @MockBean
//    private CustomerService customerService;
//    
//    /**
//     * 
//     */
//    // Test for error message
//    @Test
//    void testThatUnplannedErrorResultsInA500Status() {
//       // Given: a valid model, trim, and URI
//      String firstName = "Moe";
//      String lastName = "Schmoe";
//      String uri = 
//          String.format("%s?model=%s&trim=%s", getBaseUri(), firstName, lastName);
//    
//      doThrow(new RuntimeException("Oops!")).when(customerService)
//        .fetchCustomer(firstName, lastName);
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
//
//}
