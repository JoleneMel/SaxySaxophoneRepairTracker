package com.saxyrepairtracker.saxophone.controller;

import java.math.BigDecimal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import com.saxyrepairtracker.saxophone.entity.ServiceTicket;
import com.saxyrepairtracker.saxophone.entity.Status;

@Validated
@RequestMapping("/serviceTickets")
@OpenAPIDefinition(info = @Info(title = "Service Ticket"), servers = {
@Server(url = "http://localhost:8080", description = "Local server.")})
public interface ServiceTicketController {


  // @formatter:off
  @Operation(
      summary = "Returns a Service Ticket By Status",
      description = "Returns a Service Ticket given a Status",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "An Service Ticket is returned",
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = ServiceTicket.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Service Tickets were found with the input criteria",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.",  
              content = @Content(mediaType = "application/json"))
      },
      parameters = {
          @Parameter(name = "Status", 
              allowEmptyValue = false, 
              required = false, 
              description = "The Status (i.e., 'Awaiting_Arrival')")
      }
  )
  //this is for gets not deletes, postmapping, deletemapping etc for the methods 
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  List<ServiceTicket> fetchServiceTicketByStatus(
      @RequestParam(required = false)
      Status Status
    );
  //@formatter:on 


// /all 
// @formatter:off
@Operation(
    summary = "Returns all Service Tickets",
    description = "Returns a List of Service Tickets",
    responses = {
        @ApiResponse(
            responseCode = "200",
            description = "A List of all Service Tickets is returned",
            content = @Content(
                mediaType = "application/json", 
                schema = @Schema(implementation = ServiceTicket.class))),
        @ApiResponse(
            responseCode = "400", 
            description = "The request parameters are invalid",  
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "404", 
            description = "No Service Tickets were found with the input criteria",  
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "500", 
            description = "An unplanned error occurred.",  
            content = @Content(mediaType = "application/json"))
    }
)
//this is for gets not deletes, postmapping, deletemapping etc for the methods 
      @GetMapping("/all")
      @ResponseStatus(code = HttpStatus.OK)
      List<ServiceTicket> fetchAllServiceTickets();  
 //@formatter:on


//POST
//Create Method ServiceTickets
// @formatter:off
@Operation(
    summary = "Returns a new Service Ticket",
    description = "Returns a Service Ticket given a first and last name",
    responses = {
        @ApiResponse(
            responseCode = "201", 
            description = "A new Service Ticket has been created",
            content = @Content(
                mediaType = "application/json", 
                schema = @Schema(implementation = ServiceTicket.class))),
        @ApiResponse(
            responseCode = "400", 
            description = "The request parameters are invalid",  
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "404", 
            description = "No Service Tickets component was not found with the input criteria",  
            content = @Content(mediaType = "application/json")),//maybe reword
        @ApiResponse(
            responseCode = "500", 
            description = "An unplanned error occurred.",  
            content = @Content(mediaType = "application/json"))
    },
    parameters = {
        @Parameter(name = "customerFK", 
            allowEmptyValue = false, 
            required = false, 
            description = "The customer id (i.e., '1,2,3')"),
        @Parameter(name = "description", 
        allowEmptyValue = false, 
        required = false, 
        description = "The description (i.e., 'Reason they came there')"),
         @Parameter(name = "status", 
          allowEmptyValue = false, 
          required = false, 
          description = "A status (i.e., 'AWAITING_ARRIVAL etc')"),
        @Parameter(name = "estimatedCost", 
        allowEmptyValue = false, 
        required = false, 
        description = "estimatedCost (i.e., '135')"),
        @Parameter(name = "actualCost", 
        allowEmptyValue = true, //Will not make it null this will be fixed with further development
        required = true, 
        description = "actual Cost can be null (i.e., '152')")
    }
    
)
      @PostMapping("/Create")
      @ResponseStatus(code = HttpStatus.CREATED)
      ServiceTicket createServiceTicket(int customerFK, String description, Status status, 
          BigDecimal estimatedCost, BigDecimal actualCost);
//@formatter:on



//Deletes Employee 
//deleteEmployee
// @formatter:off
@Operation(
    summary = "Deletes an Service Ticket",
    description = "Deletes an Employee given an id",
    responses = {
        @ApiResponse(
            responseCode = "200",
            description = "Service Ticket was deleted",
            content = @Content(
                mediaType = "application/json", 
                schema = @Schema(implementation = ServiceTicket.class))),
        @ApiResponse(
            responseCode = "400", 
            description = "The request parameters are invalid",  
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "404", 
            description = "No Service Tickets were found with the input criteria",  
            content = @Content(mediaType = "application/json")),
        @ApiResponse(
            responseCode = "500", 
            description = "An unplanned error occurred.",  
            content = @Content(mediaType = "application/json"))
    },
    parameters = {
        @Parameter(name = "servicePK", 
            allowEmptyValue = false, 
            required = false, 
            description = "servicePK (i.e., 3)"),
    }
)
//this is for gets not deletes, postmapping, deletemapping etc for the methods 
    @DeleteMapping("/servicePK")
    @ResponseStatus(code = HttpStatus.OK)
    void deleteServiceTicket(int deleteId); 

 //@formatter:on


//PUT Update 
//updateServiceTicket
//@formatter:off
@Operation(
  summary = "Returns an updated ServiceTicket",
  description = "Returns a Service Ticket to update given an id",
  responses = {
      @ApiResponse(
          responseCode = "200",
          description = "An updated Service Ticket is returned",
          content = @Content(
              mediaType = "application/json", 
              schema = @Schema(implementation = ServiceTicket.class))),
      @ApiResponse(
          responseCode = "400", 
          description = "The request parameters are invalid",  
          content = @Content(mediaType = "application/json")),
      @ApiResponse(
          responseCode = "404", 
          description = "No Service Tickets were found with the input criteria",  
          content = @Content(mediaType = "application/json")),
      @ApiResponse(
          responseCode = "500", 
          description = "An unplanned error occurred.",  
          content = @Content(mediaType = "application/json"))
  }, parameters = {
      @Parameter(name = "servicePK", 
          allowEmptyValue = false, 
          required = false, 
          description = "The Service Ticket's Id within our database")
}
)

@PutMapping
@ResponseStatus(code = HttpStatus.OK) 
ServiceTicket updateServiceTicket(
 int servicePK, 
  @Valid @RequestBody ServiceTicket updatedServiceTicket);

//@formatter:on
}
