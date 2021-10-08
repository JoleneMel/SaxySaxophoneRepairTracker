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
import com.saxyrepairtracker.saxophone.entity.Saxophones;
import com.saxyrepairtracker.saxophone.entity.SaxophonesType;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@Validated
@RequestMapping("/saxophones")
@OpenAPIDefinition(info = @Info(title = "Saxophones Service"), servers = {
    @Server(url = "http://localhost:8080", description = "Local server.")})

public interface SaxophonesController {

  //Saxophones Controller interface begins the implementations from the Saxophones entity before
  //it hands off to the default saxophones controller.
  
  // @formatter:off
  @Operation(
      summary = "Returns the list of Saxophones by type",
      description = "Returns a list of Saxophones",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "A list of Saxophones by type gets returned",
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Saxophones.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Saxophones were found",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.",  
              content = @Content(mediaType = "application/json")),
      },
      parameters = {
          @Parameter(name = "type", 
              allowEmptyValue = false, 
              required = false, 
              description = "TENOR, ALTO, BARI, SOPRANO, or OTHER")
          }
  )
  @GetMapping("/fetch saxophones")
  @ResponseStatus(code = HttpStatus.OK)
  List<Saxophones> fetchSaxophones(                                                            
      @RequestParam(required = false)
        SaxophonesType type
      );
  //@formatter:on
  
  // @formatter:off
  @Operation( //!!!
      summary = "Returns the list of Saxophones",
      description = "Returns the list of Saxophones",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "A list of Saxophones gets returned",
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Saxophones.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Saxophones were found",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.",  
              content = @Content(mediaType = "application/json")),
      }
  )
  @GetMapping("/all")
  @ResponseStatus(code = HttpStatus.OK)
  List<Saxophones> fetchAllSaxophones();                                                       
  
  // @formatter:off
  @Operation(
      summary = "Returns the list of Saxophones to one customer Id",
      description = "Returns the list of Saxophones to one customer Id",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "A list of Saxophones gets returned for that customer",
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Saxophones.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Saxophones were found",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.",  
              content = @Content(mediaType = "application/json")),
      },
      parameters = {
          @Parameter(name = "customerFK", 
              allowEmptyValue = false, 
              required = false, 
              description = "The customer's PK number within the database (1,2,3 ect...)"),
      }
  )
  @GetMapping("/fetch all saxophones within a customer ID")
  @ResponseStatus(code = HttpStatus.OK)                                                        
  List<Saxophones> fetchAllSaxophonesByCustomer(@RequestParam(required = false) int customerFK);
  
  // @formatter:off
  @Operation(
      summary = "Updates a Saxophone",
      description = "Returns the updated Saxophone",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Returns updated Saxophone",
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Saxophones.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Saxophones were found",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.",  
              content = @Content(mediaType = "application/json")),
      }, 
          parameters = {
              @Parameter(name = "saxophonesPK", 
                  allowEmptyValue = false, 
                  required = false, 
                  description = "The Saxophones ID within our database") //,
//              @Parameter(
//                  name = "updatedSaxophones", 
//                  required = true, 
//                  description = "The saxophone as JSON ")  
      }
  )
  @PutMapping
  @ResponseStatus(code = HttpStatus.OK)
  Saxophones updateSaxophones(  
       int saxophonesPK, 
      @Valid @RequestBody Saxophones updatedSaxophones);
  

  
  // @formatter:off
  @Operation(
      summary = "Creates a Saxophone",
      description = "Returns the created Saxophone",
      responses = {
          @ApiResponse(
              responseCode = "201",
              description = "A new Saxophone has been added",
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Saxophones.class))),
          @ApiResponse(
              responseCode = "400", 
              description = "The request parameters are invalid",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "404", 
              description = "No Saxophones were found",  
              content = @Content(mediaType = "application/json")),
          @ApiResponse(
              responseCode = "500", 
              description = "An unplanned error occurred.",  
              content = @Content(mediaType = "application/json")),
      },
      parameters = {
          @Parameter(name = "customerFK", 
              allowEmptyValue = false, 
              required = false, 
              description = "The customer's Id within our database"),
          @Parameter(name = "serialNumber", 
          allowEmptyValue = false, 
          required = false, 
          description = "The serial number on the back end of the horn"),
          @Parameter(name = "manufacturer", 
          allowEmptyValue = false, 
          required = false, 
          description = "The Saxophones manufacturer (Yamaha, Selmer, ect)"),
          @Parameter(name = "series", 
          allowEmptyValue = false, 
          required = false, 
          description = "The series of the Saxophone (i.e., Custom Z, Student, Professional)"),
          @Parameter(name = "type", 
          allowEmptyValue = false, 
          required = false, 
          description = "The type of Saxophone (Soprano, ALTO, TENOR, BARI, OTHER)"),
          }
  )
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  Saxophones createSaxophones(int customerFK, int serialNumber, String manufacturer, String series, SaxophonesType type);
}
