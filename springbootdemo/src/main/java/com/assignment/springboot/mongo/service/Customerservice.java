package com.assignment.springboot.mongo.service;

import java.util.Collection;
import java.util.List;

import com.assignment.springboot.mongo.model.Customer;


public interface Customerservice {

	
	public void createCustomer(List<Customer> cust);

	
	public Collection<Customer> getAllCustomers();

	
	public Customer findCustomerById(int id);

	
	public void deleteCustomerById(int id);

	
	public void updateCustomer(Customer cust);

	
	public void deleteAllCustomers();
}