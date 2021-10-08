package com.saxyrepairtracker.saxophone.controller;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.saxyrepairtracker.saxophone.entity.ServiceLineItem;
import com.saxyrepairtracker.saxophone.entity.RepairType;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/serviceLineItem")
@OpenAPIDefinition(info = @Info(title = "Service Line Item"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})
public interface ServiceLineItemController {

  //Begins by telling
  
  // @formatter:off
  @Operation(
      summary = "Returns a list of Service line items",
      description = "Returns a list of Customers all first and last name",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "A list of Customers is returned",
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = ServiceLineItem.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Customers were found with the input criteria",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.",  
              content = @Content(mediaType = "application/json"))
      }

  )
  @GetMapping("/all")
  @ResponseStatus(code = HttpStatus.OK)
  List<ServiceLineItem> fetchAllServiceLineItems(                                                            
    );
 
  
  
  
  
  
  // @formatter:off
  @Operation( 
      summary = "Return a Service Line Item",
      description = "Returns a service line item after given the service ticket",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "A service line item is returned",
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = ServiceLineItem.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Customers found",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.",  
              content = @Content(mediaType = "application/json")),
      },
      parameters = {
          @Parameter(name = "serviceFK",
              allowEmptyValue = false,
              required = false,
              description = "Customer's service ticket for the instrument")
        }
  )
  @GetMapping("/An item")
  @ResponseStatus(code = HttpStatus.OK)
  List<ServiceLineItem> fetchAServiceLineItem(                                                               
      @RequestParam(required = false)
      int serviceFK);    





  
  // @formatter:off
  @Operation(
      summary = "Creates a new Service Line Item",
      description = "Returns the created Service Line Item",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "A new Service Line Item has been added",
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = ServiceLineItem.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Service Line Item was found",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.",  
              content = @Content(mediaType = "application/json")),
      },
      parameters = {
          @Parameter(name = "serviceFK", 
              allowEmptyValue = false, 
              required = false, 
              description = "The Service ticket number"),
          @Parameter(name = "saxophonesFK", 
              allowEmptyValue = false, 
              required = false, 
              description = "The saxophone tied to the ticket"),
          @Parameter(name = "employeeFK", 
              allowEmptyValue = false, 
              required = false, 
              description = "The employee working on the saxophone"),
          @Parameter(name = "laborHours", 
              allowEmptyValue = false, 
              required = false, 
              description = "The time spent working on the saxophone."),
//          @Parameter(name = "repairType", 
//              allowEmptyValue = false, 
//              required = false, 
//              description = "The work needed on the saxophone"),
          @Parameter(name = "partCost", 
              allowEmptyValue = false, 
              required = false, 
              description = "The cost of replacement parts"),
          @Parameter(name = "additionalFees", 
              allowEmptyValue = false, 
              required = false, 
              description = "Any additional costs for the repair"),
          @Parameter(name = "totalCost", 
              allowEmptyValue = true, 
              required = false, 
              description = "The total cost of the repair")
          }

  
  )
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  ServiceLineItem createServiceLineItem (int serviceFK, int saxophonesFK, int employeeFK,  
      BigDecimal laborHours,  RepairType repairType, BigDecimal partCost, BigDecimal additionalFees,
      BigDecimal totalCost);                    
  
  
  
  
  
  // @formatter:off
  @Operation(
      summary = "Updates a Service Line Item",
      description = "Returns the updated Service Line Item",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "A new Service Line Item has been updated",
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = ServiceLineItem.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Service Line Item was found",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.",  
              content = @Content(mediaType = "application/json")),
      },
      parameters = {
            @Parameter(name = "lineItemPK",
              allowEmptyValue = false,
              required = false,
              description = "The key for the service item")
          }
      )
      @PutMapping
      @ResponseStatus(code = HttpStatus.OK)
      ServiceLineItem updateServiceLineItem (int lineItemPK, 
          @Valid @RequestBody ServiceLineItem updatedItem);      
  
}
