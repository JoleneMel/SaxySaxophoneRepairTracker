package com.saxyrepairtracker.saxophone.controller.support;
//For future implementation
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import lombok.Getter;
//THIS IS USED ONLY IN TESTING
//Now here is where we get the random port, we are putting it within here so all the test classes can use it 
public class BaseTest {
  @LocalServerPort
  private int serverPort;
  
  //TEST REST TEMPLATE TO SEND THE HTTP REQUESTS 
  //This one allows a test rest template to be created for us 
  @Autowired 
  @Getter
  private TestRestTemplate restTemplate;
  
  //retrieve the uri with the method 
  //we want to return a String with this method 
  protected String getBaseUriForEmployees() {
    //this is the test that will be running in the background
    return String.format("http://localhost:%d/employees", serverPort);
  }
  
  //This should be an extension of employees 
  //we want to return a String with this method 
  protected String getBaseUriForNewEmployee() {
    //this is the test that will be running in the background
    return String.format("http://localhost:%d/newemployee", serverPort);
  }
  
  //He renames the top one but I think thats unneeded.
  protected String getBaseUriForOrders() {
    //this is the test that will be running in the background
    return String.format("http://localhost:%d/orders", serverPort);
  }
  
  protected String getBaseUriForCustomer() {
    // this is the test that will be running in the background
    return String.format("http://localhost:%d/customer", serverPort);
  }
  
  protected String getBaseUriForNewCustomer() {
    // this is the test that will be running in the background
    return String.format("http://localhost:%d/newcustomer", serverPort);
  }
  
  protected String getBaseUriForSaxophones() {
    return String.format("http://localhost:%d/saxophones", serverPort);
  }
  
  protected String getBaseUriForNewSaxophones() {
    return String.format("http://localhost:%d/newsaxophones", serverPort);
  }
}
