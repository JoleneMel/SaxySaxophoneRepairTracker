package com.saxyrepairtracker.saxophone.controller;

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
import com.saxyrepairtracker.saxophone.entity.ServiceLineItemStatus;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/serviceLineItemStatus")
@OpenAPIDefinition(info = @Info(title = "Service Item Line Item Status"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})

public interface ServiceLineItemStatusController {
  @Operation(
      summary = "Returns a Service Ticket Line Status",
      description = "Returns a Service Ticket Line Status given a updatePK and serviceFK",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "An Service Ticket Line Status is returned",
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = ServiceLineItemStatus.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Service Ticket Line Statuses were found with the input criteria",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.",  
              content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(name = "updatePK", 
              allowEmptyValue = false, 
              required = false, 
              description = "The updatePK (i.e., '1,2,3')"),
          @Parameter(name = "serviceFK", 
          allowEmptyValue = false, 
          required = false, 
          description = "The serviceFK (i.e., '1,2,3')")
      }
  )
  //this is for gets not deletes, postmapping, deletemapping etc for the methods 
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  List<ServiceLineItemStatus> fetchServiceTicketLineStatus(
      @RequestParam(required = false)
      int updatePK, 
      //@Pattern(regexp = "[\\w\\s]*")
      @RequestParam(required = false)
      int serviceFK);
  //@formatter:on


// /all 
// @formatter:off
@Operation(
    summary = "Returns all Service Ticket Line Statuses",
    description = "Returns a List of Service Ticket Line Statuses",
    responses = {
        @ApiResponse(
            responseCode = "200",
            description = "A List of all Service Ticket Line Statuses is returned",
            content = @Content(
                mediaType = "application/json", 
                schema = @Schema(implementation = ServiceLineItemStatus.class))),
        @ApiResponse(
            responseCode = "400", 
            description = "The request parameters are invalid",  
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "404", 
            description = "No Service Ticket Line Statuses were found with the input criteria",  
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "500", 
            description = "An unplanned error occurred.",  
            content = @Content(mediaType = "application/json"))
    }
)
      @GetMapping("/all")
      @ResponseStatus(code = HttpStatus.OK)
      List<ServiceLineItemStatus> fetchAllServiceTicketLineStatuses();  
 //@formatter:on


//POST

// @formatter:off
@Operation(
    summary = "Returns a new Service Ticket Line Item Status",
    description = "Returns a Service Ticket Line Item Status",
    responses = {
        @ApiResponse(
            responseCode = "201", 
            description = "A new Service Ticket Line Item Status has been created",
            content = @Content(
                mediaType = "application/json", 
                schema = @Schema(implementation = ServiceLineItemStatus.class))),
        @ApiResponse(
            responseCode = "400", 
            description = "The request parameters are invalid",  
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "404", 
            description = "No Employees component was not found with the input criteria",  
            content = @Content(mediaType = "application/json")),//maybe reword
        @ApiResponse(
            responseCode = "500", 
            description = "An unplanned error occurred.",  
            content = @Content(mediaType = "application/json"))
    },
    parameters = {
//        @Parameter(name = "updatePK", 
//            allowEmptyValue = false, 
//            required = false, 
//            description = "The updatePK (i.e., '1,2,3')"),
        @Parameter(name = "serviceFK", 
        allowEmptyValue = false, 
        required = false, 
        description = "The serviceFK (i.e., '1,2,3')"),
        @Parameter(name = "lineItemFK", 
        allowEmptyValue = false, 
        required = false, 
        description = "The lineItemFK (i.e., '1,2,3')"),
        @Parameter(name = "updates", 
        allowEmptyValue = false, 
        required = false, 
        description = "Update comment (i.e., 'Completed key replacement')")
    }
    
)
//this is for gets not deletes, postmapping, deletemapping etc for the methods 
      @PostMapping
      @ResponseStatus(code = HttpStatus.CREATED)
ServiceLineItemStatus createServiceLineItemStatus
(int serviceFK, int lineItemFK, String updates);
//@formatter:on


//not needed
////Deletes Employee 
////deleteEmployee
//// @formatter:off
//@Operation(
//    summary = "Deletes an Service Ticket Line Item Status",
//    description = "Deletes an Employee given an id",
//    responses = {
//        @ApiResponse(
//            responseCode = "200",
//            description = "Employee was deleted",
//            content = @Content(
//                mediaType = "application/json", 
//                schema = @Schema(implementation = ServiceLineItemStatus.class))),
//        @ApiResponse(
//            responseCode = "400", 
//            description = "The request parameters are invalid",  
//            content = @Content(mediaType = "application/json")),
//        @ApiResponse(
//            responseCode = "404", 
//            description = "No Employees were found with the input criteria",  
//            content = @Content(mediaType = "application/json")),
//        @ApiResponse(
//            responseCode = "500", 
//            description = "An unplanned error occurred.",  
//            content = @Content(mediaType = "application/json"))
//    },
//    parameters = {
//        @Parameter(name = "updatePK", 
//            allowEmptyValue = false, 
//            required = false, 
//            description = "The updatePK (i.e., '1,2,3')")
//    }
//)
////this is for gets not deletes, postmapping, deletemapping etc for the methods 
//    @DeleteMapping("/updatePK")
//    @ResponseStatus(code = HttpStatus.OK)
//    void deleteServiceLineItemStatus(int updatePK); 
////    @RequestParam(required = false)
////    int employeePK, 

 //@formatter:on

//PUT Update 
//EmployeeUpdate
//updateEmployee
//@formatter:off
@Operation(
  summary = "Returns an updated Service Ticket Line Item Status",
  description = "Returns a Service Ticket Line Item Status to update given an id",
  responses = {
      @ApiResponse(
          responseCode = "200",
          description = "An updated Employee is returned",
          content = @Content(
              mediaType = "application/json", 
              schema = @Schema(implementation = ServiceLineItemStatus.class))),
      @ApiResponse(
          responseCode = "400", 
          description = "The request parameters are invalid",  
          content = @Content(mediaType = "application/json")),
      @ApiResponse(
          responseCode = "404", 
          description = "No Employees were found with the input criteria",  
          content = @Content(mediaType = "application/json")),
      @ApiResponse(
          responseCode = "500", 
          description = "An unplanned error occurred.",  
          content = @Content(mediaType = "application/json"))
  },
  parameters = {
      @Parameter(name = "updatePK", 
          allowEmptyValue = false, 
          required = false, 
          description = "The Service Line Item Status Id within our database")
}
  
)
//this is for gets not deletes, postmapping, deletemapping etc for the methods 
@PutMapping
@ResponseStatus(code = HttpStatus.OK) //this may need to be tweaked a tad 
ServiceLineItemStatus updateServiceLineItemStatus(
  int updatePK, 
  @Valid @RequestBody ServiceLineItemStatus updatedServiceLineItemStatus);
//@formatter:on


}

