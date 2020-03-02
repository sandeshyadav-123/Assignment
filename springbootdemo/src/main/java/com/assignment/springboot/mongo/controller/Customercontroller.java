package com.assignment.springboot.mongo.controller;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.springboot.mongo.model.Customer;
import com.assignment.springboot.mongo.security.CustomerNotFoundException;
import com.assignment.springboot.mongo.service.Customerserviceimpl;

@RestController("Customercontroller")
@RequestMapping(value= "/customers")

public class Customercontroller {

	
	Customerserviceimpl serv;
	
	
	@Autowired
	public Customercontroller(Customerserviceimpl cusimp) {
		
		this.serv=cusimp;
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

		
	@PostMapping(value= "/create",consumes = "application/json", produces = "application/json")
	public String createUser(@RequestBody List<Customer> cust) {
        serv.createCustomer(cust);
		return "Customer records created.";
    }
		

	@GetMapping(path = "/", produces = "application/json")
	public Collection<Customer> getAll() {
		logger.debug("Getting all customer.");
		return serv.getAllCustomers();
	}

	
	@GetMapping(value= "/getbyid/{customernumber}",produces = "application/json")
	
	public ResponseEntity<Customer> getById(@PathVariable(value= "customernumber")  int id) {
		logger.debug("Getting customer with customernumber= {}.", id);
		Customer cust = serv.findCustomerById(id);
		if(cust== null ) {
			
		
			//return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			throw   new CustomerNotFoundException("Customer not found");
			
		} else {
		
			return ResponseEntity.status(HttpStatus.OK).body(cust);
		}
				
	}
	
	@PutMapping(value= "/update/{customernumber}",consumes = "application/json", produces = "application/json")

	public String update(@PathVariable(value= "customernumber") int id, @RequestBody Customer c) {
		logger.debug("Updating Customer with customernumber= {}.", id);
	Customer	cust=serv.findCustomerById(id);
		if(cust== null ) {
			
						
			throw   new CustomerNotFoundException("Customer id not found for update");
			
		} else {
		
			c.setId(id);
			serv.updateCustomer(c);
			return "Customer record for customernumber= " + id + " updated.";
		}
		
	}
	
	@DeleteMapping(value= "/delete/{customernumber}")
	public String delete(@PathVariable(value= "customernumber") int id) {
				Customer	cust=serv.findCustomerById(id);
		if(cust== null ) {
			
						
			throw   new CustomerNotFoundException("Customer id not found for delete");
			
		} else {
		
			logger.debug("Deleting customer with customernumber= {}.", id);
			serv.deleteCustomerById(id);
			return "Customer record for customernumber= " + id + " deleted.";
		}
		
	}
		
	@DeleteMapping(value= "/deleteall",produces = "application/json")
	
	public String deleteAll() {
		logger.debug("Deleting all customer.");
		serv.deleteAllCustomers();
		return "All customer records deleted.";
		
	}
}