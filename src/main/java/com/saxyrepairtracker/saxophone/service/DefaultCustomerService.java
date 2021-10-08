package com.saxyrepairtracker.saxophone.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.saxyrepairtracker.saxophone.dao.CustomerDao;
import com.saxyrepairtracker.saxophone.entity.Customer;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DefaultCustomerService implements CustomerService{
  
  @Autowired
  private CustomerDao customerDao;
  
  //Default Customer Service class implements everything from the Customer Service interface.
  //Next it all get's handed off to the Customer Data Access Object (Dao).
  
  @Override
  public List<Customer> fetchAllCustomers() {                                                  
    List<Customer> customer = customerDao.fetchAllCustomers();
    if(customer.isEmpty()) {
      String msg = String.format("We have no customers :(");
          throw new NoSuchElementException(msg);
    }
   // Collections.sort((List<Employee>) employees);
    return customer;
  }
  
  @Transactional(readOnly = true)
  @Override
  public List<Customer> fetchACustomer(String firstName, String lastName) {                    
    log.info("Fetch Customer in service layer");
    
    List<Customer> customer = customerDao.fetchACustomer(firstName, lastName);
          if(customer.isEmpty()) {
              String msg = String.format("No customer was found with firstName=%s and lastName=%s", firstName, lastName);
                throw new NoSuchElementException(msg);
    } 
       return customer;
  }
  
  @Override
  public List<Customer> fetchCustomerByFirstName(String firstName) {                           
    log.info("Fetch Customer in service layer");
    
    List<Customer> customer = customerDao.fetchCustomerByFirstName(firstName);
          if(customer.isEmpty()) {
              String msg = String.format("No customer was found with firstName=%s", firstName);
                throw new NoSuchElementException(msg);
    } 
       return customer;
  }
  

  @Override
  public Customer createCustomer (String firstName, String lastName, String phone) {           
    log.info("create Customers in service layer");
    return customerDao.createCustomer (firstName, lastName, phone);
  }

  public Customer updateCustomer(int customerPK, Customer updatedCustomer) {                   
    log.info("updates Customer in service layer");
    return customerDao.updateCustomer(customerPK, updatedCustomer);
  }

}
