package com.saxyrepairtracker.saxophone.service;

import java.util.List;
import com.saxyrepairtracker.saxophone.entity.Customer;

public interface CustomerService {

  //Customer Service takes in from the Default Customer Controller into the now customer service interface.
  //From here, it then gets handed off to the Default Customer Service Class.
  
  List<Customer> fetchAllCustomers();                                                          
  
  List<Customer> fetchACustomer(String firstName, String lastName);                            

  List<Customer> fetchCustomerByFirstName(String firstName);                                   

  Customer createCustomer(String firstName, String lastName, String phone);                    
  
  Customer updateCustomer(int customerPK, Customer updatedCustomer);                           

}

